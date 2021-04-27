public class Main {

    public static void main(String[] args) {
        Suma suma = new Suma();
        Hilo A = new Hilo(suma, "Hilo A");
        Hilo B = new Hilo(suma, "Hilo B");

        try {
            A.start();
            B.start();

            A.join();
            B.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName());
        System.out.println(suma.getContador());
    }

}
