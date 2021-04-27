package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static sample.LibPalabrasReservadas.*;
import static sample.ValueDefinitionsVariable.verificadorArgumento;
import static sample.ValueDefinitionsVariable.verificarIdentificador;

public class controllerAutSyntactic {
    public static boolean fin = false;
    public static int contador = 0;
    public static int estadoActual = 0;
    public static int estadoInicio = estadoActual;
    public static String entrada, salida;
    public static boolean aceptado = false;


    public static boolean automateValidVariables( String dataEnter) {

        int[] estadoFinal = {5};
        boolean access = true;
        String exitErr;


        entrada = dataEnter;
        exitErr = null;

        //String[] arrayEntrada = entrada.split(" ");
        String[] arrayEntrada = analitedData(entrada, 10);

        int valC = 0;
        for (int i = 0; i < arrayEntrada.length; i++) {
            if (arrayEntrada[i] != null) {
                valC++;
            }
        }

        if (valC > 5) {
            access = false;
        }

        while (!fin) {

            if (contador > entrada.length() - 1) {
                fin = true;
                break;
            }
            System.out.println("1 " + contador + " " + estadoActual);
            try {
                if (estadoActual == 0) {
                    contador++;
                    if (arrayEntrada[0].equals("args") || arrayEntrada[0].equals("float") || arrayEntrada[0].equals("char")) {

                        estadoActual = 1;
                    } else {
                        aceptado = false;
                        break;
                    }
                    continue;
                }

                if (estadoActual == 1) {
                    contador++;
                    if (verificarIdentificador(arrayEntrada[1])) {
                        if (verificadorArgumento(arrayEntrada[1])) {
                            access = false;
                            try {
                                Double data  = Double.parseDouble(arrayEntrada[1]);
                                data = 0.0;
                                if (data == 0.0) {
                                    access = true;
                                }

                            } catch (Exception e) {
                                if (!access) {
                                    Pattern regexAlpha = Pattern.compile("[.]|[#]|[!]|[$]|[%]|[*]|[+]|[?]|[~]|[`]|[}]|[{]|[,]|[>]|[<]|[&]|[|]|[]]|[@]|^]|[¡]");
                                    Matcher regexData = regexAlpha.matcher(arrayEntrada[1]);
                                    if (regexData.find() && arrayEntrada[1].charAt(0) != '\"') {
                                        arrayEntrada[1] = "Error, no permitible conjugación de simbolos - palabras";
                                        aceptado = false;
                                    } else {
                                        //cadena = "Texto";
                                    }
                                }
                            }

                        } else {
                            arrayEntrada[1] = "Identificador inválido";
                            aceptado = false;
                            break;
                        }

                    } else if (!verificarIdentificador(arrayEntrada[1])) {
                        estadoActual = 2;
                    }
                    continue;
                }

                if (estadoActual == 2) {
                    contador++;
                    if (arrayEntrada[2].equals(";")) {
                        estadoActual = 5;
                    } else {
                        if (arrayEntrada[2].equals("=")) {
                            estadoActual = 3;
                        } else {
                            aceptado = false;
                            break;
                        }
                    }

                    continue;
                }

                if (estadoActual == 3) {
                    contador++;

                    if (arrayEntrada[3] != null || !arrayEntrada[3].equals(";")) {
                        estadoActual = 4;
                    } else {
                        break;
                    }

                    continue;
                }

                if (estadoActual == 4) {
                    contador++;
                    if (arrayEntrada[4].equals(";")) {
                        estadoActual = 5;
                    } else {
                        aceptado = false;
                        break;

                    }
                    continue;
                }

                if (estadoActual == 5) {
                    break;
                }

            } catch (Exception e) {
            }
        }

        if (estadoActual == estadoFinal[0] && access) {
            aceptado = true;
            ValueDefinitionsVariable.addVariablesUser(arrayEntrada[1]);
        }

        reitinialized();

        access = true;


        return aceptado;

    }

