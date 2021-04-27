import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        char[] entrada = {'c', 'a', 's', 'm', 'i', 'o', 'n'};
        Scanner dato = new Scanner(System.in);
        String palabra = "";
        while (!palabra.equals("0")) {
            System.out.println("Ingrese una palabra (Permitidas: casa, camion, cama), ingrese 0 para terminar");
            palabra = dato.next();

            ValuerCase(entrada, palabra);
        }
    }


    static void ValuerCase(char[] entrada, String palabra) {
        boolean fin = false;
        int contador = 0;
        int contadorFinal = 0;
        int estadoInicio = 0;
        int[] estadoFinal = {5, 8};
        int estadoActual = estadoInicio;
        int contadorCasos = 0;

        try {
            while (fin == false) {

                if (contador > palabra.length() - 1) {
                    fin = true;
                    break;
                }


                if (estadoActual == 0) {
                    //System.out.println("A1");
                    if (entrada[contador] == palabra.charAt(contador)) {
                        estadoActual = 1;
                        //System.out.println("A2");
                    }
                    contador++;
                    continue;
                }

                if (estadoActual == 1) {
                    //System.out.println("A3");

                    if (entrada[contador] == palabra.charAt(contador)) {
                        estadoActual = 2;
                        //System.out.println("A4");

                    }
                    contador++;
                    continue;
                }

                if (estadoActual == 2) {
                    //System.out.println("A5");
                    contadorCasos++;
                    if (entrada[2] == palabra.charAt(contador) && contadorCasos == 1 || entrada[3] == palabra.charAt(contador) && contadorCasos == 1) {
                        //System.out.println("A6");
                        if (palabra.charAt(contador) == entrada[3]) {
                            //System.out.println("A7");
                            estadoActual = 3;
                        } else {
                            if (palabra.charAt(contador) == entrada[2]) {
                                //System.out.println("A7");
                                estadoActual = 4;
                                contadorFinal = 1;

                            }
                        }
                    } else {
                        contador = palabra.length();
                    }
                    contador++;
                    continue;
                }

                if (estadoActual == 4) {
                    //System.out.println("A8");

                    if (entrada[contadorFinal] == palabra.charAt(contador) && palabra.length() - 1 == contador) {
                        //System.out.println("A9");

                        estadoActual = 5;
                    } else {
                        estadoActual = 4;
                        contador++;
                        //System.out.println("A11");
                    }
                    contador = palabra.length();
                    continue;
                }

                if (estadoActual == 3) {
                    //System.out.println("A12");

                    if (palabra.charAt(contador) == entrada[1] && palabra.length() - 1 == contador) {
                        //System.out.println("A13");

                        estadoActual = 5;
                        contadorFinal = 1;
                    } else {
                        //System.out.println("A14");
                        if (palabra.charAt(contador) == entrada[4]) {
                            estadoActual = 6;
                            //System.out.println("A15");

                        } else {
                            contador = palabra.length();
                            //System.out.println("A16");
                        }
                    }
                    contador++;
                    continue;
                }

                contadorCasos = 0;
                if (estadoActual == 6) {
                    //System.out.println("A17");
                    contadorCasos++;
                    if (entrada[5] == palabra.charAt(contador) && contadorCasos == 1) {
                        //System.out.println("A18");

                        estadoActual = 7;

                    } else {
                        contador = palabra.length();
                    }
                    contador++;
                    continue;
                }

                if (estadoActual == 7) {
                    //System.out.println("A19");

                    if (entrada[6] == palabra.charAt(contador) && palabra.length() - 1 == contador) {
                        //System.out.println("A20");

                        estadoActual = 8;

                    }
                    contador = palabra.length();
                    continue;
                }

            }
        } catch (Exception e) {
            System.out.println("No procesable");
        }

        if (estadoActual == estadoFinal[0] || estadoActual == estadoFinal[1]) {
            System.out.println("La cadena es correcta");
        } else {
            if (!palabra.equals("0")){
                System.out.println("La cadena es incorrecta");
            } else {
                System.out.println("Fin del proceso");
            }
        }
    }

}


