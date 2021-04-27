import java.util.concurrent.Semaphore;

public class Consumidor implements Runnable {

    Semaphore ocupados, libres, S;

    public Consumidor(Semaphore ocupados, Semaphore libres, Semaphore S) {
        this.ocupados = ocupados;
        this.libres = libres;
        this.S = S;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2500);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                ocupados.acquire();
                S.acquire();
                System.out.println(" ");
                System.out.println("RECOLECTANDO DATOS....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            S.release();
            libres.release();
            System.out.println("CONSUMIENDO DATOS..");
        }
    }
}
