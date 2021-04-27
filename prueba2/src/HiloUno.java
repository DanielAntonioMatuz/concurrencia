import java.util.concurrent.Semaphore;

public class HiloUno extends Thread {
    Imprimir imprimir;
    Semaphore mutex;

    public HiloUno(Imprimir imprimir, Semaphore mutex) {
        this.imprimir = imprimir;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        System.out.println("A");
        mutex.release();
        System.out.println("B");
    }

}
