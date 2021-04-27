import java.util.concurrent.Semaphore;

public class HiloDos extends Thread {
    private Letra let;
    String argumento;
    Semaphore mutex;

    public HiloDos(String args, Letra letra, String name, Semaphore mutex) {
        super(name);
        this.let = letra;
        argumento = args;
        this.mutex = mutex;
    }


    @Override
    public void run() {
        while (true) {

            System.out.println(argumento);
            try {
                mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                mutex.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("d");
/*            try {
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
