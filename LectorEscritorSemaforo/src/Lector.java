import java.util.concurrent.Semaphore;

public class Lector implements Runnable {

    Semaphore S, wrt;
    int accesoLectores;

    public Lector (Semaphore S, Semaphore wrt, int accesoLectores) {
        this.S = S;
        this.wrt = wrt;
        this.accesoLectores = accesoLectores;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            accesoLectores++;
            if (accesoLectores == 1) {
                try {
                    wrt.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            S.release();

            System.out.println("LEYENDO..... LEYENDO...");
            System.out.println(" ");

            try {
                S.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            accesoLectores--;

            if (accesoLectores == 0) {
                wrt.release();
            }
            S.release();
        }
    }
}