    public static String[] analitedData(String dataEnter, int value) {
        List<String> matchList = new ArrayList<String>();
        Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
        Matcher regexMatcher = regex.matcher(dataEnter);


        entrada = dataEnter;

        //String[] arrayEntrada = entrada.split(" ");
        String[] arrayEntrada = new String[10000];
        int contador = 0;
        while (regexMatcher.find()) {

            String cadena = regexMatcher.group();
            arrayEntrada[contador] = cadena;
            contador++;

        }

        return arrayEntrada;
    }

  /*  public static String[] analitedData(String dataEnter, int data) {
        return  dataEnter.trim().split("\\s+");
    }*/

    public static boolean automateValidFunctionDisplay( String dataEnter) {
        int[] estadoFinal = {5};
        boolean access = true;
        String exitErr;


        entrada = dataEnter;
        exitErr = null;

        //String[] arrayEntrada = entrada.split(" ");
        String[] arrayEntrada = analitedData(entrada, 10);

        int valC = 0;
        for (int i = 0; i < arrayEntrada.length; i++) {
            if (arrayEntrada[i] != null) {
                valC++;
            }
        }

        if (valC > 5) {
            access = false;
        }

        while (!fin) {
            System.out.println("2");

            if (contador > entrada.length() - 1) {
                fin = true;
                break;
            }

            try {
                if (estadoActual == 0) {
                    contador++;
                    if (arrayEntrada[0].equals("displayView")) {
                        estadoActual = 1;
                    } else {
                        aceptado = false;
                        break;
                    }
                    continue;
                }

                if (estadoActual == 1) {
                    contador++;

                    if (arrayEntrada[1].equals("(")) {
                        estadoActual = 2;
                    } else {
                        aceptado = false;
                        break;
                    }
                    continue;
                }

                if (estadoActual == 2) {
                    contador++;

                    if (ValueDefinitionsVariable.compareVariablesUser(arrayEntrada[2])) {
                        estadoActual = 3;
                    } else {
                        estadoActual = 3;
                    }

                    continue;
                }

                if (estadoActual == 3) {
                    contador++;

                    if (arrayEntrada[3].equals(")")) {
                        estadoActual = 4;
                    } else {
                        break;
                    }

                    continue;
                }

                if (estadoActual == 4) {
                    contador++;
                    if (arrayEntrada[4].equals(";")) {
                        estadoActual = 5;
                    } else {
                        aceptado = false;
                        break;
                    }
                    continue;
                }

                if (estadoActual == 5) {
                    break;
                }

            } catch (Exception e) {
            }
        }

        if (estadoActual == estadoFinal[0] && access) {
            aceptado = true;
        }

        reitinialized();

        access = true;


        return aceptado;

    }

