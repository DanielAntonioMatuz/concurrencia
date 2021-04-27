package root;

import java.util.Observable;

public class Hilo extends Observable implements Runnable {
    private int turno;

    public Hilo(){
        turno = 1;
    }

    @Override
    public void run() {
        // Comunicarse con la vista
        // java rmi
        while (true) {
            this.setChanged();
            this.notifyObservers(String.valueOf(turno));

            System.out.println(turno);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (turno == 3){
                turno = 1;
            } else {
                turno++;
            }
        }
    }
}
