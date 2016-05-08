package hipermercado;

import java.util.Scanner;

 /* -el hilo principal es el q se ejecuta en el main
el principal reserva memoria de todos los atributos del obj y luego va a ejecutar el main
y c/obj q es hilo le pones el .start() -> cuando llega el hilo principal a start genera un nuevo hilo y luego continua
lo q hace ese nuevo hilo es ir a ejecutar lo q hay en el run, lo estan haciendo a la vez, el hilo principal esta ejecutando
el resto de instrucciones q estan debajo del start y el secundario está ejecutando las instrucciones del run
OJO: Los hilos no se tienen q ejecutar cuando se lanzan pero se ejcutarán, y no sabemos el orden se ejecución de los hilos. El SO es el encargado de 
ejecutar los hilos */
public class main{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int numCajas;
		int numClientes;
		Cola cola = new Cola();
		Contabilidad contabilidad = new Contabilidad();
		Caja caja;
		Cliente cliente;

		System.out.println("Introduzca el numero de cajas que quiera usar: ");
		numCajas = input.nextInt();
		System.out.println("Has puesto " + numCajas + " cajas");
		Caja[] cajas = new Caja[numCajas];
		for (int i = 0; i < numCajas; i++) {
			caja = new Caja(cola, contabilidad);
			cajas[i] = caja;
			System.out.println(caja.getIdd());
		}
		
		

		System.out.println("Introduzca el numero de clientes que quiera usar: ");
		numClientes = input.nextInt();
		System.out.println("Has puesto " + numClientes + " clientes\n");

		Cliente[] clientes = new Cliente[numClientes];
		
		for (int i = 0; i < numClientes; i++) {
			cliente = new Cliente();
			clientes[i] = cliente;
		}
		
		for (int i = 0; i < cajas.length; i++) {
			cajas[i].start();
		}

		for (int i = 0; i < numClientes; i++) {
			cola.añadirFinal(clientes[i]);
			synchronized (cola) {
				cola.notify();
			}
			System.out.println("Añadiendo");		
		}
		
		//Cola cola = new Cola();

        /*con join dices q quieres q el hilo principal espere a q finalicen los hilos, osea q se quede detrás de ese hilo, más tarde
el hilo principal está esperando a q acabe el hilo X, y luego cuando el hilo X finalice, el hilo principal continuara su
ejecucion. Join tiene q estar dentro de un try catch. 
*/
    	for (int i = 0; i < cajas.length; i++) {
    		try {
				cajas[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    	System.out.println("El saldo total es "+contabilidad.dameSaldo());

    	/*System.out.println(cola.tamañoMaximo());
    	System.out.println(cola.tamañoActual());
    	Cliente[] array = cola.array();
    	for (Cliente cliente : array) {
    		System.out.println(cliente.dameNombre() + " " + cliente.damePrecioCarro());
		}
    	cola.sacar();
    	System.out.println(cola.tamañoActual());
    	System.out.println(cola.tamañoMaximo());
    	cola.sacar();
    	cola.sacar();
    	array = cola.array();
    	for (Cliente cliente : array) {
    		System.out.println(cliente.dameNombre() + " " + cliente.damePrecioCarro());
		}
    	cola.sacar();
    	cola.sacar();

    	cola.sacar();*/
	}
}

/*Indeterminismo: cuando dos o + hilos están escribiendo en una var compartida, el valor es indeterminado.
Exclusión mutua: solo puedo acceder a la var compartidad un hilo a la vez
Sección crítica: aquella parte del código en el q se va a producir indeterminismo, en la q la
var compartida esta siendo accedida por varios hilos
*/
