import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Letra let1 = new Letra();
        Letra let2 = new Letra();

        Semaphore mutex = new Semaphore(0);

        Hilo A = new Hilo("A,B", let1, "Hilo A", mutex);
        Hilo B = new Hilo("C,D", let2, "Hilo B", mutex);



        A.setDaemon(true);
        B.setDaemon(true);

        A.start();
        B.start();

        System.out.println(Thread.currentThread().getName());
    }

}
