package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.lang.reflect.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    @FXML
    private Button executeP1;

    @FXML
    private TextField inputTelefono;

    @FXML
    private TextField valTel;

    @FXML
    private Button executeP2;

    @FXML
    private TextField inputCurp;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField valCurp;

    @FXML
    private TextField valEmailExit;

    private Matcher match;

    @FXML
    public void executeP1(ActionEvent event) {
        Pattern valuate = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
        Pattern valEmail = Pattern.compile("ids.upchiapas.edu.mx");

        String [] emailData;
        emailData = inputEmail.getText().split("@");

        Matcher match = valuate.matcher(inputTelefono.getText());
        Matcher matchEmail = null;
        try {
            matchEmail = valEmail.matcher(emailData[1]);
        } catch (Exception e) {
            valEmailExit.setText("El email no es válido");
            valEmailExit.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        }


       // 183441@ids.upchiapas.edu.mx

        if (match.matches()) {
            valTel.setText("El número de telefono es válido");
            valTel.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
        } else {
            valTel.setText("El número de telefono no es válido");
            valTel.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        }



        try {
            if (matchEmail.matches()) {
                valEmail = Pattern.compile("[^A-Za-z0-9.@_-~#]+");
                matchEmail = valEmail.matcher(emailData[0]);

                StringBuffer buffer = new StringBuffer();
                boolean resultado = matchEmail.find();
                boolean ilegalCharacter = false;

                while(resultado) {
                    ilegalCharacter = true;
                    matchEmail.appendReplacement(buffer, "");
                    resultado = matchEmail.find();
                }

                if(!ilegalCharacter) {
                    valEmailExit.setText("El email es válido");
                    valEmailExit.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
                } else {
                    valEmailExit.setText("El email no es válido");
                    valEmailExit.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                }

            } else {
                valEmailExit.setText("El email no es válido");
                valEmailExit.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            }
        } catch (Exception e) {
            valEmailExit.setText("El email no es válido");
            valEmailExit.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        }
    }

    @FXML
    public void executeP2(ActionEvent event) {
        Pattern valuate = Pattern.compile("^([A-Z][AEIOUX][A-Z]{2}\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])[HM](?:AS|B[CS]|C[CLMSH]|D[FG]|G[TR]|HG|JC|M[CNS]|N[ETL]|OC|PL|Q[TR]|S[PLR]|T[CSL]|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d])(\\d)$");
        Matcher match = valuate.matcher(inputCurp.getText().toUpperCase());

        if (!match.matches()) {
            valCurp.setText("La CURP no es válida");
            valCurp.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        } else {
            if ((inputCurp.getText().length() -10) != verificadorCURP(inputCurp.getText())) {
                valCurp.setText("La CURP es válida");
                valCurp.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
            }
        }
    }

    public double verificadorCURP(String curp) {
        String diccionario = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        double lngSuma = 0.0;
        double lngDigito = 0.0;

        for(int i = 0; i < 17; i++) {
            lngSuma = lngSuma + diccionario.indexOf(curp.charAt(i)) * (18 - i);
        }
        lngDigito = 10 - lngSuma % 10;
        if (lngDigito == 10) {
            return 0;
        } else {
            return lngDigito;
        }
    }
}