    public static boolean automateValidFunctionMult( String dataEnter) {
        int[] estadoFinal = {9};
        boolean access = true;
        String exitErr;



        entrada = dataEnter;
        exitErr = null;

        //String[] arrayEntrada = entrada.split(" ");
        String[] arrayEntrada = analitedData(entrada, 10);

        int valC = 0;
        for (int i = 0; i < arrayEntrada.length; i++) {
            if (arrayEntrada[i] != null) {
                valC++;
            }
        }

        if (valC > 9) {
            access = false;
        }

        while (!fin) {
            System.out.println("3");

            if (contador > entrada.length() - 1) {
                fin = true;
                break;
            }

            try {
                if (estadoActual == 0) {
                    contador++;
                    if (ValueDefinitionsVariable.compareVariablesUser(arrayEntrada[0])) {
                        estadoActual = 1;
                    } else {
                        aceptado = false;
                    }
                    continue;
                }

                if (estadoActual == 1) {
                    contador++;

                    if (arrayEntrada[1].equals("=")) {
                        estadoActual = 2;
                    } else {
                        aceptado = false;
                    }
                    continue;
                }

                if (estadoActual == 2) {
                    contador++;

                    if (arrayEntrada[2].equals("mult")) {
                        estadoActual = 3;
                    } else {
                        aceptado = false;
                    }
                    continue;
                }

                if (estadoActual == 3) {
                    contador++;

                    if (arrayEntrada[3].equals("(")) {
                        estadoActual = 4;
                    } else {
                        aceptado = false;
                    }
                    continue;
                }

                if (estadoActual == 4) {
                    contador++;

                    if (ValueDefinitionsVariable.compareVariablesUser(arrayEntrada[4]) || validarNumero(arrayEntrada[4])) {
                        estadoActual = 5;
                    }

                    continue;
                }

                if (estadoActual == 5) {
                    contador++;

                    if (arrayEntrada[5].equals(",")) {
                        estadoActual = 6;
                    }

                    continue;
                }

                if (estadoActual == 6) {
                    contador++;

                    if (ValueDefinitionsVariable.compareVariablesUser(arrayEntrada[6]) || validarNumero(arrayEntrada[6])) {
                        estadoActual = 7;
                    }

                    continue;
                }

                if (estadoActual == 7) {
                    contador++;

                    if (arrayEntrada[7].equals(")")) {
                        estadoActual = 8;
                    } else {
                        aceptado = false;
                    }
                    continue;
                }

                if (estadoActual == 8) {
                    contador++;
                    if (arrayEntrada[8].equals(";")) {
                        estadoActual = 9;
                    } else {
                        aceptado = false;
                    }
                    continue;
                }

                if (estadoActual == 9) {
                    break;
                }

            } catch (Exception e) {
            }
        }

        if (estadoActual == estadoFinal[0] && access) {
            aceptado = true;
        }
        reitinialized();
        access = true;

        return aceptado;

    }

    public static boolean createFunction(String dataEnter) {
        int[] estadoFinal = {5};
        boolean access = true;
        String exitErr;


        entrada = dataEnter;
        exitErr = null;

        String[] arrayEntrada = analitedData(entrada, 10);

        int valC = 0;
        for (int i = 0; i < arrayEntrada.length; i++) {
            if (arrayEntrada[i] != null) {
                valC++;
            }
        }

        if (valC > 6) {
            access = false;
        }

        while (!fin) {

            if (contador > entrada.length() - 1) {
                fin = true;
                break;
            }
            System.out.println("AUX " + estadoActual + " C " + contador);
            try {
                if (estadoActual == 0) {
                    contador++;
                    if (verificarIdentificador(arrayEntrada[0])) {
                        if (verificadorArgumento(arrayEntrada[0])) {
                            access = false;
                            try {
                                Double data  = Double.parseDouble(arrayEntrada[1]);
                                data = 0.0;
                                if (data == 0.0) {
                                    access = true;
                                }

                            } catch (Exception e) {
                                if (!access) {
                                    Pattern regexAlpha = Pattern.compile("[.]|[#]|[!]|[$]|[%]|[*]|[+]|[?]|[~]|[`]|[}]|[{]|[,]|[>]|[<]|[&]|[|]|[]]|[@]|^]|[¡]");
                                    Matcher regexData = regexAlpha.matcher(arrayEntrada[1]);
                                    if (regexData.find() && arrayEntrada[0].charAt(0) != '\"') {
                                        arrayEntrada[0] = "Error, no permitible conjugación de simbolos - palabras";
                                        aceptado = false;
                                    } else {
                                        //cadena = "Texto";
                                    }
                                }
                            }

                        } else {
                            arrayEntrada[0] = "Identificador inválido";
                            aceptado = false;
                            break;
                        }

                    } else if (!verificarIdentificador(arrayEntrada[0])) {
                        estadoActual = 1;
                    }
                    continue;
                }

                if (estadoActual == 1) {
                    System.out.println(arrayEntrada[1] + " " + arrayEntrada[2] + " PDA");
                    contador++;
                    if (arrayEntrada[1].equals("(") && arrayEntrada[2].equals(")")) {
                        estadoActual = 4;
                    } else {
                        estadoActual = 2;
                        System.out.println("EA 2");
                    }
                    continue;
                }

                if (estadoActual == 2) {
                    contador++;
                    System.out.println("EAA 2 - A");
                    if (arrayEntrada[2].equals("args") || arrayEntrada[2].equals("float") || arrayEntrada[2].equals("char")) {
                        estadoActual = 6;
                    } else {
                        break;
                    }

                    continue;
                }

                if (estadoActual == 3) {
                    contador++;

                    if (arrayEntrada[4].equals(")")) {
                        estadoActual = 4;
                    } else {
                        break;
                    }

                    continue;
                }

                if (estadoActual == 4) {
                    contador++;
                    if (arrayEntrada[3].equals("{")) {
                        estadoActual = 5;
                    } else {
                        System.out.println(arrayEntrada[5] + " AEL");
                       if (arrayEntrada[5].equals("{")) {
                           estadoActual = 5;
                       } else {
                           break;
                       }
                    }
                    continue;
                }

                if (estadoActual == 5) {
                    break;
                }

                if (estadoActual == 6) {
                    contador++;
                    if (verificarIdentificador(arrayEntrada[3])) {
                        if (verificadorArgumento(arrayEntrada[3])) {
                            access = false;
                            try {
                                Double data  = Double.parseDouble(arrayEntrada[1]);
                                data = 0.0;
                                if (data == 0.0) {
                                    access = true;
                                }

                            } catch (Exception e) {
                                if (!access) {
                                    Pattern regexAlpha = Pattern.compile("[.]|[#]|[!]|[$]|[%]|[*]|[+]|[?]|[~]|[`]|[}]|[{]|[,]|[>]|[<]|[&]|[|]|[]]|[@]|^]|[¡]");
                                    Matcher regexData = regexAlpha.matcher(arrayEntrada[3]);
                                    if (regexData.find() && arrayEntrada[3].charAt(0) != '\"') {
                                        arrayEntrada[3] = "Error, no permitible conjugación de simbolos - palabras";
                                        aceptado = false;
                                    } else {
                                        //cadena = "Texto";
                                    }
                                }
                            }

                        } else {
                            arrayEntrada[3] = "Identificador inválido";
                            aceptado = false;
                            break;
                        }

                    } else if (!verificarIdentificador(arrayEntrada[3])) {
                        estadoActual = 3;
                    }
                    continue;
                }

            } catch (Exception e) {
            }
        }

        if (estadoActual == 5 && access) {
            aceptado = true;
        }

        reitinialized();

        access = true;


        return aceptado;
    }

