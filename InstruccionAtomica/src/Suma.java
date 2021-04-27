public class Suma {
    private long contador = 0;

    public synchronized void sumar(){
        for (int i=1;i<=100000;i++){
            contador++;
        }
    }

    public long getContador(){
        return contador;
    }
}
