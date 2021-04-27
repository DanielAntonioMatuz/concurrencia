package sample;

public class controllerPila {


    public static boolean validarExpersion(String valor) {
        Pila pila = new Pila();
        String cadena = valor;

        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == '(' || cadena.charAt(i) == '[' || cadena.charAt(i) == '{') {

                pila.Insertar(cadena.charAt(i));

            } else {

                if (cadena.charAt(i) == ')') {

                    if (pila.extraer() != '(') {
                        return false;
                    }

                } else {

                    if (cadena.charAt(i) == ']') {

                        if (pila.extraer() != '[') {
                            return false;
                        }

                    } else {

                        if (cadena.charAt(i) == '}') {

                            if (pila.extraer() != '{') {
                                return false;
                            }

                        }

                    }

                }

            }
        }
        return pila.PilaVacia();
    }

}
