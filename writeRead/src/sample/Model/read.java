package sample.Model;

import java.util.Observable;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class read extends Observable implements Runnable {

    private static Semaphore mutex = new Semaphore(1);
    private Semaphore access_write;
    private Semaphore lleno;
    private static int numeroLectores = 0;
    private dataBase db;
    private Random random;
    private String name;

    public read(Semaphore access_write, Semaphore lleno, dataBase db, String id) {
        this.db = db;
       /* this.access_write = access_write;
        this.lleno = lleno;*/
        random = new Random();
        this.name = id;
    }

    @Override
    public void run() {
        /*try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        numeroLectores++;
        if (numeroLectores == 1) {
            /*try {
                lleno.acquire();
                access_write.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        //mutex.release();
        try {
            Thread.sleep(random.nextInt(5000) + 5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setChanged();
        notifyObservers(Thread.currentThread().getName());



        db.read();
        //System.out.println("L" + Thread.currentThread().getName());

        /*try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        numeroLectores--;

        if (numeroLectores == 0) {
           // access_write.release();
        }
       // mutex.release();
    }

    public String getName() {
        return name;
    }
}
