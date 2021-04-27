import java.util.concurrent.Semaphore;

public class HiloDos extends Thread {
    Imprimir imprimir;
    Semaphore mutex;

    public HiloDos(Imprimir imprimir, Semaphore mutex) {
        this.imprimir = imprimir;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("C");
        System.out.println("D");;
    }

}
