package sample.Model;

public class Productor implements Runnable {

    Monitor monitor;

    public Productor(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("Pro");
            monitor.producir();
        }
    }
}
