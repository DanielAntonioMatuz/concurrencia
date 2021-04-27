import java.util.concurrent.Semaphore;

public class Productor implements Runnable{

    Semaphore libres, S, ocupados;

    public Productor(Semaphore libres, Semaphore S, Semaphore ocupados) {
        this.libres = libres;
        this.S = S;
        this.ocupados = ocupados;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2500);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("PRODUCIENDO DATOS.......");

            try {
                libres.acquire();
                S.acquire();
                System.out.println("AGREGANDO DATOS....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            S.release();
            ocupados.release();

        }
    }
}
