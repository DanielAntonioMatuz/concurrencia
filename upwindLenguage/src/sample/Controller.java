package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static sample.LibPalabrasReservadas.validarPalabra;
import static sample.LibPalabrasReservadas.regresarSignificado;
import static sample.LibSimbolos.*;
import static sample.ValueDefinitionsVariable.*;


public class Controller implements Initializable {
    String exitErr;
    String cadenaDataAux;
    boolean declaradaBody = false;

    @FXML private TextArea entrada;
    @FXML private TextArea exitData;
    @FXML private TableView<dataTable> table;
    @FXML private TableColumn<dataTable, String> dataColum;
    @FXML private TableColumn<dataTable, String> definicionColum;

    ObservableList<dataTable> arguments = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataColum.setCellValueFactory(new PropertyValueFactory<dataTable, String>("valor"));
        definicionColum.setCellValueFactory(new PropertyValueFactory<dataTable, String>("definicion"));

        table.setItems(arguments);
    }


    @FXML
    public void analizador() {
        arguments.clear();
        exitErr = null;
        exitData.setText(" ");
        String oracion = entrada.getText();
        String dataAux = entrada.getText();
        List<String> matchList = new ArrayList<String>();
        Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
        Matcher regexMatcher = regex.matcher(oracion);
        int contador = 1;
        while (regexMatcher.find()) {

            String cadena = regexMatcher.group();
            cadenaDataAux = cadena;
            String originalData = cadena;

            if (validarPalabra(cadena)){

                cadena = regresarSignificado(cadena);

            } else if (validarSimbolo(cadena)) {

                cadena = regresarDefincion(cadena);

            } else if (simbolosNoPermitidos(cadena)) {

                cadena = "Simbolo no permitido";
                errResolvers.outputSystem(originalData, contador, cadena, true);

            } else if (simbolosComparativos(cadena)) {

                cadena = regresarSignificado(cadena);

            } else if (verificarIdentificador(cadena)) {
                if (verificadorArgumento(cadena)) {
                    boolean access = false;
                    try {
                        Double data  = Double.parseDouble(cadena);
                        data = 0.0;
                        if (data == 0.0) {
                            cadena = "Númerico";
                            access = true;
                        }

                    } catch (Exception e) {
                        if (!access) {
                            Pattern regexAlpha = Pattern.compile("[.]|[#]|[!]|[$]|[%]|[*]|[+]|[?]|[~]|[`]|[}]|[{]|[,]|[>]|[<]|[&]|[|]|[@]|^]|[¡]|[\\[\\d\\]]+");
                            Matcher regexData = regexAlpha.matcher(cadena);
                            if (regexData.find() && cadena.charAt(0) != '\"') {
                                if (!(cadena.equals("++") || cadena.equals("--") || cadena.equals("&&") || cadena.equals("||"))) {
                                    cadena = "Error, no permitible conjugación de simbolos - palabras";
                                    errResolvers.outputSystem(originalData, contador, cadena, true);
                                }
                            } else {
                                cadena = "Texto";
                            }
                        }
                    }

                } else {
                       cadena = "Identificador inválido";
                       exitErr += "ERR: en posición (" + contador + ") con valor de \""  + originalData + "\" | y error: " + cadena + "\n";
                       errResolvers.outputSystem(originalData, contador, cadena, true);
                }

            } else if (!verificarIdentificador(cadena)) {

                cadena = "Identificador";

            }

            if(dataAux.equals("\"")) {
                cadena = regresarDefincion(cadena);
            }

            arguments.add(new dataTable(originalData, cadena));
            matchList.add(cadena);
            contador++;
        }



        if (exitErr != null) {
            for (int i = 0; i < errResolvers.getErrData().size(); i++) {
                exitErr += errResolvers.getErrData().get(i);
            }
            exitData.setText(exitErr);
            exitData.setStyle("-fx-text-inner-color: red;");
        } else {
            verificationSyntacticData();
        }

        table.setItems(arguments);

    }

    public void verificationSyntacticData() {

        String [] lineas;

        lineas = entrada.getText().split("\n");

        analizadorDeLLaves();
        declaradaBody=false;

        for (int i = 0; i < lineas.length; i++) {
            //TODO: AGRGAR IF FOR Y ELSE
            if (!lineas[i].equals("")) {

                if (declaradaBody == false) {
                    if (controllerAutSyntactic.automateValidVariables(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "VARIABLE", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                        errResolvers.outputSystem(lineas[i], i, "Función Body no declarada aun", true);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: red;");
                    } else if (controllerAutSyntactic.automateValidFunctionDisplay(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION displayView()", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                        errResolvers.outputSystem(lineas[i], i, "Función Body no declarada aun", true);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: red;");
                    } else if (controllerAutSyntactic.automateValidFunctionMult(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION mult()", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else if (controllerAutSyntactic.createFunction(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION DECLARADA", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                        errResolvers.outputSystem(lineas[i], i, "Función Body no declarada aun", true);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: red;");
                    } else if (controllerAutSyntactic.automateValidFunctionBody(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION body()", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                        declaradaBody=true;
                    } else if (controllerAutSyntactic.validarIf(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION if()", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else if (controllerAutSyntactic.validarElse(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION else", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else if (controllerAutSyntactic.validarFor(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION for()", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else if(controllerAutSyntactic.automataArreglo(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "ARREGLO DECLARADO", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else if (controllerAutSyntactic.automateValidFunctionRest(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION rest()", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                        errResolvers.outputSystem(lineas[i], i, "Función Body no declarada aun", true);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: red;");
                    } else if(controllerAutSyntactic.automataDisplayEnter(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION displayEnter", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else {
                        System.out.println("AQA " + analitedData(lineas[i]));
                        if (!analitedData(lineas[i]).equals("{") && !analitedData(lineas[i]).equals("}")) {
                            errResolvers.outputSystem(lineas[i], i, "NOK", true);
                            exitData.setText(exitErr);
                            exitData.setStyle("-fx-text-inner-color: red;");
                        }
                    }
                } else {
                    if (controllerAutSyntactic.automateValidVariables(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "VARIABLE", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else if (controllerAutSyntactic.automateValidFunctionDisplay(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION displayView()", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else if (controllerAutSyntactic.automateValidFunctionMult(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION mult()", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else if (controllerAutSyntactic.createFunction(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION DECLARADA", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else if (controllerAutSyntactic.validarIf(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION if()", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else if (controllerAutSyntactic.validarElse(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION else", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else if (controllerAutSyntactic.validarFor(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION for()", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else if (controllerAutSyntactic.automateValidFunctionBody(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION body()", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                        errResolvers.outputSystem(lineas[i], i, "la función Body ya fue declarada", true);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: red;");
                    } else if(controllerAutSyntactic.automataArreglo(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "ARREGLO DECLARADO", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else if(controllerAutSyntactic.automataDisplayEnter(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION displayEnter", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    }   else if (controllerAutSyntactic.automateValidFunctionRest(lineas[i])) {
                        errResolvers.outputSystem(lineas[i], i, "FUNCION rest()", false);
                        exitData.setText(exitErr);
                        exitData.setStyle("-fx-text-inner-color: green;");
                    } else {
                        if (!analitedData(lineas[i]).equals("{") && !analitedData(lineas[i]).equals("}")) {
                            errResolvers.outputSystem(lineas[i], i, "NOK", true);
                            exitData.setText(exitErr);
                            exitData.setStyle("-fx-text-inner-color: red;");
                        }
                    }
                }

            }
        }

        for (int i = 0; i < errResolvers.getErrData().size(); i++) {
            if (errResolvers.isErrValue()) {
                exitErr += errResolvers.getErrData().get(i);
                exitData.setStyle("-fx-text-inner-color: red;");
            } else {
                exitErr += errResolvers.getErrData().get(i);
                exitData.setStyle("-fx-text-inner-color: green;");
            }
        }
        errResolvers.setErrValue(false);
        exitData.setText(exitErr);

        errResolvers.removeErrBuffer();


    }

    public void analizadorDeLLaves() {
        if (controllerPila.validarExpersion(entrada.getText())) {
            errResolvers.outputSystem("[ ] { } ( ) | ANALIZADOR SIEMPRE ACTIVO", entrada.getLength(), "AGRUPACIONES CORRECTAS", false);
        } else {
            errResolvers.outputSystem("[ ] { } ( )", entrada.getLength(), "ERRORES EN AGRUPACIONES | NO MATCH {  ||  }", true);
        }
    }

    public static String analitedData(String dataEnter) {
        List<String> matchList = new ArrayList<String>();
        Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
        Matcher regexMatcher = regex.matcher(dataEnter);



        //String[] arrayEntrada = entrada.split(" ");
        String[] arrayEntrada = new String[10000];
        int contador = 0;
        while (regexMatcher.find()) {

            String cadena = regexMatcher.group();
            arrayEntrada[contador] = cadena;
            contador++;

        }

        String args = "";

        for (int y = 0; y < arrayEntrada.length; y++) {
            if (arrayEntrada[y] != null) {
                args += arrayEntrada[y];
            }
        }

        return args;
    }

}

/*
/Codigo de prueba:

body ( args ) {
	displayView ( afgals ) ;
	args dataValue = 4 ;
	float dataExam ;
	displayView ( a14s5a2s ) ;
	displayView ( "Hola Mundo" ) ;
	char caracteres [10][10] ;
	char caracteres [10] ;

	if ( dataString == dataValue ) {
		displayView ( "SI" ) ;
	} else {
		displayView ( "Quizas" ) ;
	}

	functionMath ( ) {
		dataExam = mult ( 55 , dataValue ) ;

		for ( i = 0 ; dataValue <= ( ( i * numero ) / 12 ) ; i ++ ) {
            		displayEnter ( dataFloat ) ;
		}
	}

	functionResult ( float data ) {
		displayView ( data ) ;
	}
}
 */
