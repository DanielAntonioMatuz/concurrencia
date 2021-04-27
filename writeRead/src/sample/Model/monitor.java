package sample.Model;

import java.util.Observable;

public class monitor extends Observable {
    dataBase db;
    int count;
    int elementoEscritor;

    public monitor(int count, int elementoEscritor) {
        this.count = count;
        this.elementoEscritor = elementoEscritor;
    }

    public void escribir () {
        setChanged();
        notifyObservers(Thread.currentThread().getName());

        db.insert("dato");
        //  lleno.release();
        System.out.println(db.toString());
    }
}