    //TODO: AGREGAR IF
    public static boolean validarIf(String dataEnter) {
        int estadoActual = 0;
        String[] cadenaArray = dataEnter.trim().split("\\s+");
        Pila pila = new Pila();
        boolean banderaNEG = true;
        boolean banderaOP = true;
        boolean banderaOPB = false;
        loop:
        for (int i = 0; i < cadenaArray.length; i++) {
            String cadena = cadenaArray[i];
//            System.out.println("//" + cadena);
            switch (estadoActual) {
                case 0:
                    if (cadena.equals("if")) {
                        estadoActual = 1;
                    } else {
                        return false;
                    }
                    break;
                case 1:
                    if (cadena.equals("(") && !cadenaArray[i + 1].equals(")")) {
                        estadoActual = 2;
                        pila.Insertar(cadena.charAt(0));
                    } else {
                        return false;
                    }
                    break;
                case 2:
                    switch (cadena) {
                        case "(":
                            pila.Insertar(cadena.charAt(0));
                            banderaNEG = true;
                            banderaOP = true;
                            banderaOPB = false;
                            break;
                        case ")":
                            if (!pila.PilaVacia()) {
                                pila.extraer();
                                banderaOPB = true;
                                banderaNEG = false;
                                banderaOP = false;
                                if (pila.PilaVacia()) {
                                    estadoActual = 3;
                                }
                            }
                            break;
                        default:
                            //Negación
                            if ((cadena.equals("!")) && banderaNEG) {
                                banderaOP = true;
                                banderaOPB = false;
                                banderaNEG = false;
                            } else {
                                //Operador binario
                                if ((cadena.equals("==") || cadena.equals("!=") || cadena.equals("<=") || cadena.equals(">=")
                                        || cadena.equals("||") || cadena.equals("&&") || cadena.equals("-") || cadena.equals("+")
                                        || cadena.equals("*") || cadena.equals("/") || cadena.equals("%") || cadena.equals("<")
                                        || cadena.equals(">")) && banderaOPB) {
                                    banderaOP = true;
                                    banderaNEG = true;
                                    banderaOPB = false;
                                } else {
                                    //Operando
                                    if ((validarNumero(cadena) || validarIdentificador(cadena) || cadena.equals("true")
                                            || cadena.equals("false")) && banderaOP) {
                                        banderaOPB = true;
                                        banderaOP = false;
                                        banderaNEG = false;
                                    }
                                    //Una entrada inválida
                                    else {
                                        return false;
                                    }
                                }
                            }
                            break;
                    }
                    break;
                case 3:
                    if (i == (cadenaArray.length - 1)) {
                        if (cadena.equals("{")) {
                            break loop;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
            }
        }
        return true;
    }

    //TODO: AGREGAR FOR
    public static boolean validarFor(String dataEnter) {
        int estadoActual = 0;
        String[] cadenaArray = dataEnter.trim().split("\\s+");
        Pila pila = new Pila();
        boolean banderaNEG = true;
        boolean banderaOP = true;
        boolean banderaOPB = false;
        loop:
        for (int i = 0; i < cadenaArray.length; i++) {
            String cadena = cadenaArray[i];
            switch (estadoActual) {
                case 0:
                    if (cadena.equals("for")) {
                        estadoActual = 1;
                    } else {
                        return false;
                    }
                    break;
                case 1:
                    if (cadena.equals("(")) {
                        estadoActual = 2;
                    } else {
                        return false;
                    }
                    break;
                case 2:
                    if (validarIdentificador(cadena)) {
                        estadoActual = 4;
                    } else if (cadena.equals("int") || cadena.equals("string") || cadena.equals("float") || cadena.equals("boolean")) {
                        estadoActual = 3;
                    } else {
                        return false;
                    }
                    break;
                case 3:
                    if (validarIdentificador(cadena)) {
                        estadoActual = 4;
                    } else {
                        return false;
                    }
                    break;
                case 4:
                    if (cadena.equals("=")) {
                        estadoActual = 5;
                    } else {
                        return false;
                    }
                    break;
                case 5:
                    if (validarNumero(cadena) || cadena.equals("true") || cadena.equals("false")) {
                        estadoActual = 6;
                    } else {
                        return false;
                    }
                    break;
                case 6:
                    if (cadena.equals(";") && !cadenaArray[i + 1].equals(";")) {
                        estadoActual = 7;
                    } else {
                        return false;
                    }
                    break;
                case 7:
                    switch (cadena) {
                        case "(":
                            pila.Insertar(cadena.charAt(0));
                            banderaNEG = true;
                            banderaOP = true;
                            banderaOPB = false;
                            break;
                        case ")":
                            if (!pila.PilaVacia()) {
                                pila.extraer();
                                banderaOPB = true;
                                banderaNEG = false;
                                banderaOP = false;
                            } else {
                                return false;
                            }
                            break;
                        case ";":
                            if (pila.PilaVacia()) {
                                estadoActual = 8;
                            } else {
                                return false;
                            }
                            break;
                        default:
                            //Negación
                            if ((cadena.equals("!")) && banderaNEG) {
                                banderaOP = true;
                                banderaOPB = false;
                                banderaNEG = false;
                            } else {
                                //Operador binario
                                if ((cadena.equals("==") || cadena.equals("!=") || cadena.equals("<=") || cadena.equals(">=")
                                        || cadena.equals("||") || cadena.equals("&&") || cadena.equals("-") || cadena.equals("+")
                                        || cadena.equals("*") || cadena.equals("/") || cadena.equals("%") || cadena.equals("<")
                                        || cadena.equals(">")) && banderaOPB) {
                                    banderaOP = true;
                                    banderaNEG = false;
                                    banderaOPB = false;
                                } else {
                                    //Operando
                                    if ((validarNumero(cadena) || validarIdentificador(cadena) || cadena.equals("true")
                                            || cadena.equals("false")) && banderaOP) {
                                        banderaOPB = true;
                                        banderaOP = false;
                                        banderaNEG = false;
                                    }
                                    //Una entrada inválida
                                    else {
                                        return false;
                                    }
                                }
                            }
                            break;
                    }
                    break;
                case 8:
                    if (validarIdentificador(cadena)) {
                        estadoActual = 9;
                    } else {
                        return false;
                    }
                    break;
                case 9:
                    if (cadena.equals("++") || cadena.equals("--")) {
                        estadoActual = 10;
                    } else {
                        return false;
                    }
                    break;
                case 10:
                    if (cadena.equals(")")) {
                        estadoActual = 11;
                    } else {
                        return false;
                    }
                    break;
                case 11:
                    if (i == (cadenaArray.length - 1)) {
                        if (cadena.equals("{")) {
                            break loop;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
            }
        }
        return true;
    }

    public static boolean validarElse(String dataEnter) {
        int estadoActual = 0;
        String[] cadenaArray = dataEnter.trim().split("\\s+");
        loop :
        for (int i = 0; i < cadenaArray.length;i++) {
            String cadena = cadenaArray[i];
            System.out.println("//" + cadena);
            switch (estadoActual) {
                case 0:
                    if (cadena.equals("}")){
                        estadoActual = 1;
                    } else {
                        return false;
                    }
                    break;
                case 1:
                    if (cadena.equals("else")){
                        estadoActual = 2;
                    } else {
                        return false;
                    }
                    break;
                case 2:
                    if (cadena.equals("{") && cadenaArray.length - 1 == i){
                        break loop;
                    } else {
                        return false;
                    }
            }
        }

        return true;
    }

    //TODO: AGREGAR
    private static boolean validarIdentificador(String cadena) {
        Pattern I = Pattern.compile("[a-zA-Z][\\w]*");
        Matcher m = I.matcher(cadena);
        return m.find() && !validarPalabra(cadena);
    }

    public static void reitinialized() {
        contador = 0;
        fin = false;
        estadoActual = 0;
        estadoInicio = estadoActual;
        entrada = "";
    }

    public static boolean validarNumero(String arrayEntrada) {
        boolean estatus = false;
        String arrayAux;
        arrayAux = arrayEntrada;

/*        arrayAux = arrayAux.replace('[', ' ');
        arrayAux = arrayAux.replace(']', ' ');*/
        String[] arrayDataAux;


        arrayDataAux = arrayAux.split(" ");
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(arrayDataAux[0]);

        if (matcher.matches()) {
            estatus = true;
        }

        return estatus;
    }

    public static boolean  automateValidFunctionBody(String dataEnter){
        int[] estadoFinal = {5};
        boolean access = true;
        String exitErr;

        entrada = dataEnter;
        exitErr = null;

        String[] arrayEntrada = analitedData(entrada, 10);

        int valC = 0;
        for (int i = 0; i < arrayEntrada.length; i++) {
            if (arrayEntrada[i] != null) {
                valC++;
            }
        }
        System.out.println("val c: ="+valC);

        while (!fin) {
            System.out.println("función body");

            if (contador > entrada.length() - 1) {
                fin = true;
                break;
            }

            try{
                if (estadoActual == 0) {
                    contador++;
                    if (arrayEntrada[0].equals("body")) {
                        estadoActual = 1;

                        System.out.println(arrayEntrada[contador]);
                    } else {
                        aceptado = false;
                        break;
                    }

                    continue;
                }
                if (estadoActual == 1) {
                    contador++;
                    if (arrayEntrada[1].equals("(")) {
                        estadoActual = 2;

                    } else {
                        aceptado = false;
                        break;
                    }

                    continue;
                }
                if (estadoActual == 2) {
                    contador++;
                    if (arrayEntrada[2].equals("args")) {
                        estadoActual = 3;
                    } else {
                        aceptado = false;
                        break;
                    }

                    continue;
                }
                if (estadoActual == 3) {
                    contador++;
                    if (arrayEntrada[3].equals(")")) {

                        estadoActual = 4;
                    } else {
                        aceptado = false;
                        break;
                    }

                    continue;
                }
                if (estadoActual == 4) {
                    contador++;
                    if (arrayEntrada[4].equals("{")) {

                        estadoActual = 5;
                    } else {
                        aceptado = false;
                        break;
                    }

                    continue;
                }
                if (estadoActual == 5) {
                    break;
                }

            }catch (Exception a){

            }
        }

        if (estadoActual == estadoFinal[0] && access) {
            aceptado = true;
        }
        reitinialized();
        access = true;

        return aceptado;
    }

    public static boolean automateValidFunctionRest( String dataEnter) {
        int[] estadoFinal = {9};
        boolean access = true;
        String exitErr;



        entrada = dataEnter;
        exitErr = null;

        //String[] arrayEntrada = entrada.split(" ");
        String[] arrayEntrada = analitedData(entrada, 10);

        int valC = 0;
        for (int i = 0; i < arrayEntrada.length; i++) {
            if (arrayEntrada[i] != null) {
                valC++;
            }
        }

        if (valC > 9) {
            access = false;
        }

        while (!fin) {
            System.out.println("Rest");

            if (contador > entrada.length() - 1) {
                fin = true;
                break;
            }

            try {
                if (estadoActual == 0) {
                    contador++;
                    if (ValueDefinitionsVariable.compareVariablesUser(arrayEntrada[0])) {
                        estadoActual = 1;
                    } else {
                        aceptado = false;
                    }
                    continue;
                }

                if (estadoActual == 1) {
                    if (arrayEntrada[1].equals("=")) {
                        estadoActual = 2;
                    } else {
                        aceptado = false;
                    }
                    contador++;
                    continue;
                }

                if (estadoActual == 2) {
                    if (arrayEntrada[2].equals("rest")) {
                        estadoActual = 3;
                    } else {
                        aceptado = false;
                    }
                    contador++;
                    continue;
                }

                if (estadoActual == 3) {
                    if (arrayEntrada[3].equals("(")) {
                        estadoActual = 4;
                    } else {
                        aceptado = false;
                    }
                    contador++;
                    continue;
                }

                if (estadoActual == 4) {
                    if (ValueDefinitionsVariable.compareVariablesUser(arrayEntrada[4]) || validarNumero(arrayEntrada[4])) {
                        estadoActual = 5;
                    }

                    contador++;
                    continue;
                }

                if (estadoActual == 5) {

                    if (arrayEntrada[5].equals(",")) {
                        estadoActual = 6;
                    }

                    contador++;
                    continue;
                }

                if (estadoActual == 6) {
                    if (ValueDefinitionsVariable.compareVariablesUser(arrayEntrada[6]) || validarNumero(arrayEntrada[6])) {
                        estadoActual = 7;
                    }

                    contador++;
                    continue;
                }

                if (estadoActual == 7) {
                    if (arrayEntrada[7].equals(")")) {
                        estadoActual = 8;
                    } else {
                        aceptado = false;
                    }
                    contador++;
                    continue;
                }

                if (estadoActual == 8) {
                    contador++;
                    if (arrayEntrada[8].equals(";")) {
                        estadoActual = 9;
                    } else {
                        aceptado = false;
                    }
                    continue;
                }

                if (estadoActual == 9) {
                    break;
                }

            } catch (Exception e) {
            }
        }

        if (estadoActual == estadoFinal[0] && access) {
            aceptado = true;
        }
        reitinialized();
        access = true;

        return aceptado;

    }

    public static boolean automataArreglo(String dataEnter) {
        int estadoActual = 0;
        int estadoFinal = 5;
        int contador = 0;

        boolean aceptado = false;

        entrada = dataEnter;
        String[] datos = analitedData(entrada, 10);
        while (contador < datos.length - 1) {
            if(estadoActual == 0) {
                if(datos[contador].equals("args") || datos[contador].equals("char") || datos[contador].equals("float") || datos[contador].equals("int")) {
                    estadoActual = 1;
                    contador++;
                    continue;
                }
            }

            if(estadoActual == 1) {
                Pattern patternAceptado = Pattern.compile("[a-zA-Z]");
                Pattern patternProhibido = Pattern.compile("[.!#$%&'*+/=?^`{|}~-]");
                Matcher matcherAceptado = patternAceptado.matcher(datos[contador]);
                Matcher matcherProhibido = patternProhibido.matcher(datos[contador]);

                if(!matcherProhibido.find()) {
                    if(matcherAceptado.find()) {
                        estadoActual = 2;
                        contador++;
                        continue;
                    }
                } else {
                    //error
                }
            }

            if(estadoActual == 2) {
                if(dimensionMatriz(datos[contador])) {
                    estadoActual = 3;
                    contador++;
                    continue;
                } else if(dimensionArreglo(datos[contador])) {
                    estadoActual = 4;
                }
            }

            if(estadoActual == 3) {
                if(datos[contador].equals(";")) {
                    estadoActual = 5;
                    contador++;
                    continue;
                }
            }

            if(estadoActual == 4) {
                if(datos[contador].equals(";")) {
                    estadoActual = 5;
                    contador++;
                    continue;
                }
            }

            contador ++;
        }

        if(estadoActual == estadoFinal) {
            aceptado = true;
            ValueDefinitionsVariable.addVariablesUser(datos[1]);
        } else {
            System.out.println("Error");
        }

        return aceptado;
    }

    public static boolean dimensionArreglo(String dimension) {
        boolean existe = false;
        String regexp = "\\[\\d+\\]";
        if(Pattern.matches(regexp, dimension)) {
            existe = true;
        }

        return existe;
    }

    public static boolean dimensionMatriz(String dimension) {
        boolean existe = false;
        String regexp = "\\[\\d+\\]\\[\\d+\\]";
        if(Pattern.matches(regexp, dimension)) {
            existe = true;
        }

        return existe;
    }

    public static boolean automataDisplayEnter(String dataEnter) {
        int[] estadoFinal = {5};
        boolean access = true;
        String exitErr;


        entrada = dataEnter;
        exitErr = null;

        //String[] arrayEntrada = entrada.split(" ");
        String[] arrayEntrada = analitedData(entrada, 10);

        int valC = 0;
        for (int i = 0; i < arrayEntrada.length; i++) {
            if (arrayEntrada[i] != null) {
                valC++;
            }
        }

        if (valC > 5) {
            access = false;
        }

        while (!fin) {

            if (contador > entrada.length() - 1) {
                fin = true;
                break;
            }

            try {
                if (estadoActual == 0) {
                    contador++;
                    if (arrayEntrada[0].equals("displayEnter")) {
                        estadoActual = 1;
                    } else {
                        aceptado = false;
                        break;
                    }
                    continue;
                }

                if (estadoActual == 1) {
                    contador++;

                    if (arrayEntrada[1].equals("(")) {
                        estadoActual = 2;
                    } else {
                        aceptado = false;
                        break;
                    }
                    continue;
                }

                if (estadoActual == 2) {
                    contador++;

                    if (ValueDefinitionsVariable.compareVariablesUser(arrayEntrada[2])) {
                        estadoActual = 3;
                    } else {
                        estadoActual = 3;
                    }

                    continue;
                }

                if (estadoActual == 3) {
                    contador++;

                    if (arrayEntrada[3].equals(")")) {
                        estadoActual = 4;
                    } else {
                        break;
                    }

                    continue;
                }

                if (estadoActual == 4) {
                    contador++;
                    if (arrayEntrada[4].equals(";")) {
                        estadoActual = 5;
                    } else {
                        aceptado = false;
                        break;
                    }
                    continue;
                }

                if (estadoActual == 5) {
                    break;
                }

            } catch (Exception e) {
            }
        }

        if (estadoActual == estadoFinal[0] && access) {
            aceptado = true;
        }

        reitinialized();

        access = true;


        return aceptado;
    }


}
