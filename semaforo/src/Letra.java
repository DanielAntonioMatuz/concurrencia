public class Letra {
    private String argumento;
    public String [] data;

    public synchronized void letras(String letra) {
        argumento = letra;
        data = argumento.split(",");
    }

    public String getLetra() {
        System.out.println(data[0]);
        System.out.println(data[1]);
        return argumento;
    }
}
