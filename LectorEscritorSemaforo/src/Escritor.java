import java.util.concurrent.Semaphore;

public class Escritor implements Runnable {

    Semaphore wrt;

    public Escritor(Semaphore wrt) {
        this.wrt = wrt;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                wrt.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("ESCRIBIENDO.... ESCRIBIENDO...");
            System.out.println(" ");

            wrt.release();
        }
    }
}
