package sample.Model;

public class Consumidor implements Runnable {
    Monitor monitor;

    public Consumidor(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("Con");
            monitor.consumidor();
        }
    }
}
