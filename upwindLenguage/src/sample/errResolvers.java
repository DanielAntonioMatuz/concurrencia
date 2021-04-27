package sample;

import java.util.ArrayList;

public class errResolvers {

    public static ArrayList<String> errData = new ArrayList<>();
    public static boolean errValue = false;

    public static void outputSystem(String data, int position, String err, boolean type) {

        if (type) {
            errData.add("ERR: en posición (" + position + ") con valor de \""  + data + "\" | y error: " + err + "\n");
            errValue = true;
        } else {
            errData.add("Definicion de " + data + " (" + err + ")" + " correcta, analisis sintactico OK \n");
        }
        setErrData(errData);
    }

    public static void removeErrBuffer () {
        errData.clear();
    }

    public static ArrayList<String> getErrData() {
        return errData;
    }

    public static void setErrData(ArrayList<String> errData) {
        errResolvers.errData = errData;
    }

    public static boolean isErrValue() {
        return errValue;
    }

    public static void setErrValue(boolean errValue) {
        errResolvers.errValue = errValue;
    }
}
