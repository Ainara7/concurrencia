package hipermercado;

//para crear un hilo hay q extender de Thread, y sobreescribir el metodo run
class Caja extends Thread{
    
    private Cola cola;
    private Contabilidad conta;
    private static int id = 0;
    private int idd;
    private double saldo = 0;
    
    public Caja(Cola cola, Contabilidad contabilidad){
    
        this.cola = cola;
        this.conta = contabilidad;
        idd = id++;
        
    }
    
    public void run(){
        
        Cliente cliente = cola.sacar();

        while(cola.tamaño() > 0 && cliente != null){
            try{

                Thread.sleep( ((long)cliente.damePrecioCarro() / 10) * 1000 );
                System.out.println(cliente.dameNombre() + "fue atendido en CAJA " + idd + " y gasto " + cliente.damePrecioCarro() + "€");
                saldo = saldo + cliente.damePrecioCarro();
                conta.añadeSaldo(saldo);
                cliente = cola.sacar();

            }catch(Exception e){}
        }
        
        if(cliente == null){
            System.out.println("Caja con ID: " + idd + " ha cerrado y obtenido " + saldo + "€");
            Thread.interrupted();
            return; 
        }
        
    }
    
    public int getIdd() {
        return id;
    }
    
}
