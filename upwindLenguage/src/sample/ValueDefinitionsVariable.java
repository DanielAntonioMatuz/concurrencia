package sample;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueDefinitionsVariable {

    public static ArrayList<String> variablesAddUser = new ArrayList<>();

    public static boolean verificarIdentificador (String cadena) {

        Pattern pattern = Pattern.compile("[^A-Za-z0-9.@_-~#]|[0-9]|[.]|[#]+");
        Matcher matcher = pattern.matcher(cadena);
        StringBuffer sb = new StringBuffer();
        boolean resultado = matcher.find();
        boolean caracteresIlegales = false;

        while(resultado) {
            caracteresIlegales = true;
            matcher.appendReplacement(sb, "");
            resultado = matcher.find();
        }

        matcher.appendTail(sb);
        return caracteresIlegales;
    }

    public static boolean verificarIlegales(String cadena) {
        boolean validation = false;
        Pattern pattern = Pattern.compile("[^A-Za-z0-9.@_-~#]+");
        Matcher matcher = pattern.matcher(cadena);
        StringBuffer sb = new StringBuffer();
        boolean resultado = matcher.find();

        while(resultado) {
            validation = true;
            matcher.appendReplacement(sb, "");
            resultado = matcher.find();
        }

        matcher.appendTail(sb);

        return validation;

    }

    public static boolean verificadorArgumento(String cadena) {
        boolean evaluation = false;

        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(cadena);

        if (matcher.matches()) {

            if (!verificarIlegales(cadena)) {
                evaluation = true;
            } else {
                evaluation = false;
            }

        } else {
            pattern = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
            matcher = pattern.matcher(cadena);

            if (matcher.matches()) {
                evaluation = true;
            }

        }

        return evaluation;
    }

    public static void addVariablesUser(String data) {
        variablesAddUser.add(data);
        setVariablesAddUser(variablesAddUser);
    }

    public static boolean compareVariablesUser(String data) {
        boolean confirm = false;
        if (variablesAddUser.contains(data)) {
            confirm = true;
        }

        return confirm;
    }

    public static ArrayList<String> getVariablesAddUser() {
        return variablesAddUser;
    }

    public static void setVariablesAddUser(ArrayList<String> variablesAddUser) {
        ValueDefinitionsVariable.variablesAddUser = variablesAddUser;
    }
}
