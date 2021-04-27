package sample.Model;

import java.util.Random;

public class Monitor {

    int count;
    int [] buffer;
    Random random;
    int elementoBuffer;

    public Monitor(int count) {
        this.count = count;
        buffer = new int[count];
        random = new Random(System.currentTimeMillis());
        elementoBuffer = 0;
    }

    public void producir() {
        int valor;
        valor = random.nextInt(100) + 1;

        // Guarda booleana
        synchronized (this) {
            while ( elementoBuffer == count ) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            elementoBuffer++;
            int indice = 0;

            while(buffer[indice] != 0) {
                indice++;
            }
            buffer[indice] = valor;
            notify();
        }
    }

    public void consumidor() {

       synchronized (this) {
           while (elementoBuffer == 0) {
               try {
                   wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }

           elementoBuffer--;
           int indice = 0;
           while (buffer[indice] == 0) {
               indice++;
           }
           buffer[indice] = 0;
           notify();
       }
    }
}
