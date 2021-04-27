import java.util.concurrent.Semaphore;

public class HiloTres extends Thread {
    private Letra let;
    String argumento;
    Semaphore mutex;

    public HiloTres(String args, Letra letra, String name, Semaphore mutex) {
        super(name);
        this.let = letra;
        argumento = args;
        this.mutex = mutex;
    }


    @Override
    public void run() {
        while (true) {
            System.out.println(argumento);
            mutex.release();
            System.out.println("f");
           /* try {
                mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
