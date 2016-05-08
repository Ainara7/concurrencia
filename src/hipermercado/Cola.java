package hipermercado;
import java.util.*;
import java.text.*;

    /* Primero debemos identificar que clase es la hilo, la clase hilo es aquella que tiene muchos objetos,
 * por ejemplo aqui hay muchas cajas por lo tanto es la clase hilo. En el examen la clase hilo es la de los
 * usuarios, ya que habia muchos usuarios.
 * Todas las clases que no sean hilos, hay que ponerles el synchronized. 
 * 
 * Si tienes una duda con que clase es la hilo, si la que te dan implementada no tiene un metodo RUN,
 * significara que esa no es la clase hilo.
 * Otra pista para saber cual es la hilo, es que cuando tengamos que crear un array, el array tendra objetos de un tipo,
 * ese tipo de objetos no puede ser que su clase sea la hilo. Aqui por ejemplo se crear un array que dentro tiene clientes,
 * por lo tantos los clientes no son la clase hilo.
 * En el examen habia que crear un array de bicicletas, por lo tanto la clase bicicletas no es la del hilo. */

public class Cola {
    
    private int tam_max;
    private boolean estado = true;
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
   
    
    /* El synchronized se pone en las clases en las que hay que evitar que varios procesos entren
	 * a la vez a la clase. Los synchronized unicamente van en una clase como maximo. 
	 * Generalmente si no sabemos diferenciar donde va el sinchronized, suele ir en los metodos de la clase
	 * que te diga de añadir, eliminar y esas cosas.
	 * Otras pista, es que el synchronized va en la clase tocha(clase con metodos largos) que no es la hilo. 
	 * El SYNCHRONIZED, unicamente se utilizan en los metodos que hagan alguna operacion que tenga que ver con semaforos,
	 * no se usa en todos los metodos, solo en esos.*/
    
    public synchronized void añadirFinal(Cliente cliente){
        
        if(estado == true){
            
            clientes.add(cliente);
            /* las cajas estan continuamente pidiendo clientes. Sin embargo, cuando una cola esta vacia si pides un clientes,
            * ese proceso que pide, se va a quedar bloqueado.
            * Por lo tanto, cuando añadimos un cliente al final de la cola en este metodo, lo que hacemos es que esos
            * procesos bloqueados que pedian clientes vuelvan a ejecutarse desde el principio de su metodo, haciendo
            * que esos procesos tengan la posibilidad de coger al cliente nuevo que se añadio a la cola */
            
            notifyAll();
            tam_max++;
            System.out.println("Añadido " + cliente.dameNombre() + " al final de la cola");
            
        }else{
            System.out.println("La caja esta cerrada");
        }
        
    }
    
    public synchronized void añadirPrincipio(Cliente cliente){
    
        clientes.add(0,cliente);
        notifyAll();
        tam_max++;
        System.out.println("Añadido " + cliente.dameNombre() + " al principio de la cola");
        
    }
    
    public synchronized Cliente sacar(){
    
        if( estado == false && clientes.size() == 0 ){
            return null;
        }
        
        if( clientes.size() > 0 ){

            Cliente borrado = clientes.remove(0);

            System.out.println("El cliente con nombre: " + borrado.dameNombre() + " ha sido borrado de la cola");
            return borrado;

        }else{

            try{

                wait(10000);
                if( clientes.size() > 0 ){

                    Cliente borrado = clientes.remove(0);

                    System.out.println("El cliente con nombre: " + borrado.dameNombre() + "ha sido borrado de la cola");
                    return borrado;

                }else{
                    return null;
                }

            }catch(Exception e){}

        }
        
        return null;
            
    }
    
    public void cerrar(){
        this.estado = false;
    }
    
    public int tamaño(){
        return clientes.size();
    }
    
}

/*
public synchronized Cliente sacar(){
        long tiempo_inicio = System.currentTimeMillis();
        //long tiempo_actual = System.currentTimeMillis();
        
        //10 segundos = 10000 milisegundos
        while( System.currentTimeMillis() - tiempo_inicio) <= 10000 ){
            
            try{
                
                if( this.cola_clientes.size() > 0 ){
                    notifyAll(); // 
                    
                    //Obtenemos la fecha
                    Calendar fecha = Calendar.getInstance();
                    //Fijamos un formato para la fecha
                    SimpleDateFormat formato_fecha = new SimpleDateFormat(" dd/MM/yyyy ' a las ' hh:mm:ss ");
                    //Indicamos a que fecha le aplicamos el formato
                    System.out.prinln( borrado.dameNombre() + " extraido de la cola el " + dateFormat.format(fecha) );
                    
                    return this.cola_clientes.remove(o); 
                         
                }else{
                    //esto de aqui hace que cuando un proceso se despierte y si vuelve a caer en el wait, se quede con el tiempo que le corresponde(no los 10 segundos)???
                    // respecto a esto estaba mal expresado, pero la idea era buena, el wait q le estabas haciendo era desde los 10000 menos el tiempo del origen de los tiempos
                    // y debería de ser desde (System.currentTimeMillis() - tiempo_inicio) - 10000
                    // Por lo que me conto el profe el hilo principal es el main y Caja sería como hilo secundario, y cola iría haciendo cosas en función
                    // de como vaya en run la gestión, y si está bn lo de hacer start en main, pq eso iniciaría run hasta que le diga q se cierre. 
                    // cuando finaliza run se acaba el hilo
                    // esto funciona asi en un metodo puedes tener un bloque try-catch entoncesimagínate que en el try hay un wait pues eso significa q los hilos están esperando
                    // luego en el catch hay una interrupción y debtro pues la gestión de esta en el run deberíamos gestionar la interrupción del duende avería por ejemplo o algo
                    // así me dijeron los chicos de atrás, entonces después de gestionar la interrupción pones un notifyall esto hace q se liberen los hilos, es decir tu caja(pq es thread)
                    // estos hilos luchan por el lock
                    wait( 10000 - System.currentTimeMillis() ); // el hilo espera a otro hilo o q pase este tiempo
                    cerrar();
                    System.out.println("Caja cerrada ya que ha pasado mas de 10 segundos sin clientes");
                }
                
            }catch( InterruptedException e ){
                cerrar();
                añadirPrincipio( this.cola_clientes.get(o) );
                System.out.println("Caja averiada, el cliente ha sido mandada al inicio de la cola");
                return null;
            }
            
        }//fin While
        
        cerrar();
        System.out.println("La caja se ha cerrado ya que no ha recibido clientes durante 10 segundos");
        return null;
        
    }
*/