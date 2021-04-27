public class EjemploLyA_AFD_1 {
    public static void main(String[] args) {
        int[] entrada = {1, 0, 0, 1};
        int estadoInicio = 0;
        int estadoFinal = 0;
        int estadoActual = estadoInicio;

        boolean fin = false;

        int contador = 0;

        while (fin == false) {
            if (contador > entrada.length - 1) {
                fin = true;
                break;
            }

            if (estadoActual == 0) {
                if (entrada[contador] == 1) {
                    estadoActual = 0;
                }

                if (entrada[contador] == 0) {
                    estadoActual = 1;
                }

                contador++;
                continue;
            }

            if (estadoActual == 1) {
                if (entrada[contador] == 1) {
                    estadoActual = 1;
                }

                if (entrada[contador] == 0) {
                    estadoActual = 0;
                }

                contador++;
                continue;
            }

        }
        if (estadoActual == estadoFinal) {
            System.out.println("La cadena es correcta ");
        } else {
            System.out.println("La cadena es incorrecta");
        }
    }


}
