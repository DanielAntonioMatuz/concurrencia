public class Main {
    public static void main(String[] args) {

        automata();
    }

    public static void automata(){
        char [] vocabulario = {'a','b','c','+','-'};
        int estadoInicial = 0;
        int estadoFinal = 7;
        int estadoActual = estadoInicial;
        boolean fin = false;
        int contador = 0;
        int val = contador;
        String entrada = "aa-b+c";

        while (!fin) {
            val = contador;

            if (contador > entrada.length() - 1){
                fin = true;
                break;
            }

            if (estadoActual == 0){
                System.out.println(entrada.charAt(contador) + "entrada 1");

                if(entrada.charAt(contador) == vocabulario[0] && entrada.charAt(val + 1) != entrada.charAt(contador)){
                    estadoActual = 1;
                } else {
                    contador = entrada.length();
                }
                contador++;
            }

            if (estadoActual == 1){
                System.out.println(entrada.charAt(contador) + "entrada 2");
                if(entrada.charAt(contador) == vocabulario[3]){
                    estadoActual = 2;
                } else if(entrada.charAt(contador) == vocabulario[4]){
                    estadoActual = 3;
                }
                contador++;
            }

            if (estadoActual == 2){
                System.out.println(entrada.charAt(contador) + "entrada 3");
                if(entrada.charAt(contador) == vocabulario[1]){
                    estadoActual = 4;
                }
                contador++;
            }

            if (estadoActual == 3){
                System.out.println(entrada.charAt(contador) + "entrada 4");
                if(entrada.charAt(contador) == vocabulario[1]){
                    estadoActual = 4;
                    System.out.println("--");
                }
                contador++;
            }


            if (estadoActual == 4){
                System.out.println(entrada.charAt(contador) + "entrada 5");
                if(entrada.charAt(contador) == vocabulario[3]){
                    estadoActual = 5;
                } else if(entrada.charAt(contador) == vocabulario[4]){
                    estadoActual = 6;
                    System.out.println("A");
                }
                contador++;
            }

            if (estadoActual == 5){
                System.out.println(entrada.charAt(contador) + "entrada 6");
                if(entrada.charAt(contador) == vocabulario[2]){
                    estadoActual = 7;
                }
                contador++;
            }

            if (estadoActual == 6){
                System.out.println(entrada.charAt(contador) + "entrada 7");
                if(entrada.charAt(contador) == vocabulario[2]){
                    estadoActual = 7;
                    System.out.println("C7");
                }
                contador++;
            }

        }

        if (estadoActual == estadoFinal){
            System.out.println("La cadena es correcta " + entrada);
        } else {
            System.out.println("La cadena es incorrecta " + entrada);
        }

    }
}
