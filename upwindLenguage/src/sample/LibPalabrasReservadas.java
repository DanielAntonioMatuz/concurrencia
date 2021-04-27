package sample;

//TODO: ACTUALIZAR CLASE
public class LibPalabrasReservadas {
    public static boolean validarPalabra(String cadena) {
        return cadena.equals("args") || cadena.equals("body") || cadena.equals("case") || cadena.equals("catch") ||
                cadena.equals("char") || cadena.equals("displayEnter") || cadena.equals("displayView") ||
                cadena.equals("else") || cadena.equals("final") || cadena.equals("float") || cadena.equals("for") ||
                cadena.equals("if") || cadena.equals("mult") || cadena.equals("rest") || cadena.equals("run") ||
                cadena.equals("string") || cadena.equals("sum") || cadena.equals("try") || cadena.equals("int") ||
                cadena.equals("boolean");
    }

    public static String regresarSignificado(String cadena) {
        switch (cadena) {
            case "args":
            case "string":
            case "char":
            case "boolean":
                return "Tipo de dato";
            case "int":
                return "Tipo de dato entero";
            case "float":
                return "Tipo de dato númerico";
            case "body":
                return "Funcion principal";
            case "case":
                return "Funcion de caso";
            case "displayEnter":
                return "Función de ingreso de datos en consola";
            case "displayView":
                return "Función de salida de datos a consola";
            case "else":
                return "Funcion de decisión";
            case "if":
            case "for":
                return "Estructura de control";
            case "run":
                return "Función de ejecutar programa";
            case "sum":
            case "mult":
            case "rest":
                return "Función matemática";
            case "try":
            case "catch":
                return "Funcion de corrección de error";
            case "final":
                return "Restricción de datos";
            default:
                return cadena;
        }
    }

    public static Boolean regresarValidacion(String cadena) {
        switch (cadena) {
            case "args":
            case "string":
            case "char":
            case "float":
            case "body":
            case "case":
            case "displayEnter":
            case "displayView":
            case "else":
            case "if":
            case "for":
            case "run":
            case "sum":
            case "mult":
            case "rest":
            case "try":
            case "catch":
            case "final":
                return true;
            default:
                return false;
        }
    }
}
