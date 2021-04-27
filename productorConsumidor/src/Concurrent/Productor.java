package Concurrent;

import Model.Buffer;
import Model.File;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Productor implements Runnable {

    private Buffer buffer;
    private Random random;
    private Semaphore mutex;
    private Semaphore lleno;
    private Semaphore vacio;


    public Productor(Buffer buffer, Semaphore mutex,Semaphore lleno,Semaphore vacio) {
        this.buffer = buffer;
        this.random = new Random(System.currentTimeMillis());
        this.mutex = mutex;
        this.lleno = lleno;
        this.vacio = vacio;
    }

    @Override
    public void run() {
        while (true) {
            String name = String.valueOf((char) (random.nextInt(26) + 65));

            try {
                vacio.acquire();
                mutex.acquire();
                buffer.addFile(new File(name, 1.0F));
            } catch (Exception e) { }
            mutex.release();
            lleno.release();

            System.out.println( Thread.currentThread().getName() + buffer.toString());
            try {
                Thread.sleep(random.nextInt(500) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
