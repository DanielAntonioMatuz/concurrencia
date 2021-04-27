public class Letra {
    private String argumento;

    public synchronized void letras(String letra) {
        argumento = letra;
    }

    public String getLetra() {
        System.out.println(argumento);
        return argumento;
    }
}
