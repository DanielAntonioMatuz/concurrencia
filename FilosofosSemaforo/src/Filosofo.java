import java.util.concurrent.Semaphore;

public class Filosofo implements Runnable {

    public int identidad, izq, der;
    Semaphore[] tenedores;
    Semaphore comedor;

    public Filosofo(int identidad, Semaphore comedor, Semaphore[] tenedores) {
        this.identidad = identidad;
        this.izq = identidad;
        this.der = (identidad + 1) % 5;
        this.comedor = comedor;
        this.tenedores = tenedores;
    }


    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("PENSANDO " + identidad);
            try {
                comedor.acquire();
                tenedores[izq].acquire();
                tenedores[der].acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("COMIENDO " + identidad + " tenedores en uso: " + izq + " (IZQUIERDO) y: " + der + " (DERECHO) ");
            System.out.println(" ");
            System.out.println("_______________________");
            tenedores[der].release();
            tenedores[izq].release();
            comedor.release();
        }
    }
}
