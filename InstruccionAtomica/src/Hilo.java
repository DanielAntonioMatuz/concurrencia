public class Hilo extends Thread {

    private Suma suma;

    public Hilo(Suma suma, String name){
        super(name);
        this.suma = suma;
    }

    @Override
    public void run() {
        suma.sumar();
        System.out.println(Thread.currentThread().getName() + " Termino");
    }
}
