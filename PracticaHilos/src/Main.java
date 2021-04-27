import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Letra let = new Letra();
        Semaphore mutex = new Semaphore(0);

        HiloUno A = new HiloUno("a", let, "Hilo A", mutex);
        HiloDos B = new HiloDos("c", let, "Hilo B", mutex);
        HiloTres C = new HiloTres("e", let, "Hilo C", mutex);


       /* A.setDaemon(true);
        B.setDaemon(true);
        C.setDaemon(true);*/

        A.start();
        B.start();
        C.start();

        /*System.out.println(Thread.currentThread().getName());*/
    }

}
