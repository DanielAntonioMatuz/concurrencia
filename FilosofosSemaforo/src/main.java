import java.util.concurrent.Semaphore;

public class main {
    public static void main(String[] args) {
        Semaphore comedor = new Semaphore(5);
        Semaphore[] tenedores = new Semaphore[5];
        Filosofo filosofo;
        Thread hilo;

        for (int i = 0; i < 5; i++) {
            tenedores[i] = new Semaphore(1);
        }

        for (int i = 0; i < 5; i++) {
            filosofo = new Filosofo(i,comedor, tenedores);
            hilo = new Thread(filosofo);
            hilo.start();
        }
    }
}
