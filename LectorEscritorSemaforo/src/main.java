import java.util.concurrent.Semaphore;

public class main {

    public static void main(String[] args) {

        Semaphore wrt = new Semaphore(1);
        Semaphore S = new Semaphore(1);
        int accesoLectores = 0;
        Thread hilo, hilo2;
        Lector lector;
        Escritor escritor;

        lector = new Lector(S, wrt, accesoLectores);
        hilo = new Thread(lector);
        hilo.start();

        escritor = new Escritor(wrt);
        hilo2 = new Thread(escritor);
        hilo2.start();

    }
}
