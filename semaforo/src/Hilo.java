import java.util.concurrent.Semaphore;

public class Hilo extends Thread {
    private Letra let;
    Semaphore mutex;
    String argumento;
    public String [] data;

    public Hilo(String args, Letra letra, String name, Semaphore mutex) {
        super(name);
        this.let = letra;
        argumento = args;
        this.mutex = mutex;
    }


    @Override
    public void run() {
        data = argumento.split(",");
        if (data[0].equals("C")) {
            System.out.println(data[0]);
            try {
                mutex.acquire(); //wait(s)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(data[1]);
        }

        if (data[0].equals("A")) {
            System.out.println(data[0]);
            mutex.release(); //wait(s)
            System.out.println(data[1]);
        }
        /*while(true) {
            let.letras(argumento);
            let.getLetra();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

}
