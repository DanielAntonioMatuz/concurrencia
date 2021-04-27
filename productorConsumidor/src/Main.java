import Concurrent.Consumidor;
import Concurrent.Productor;
import Model.Buffer;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Semaphore mutex = new Semaphore(1);
        Semaphore lleno = new Semaphore(0);
        Semaphore vacio = new Semaphore(10);

        Productor productor = new Productor(buffer, mutex, lleno, vacio);
        Consumidor consumidor = new Consumidor(buffer, mutex, lleno, vacio);

        Thread hilo1 = new Thread(productor);
        Thread hilo2 = new Thread (consumidor);
        hilo1.setName("P:");
        hilo2.setName("C:");
/*
        hilo1.setDaemon(true);
        hilo2.setDaemon(true);
*/

        hilo1.start();
        hilo2.start();
    }
}
