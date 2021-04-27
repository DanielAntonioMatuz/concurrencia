import java.util.concurrent.Semaphore;

public class main {

    public static void main(String[] args) {
        Semaphore libres = new Semaphore(10);
        Semaphore ocupados = new Semaphore(0);
        Semaphore S = new Semaphore(1);

        Thread hiloConsumidor, hiloProductor;
        Consumidor consumidor;
        Productor productor;

        consumidor = new Consumidor(ocupados, libres, S);
        hiloConsumidor = new Thread(consumidor);
        hiloConsumidor.start();

        productor = new Productor(libres, S, ocupados);
        hiloProductor = new Thread(productor);
        hiloProductor.start();

    }
}
