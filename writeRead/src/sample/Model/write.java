package sample.Model;

import java.util.Observable;
import java.util.concurrent.Semaphore;

public class write extends Observable implements Runnable {

    //private Semaphore access_write;
    private dataBase db;
    private monitor monitor;
    //private Semaphore lleno;
    private String name;

    public write(monitor monitor, dataBase db, String id) {
        //this.access_write = access_write;
        /*this.db = db;
        //this.lleno = lleno;
        this.name = id;*/
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while (true) {
            monitor.escribir();
        }
    }

    public String getName() {
        return name;
    }
}
