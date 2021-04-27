package Concurrent;

import Model.Buffer;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Consumidor implements Runnable {
    Buffer buffer;
    private Random random;
    private Semaphore mutex;
    private Semaphore lleno;
    private Semaphore vacio;

    public Consumidor (Buffer buffer, Semaphore mutex,Semaphore lleno,Semaphore vacio) {
        this.buffer = buffer;
        this.random = new Random(System.currentTimeMillis());
        this.mutex = mutex;
        this.lleno = lleno;
        this.vacio = vacio;
    }

    @Override
    public void run() {
        while(true) {
            try {
                lleno.acquire();
                mutex.acquire();
                buffer.deleteFile();
            } catch (Exception e) { }
            mutex.release();
            vacio.release();

            System.out.println( Thread.currentThread().getName() + buffer.toString());

            try {
                Thread.sleep(random.nextInt(500) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
