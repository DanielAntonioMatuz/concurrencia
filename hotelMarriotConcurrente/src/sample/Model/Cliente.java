package sample.Model;

import java.util.Observable;

public class Cliente extends Observable implements Runnable {

    int contador;
    int ejeX;
    int ejeY;
    int idMesa;
    boolean ejeYN;

    public Cliente(int x, int y, int idMesa) {
        contador = 0;
        this.ejeX = x;
        this.ejeY = y;
        ejeYN = false;
        this.idMesa = idMesa;
    }

    @Override
    public void run() {
        while (true) {
            try {

                setChanged();
                notifyObservers(Thread.currentThread().getName());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public int getEjeX() {
        return ejeX;
    }

    public void setEjeX(int ejeX) {
        this.ejeX = ejeX;
    }

    public int getEjeY() {
        return ejeY;
    }

    public void setEjeY(int ejeY) {
        this.ejeY = ejeY;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }
}
