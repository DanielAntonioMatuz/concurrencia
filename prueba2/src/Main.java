import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore mutex = new Semaphore(0);
        Imprimir imprimir = new Imprimir();

        HiloUno uno = new HiloUno(imprimir, mutex);
        HiloDos dos = new HiloDos(imprimir, mutex);

        uno.start();
        dos.start();
    }
}
