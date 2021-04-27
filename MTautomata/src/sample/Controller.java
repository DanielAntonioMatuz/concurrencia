package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {

    public int contador = 0;
    public int estadoActual = 0;
    public int estadoInicio = estadoActual;
    public int estadoFinal = 5;
    public boolean fin = false;
    public String entrada, salida;
    public char[] vocabulario = {'0', '1', 'B'};
    public int counterExecutions = 0;

    @FXML
    private TextField ingresoDatos;

    @FXML
    private TextField salidaDatos;

    @FXML
    private Text numeroEjecuciones;

    @FXML
    private Text errorExit;


    @FXML
    private Button ejecutar;

    @FXML
    private CheckBox mod;

    @FXML
    public void modTuring(ActionEvent event) {
        if (mod.isSelected()) {
            ingresoDatos.setVisible(false);
            ejecutar.setVisible(false);
        } else {
            ingresoDatos.setVisible(true);
            ejecutar.setVisible(true);
        }
    }

    @FXML
    public void automataMT(ActionEvent event) {
        entrada = ingresoDatos.getText() + "B";
        errorExit.setVisible(false);
        counterExecutions = 0;
        char[] arrayEntrada = new char[entrada.length()];

        for (int i = 0; i < entrada.length(); i++) {
            arrayEntrada[i] = entrada.charAt(i);
        }


        while (!fin) {
            counterExecutions++;
            if (contador > entrada.length() - 1) {
                fin = true;
                break;
            }

            if (estadoActual == 0) {
                if (vocabulario[0] == arrayEntrada[contador]) {
                    estadoActual = 1;
                } else if (vocabulario[1] == arrayEntrada[contador]) {
                    estadoActual = 4;
                } else if (vocabulario[2] == arrayEntrada[contador]) {
                    estadoActual = 5;
                } else {
                    errorExit.setVisible(true);
                    errorExit.setText("ERROR EN EL SIMBOLO: " + arrayEntrada[contador]);
                    reignited();
                    break;
                }
                contador++;
                continue;

            }

            if (estadoActual == 1) {

                if (vocabulario[0] == arrayEntrada[contador]) {
                    estadoActual = 2;
                    arrayEntrada[contador] = '1';
                    contador--;
                } else if (vocabulario[1] == arrayEntrada[contador]) {
                    estadoActual = 4;
                    contador++;

                } else if (vocabulario[2] == arrayEntrada[contador]) {
                    estadoActual = 5;
                    contador++;
                } else {
                    errorExit.setVisible(true);
                    errorExit.setText("ERROR EN EL SIMBOLO: " + arrayEntrada[contador]);
                    reignited();
                    break;
                }
                continue;
            }

            if (estadoActual == 2) {

                if (vocabulario[0] == arrayEntrada[contador]) {
                    estadoActual = 3;
                    arrayEntrada[contador] = '1';
                    contador++;

                } else if (vocabulario[1] == arrayEntrada[contador]) {
                    estadoActual = 3;
                    arrayEntrada[contador] = '0';
                    contador++;

                } else {
                    errorExit.setVisible(true);
                    errorExit.setText("ERROR EN EL SIMBOLO: " + arrayEntrada[contador]);
                    reignited();
                    break;
                }

                continue;
            }

            if (estadoActual == 3) {

                if (vocabulario[0] == arrayEntrada[contador]) {
                    estadoActual = 0;

                } else if (vocabulario[1] == arrayEntrada[contador]) {
                    estadoActual = 0;
                } else {
                    errorExit.setVisible(true);
                    errorExit.setText("ERROR EN EL SIMBOLO: " + arrayEntrada[contador]);
                    reignited();
                    break;
                }

                contador++;
                continue;
            }

            if (estadoActual == 4) {

                if (vocabulario[0] == arrayEntrada[contador]) {
                    estadoActual = 1;
                    contador++;

                } else if (vocabulario[1] == arrayEntrada[contador]) {
                    estadoActual = 2;
                    arrayEntrada[contador] = '0';
                    contador--;

                } else if (vocabulario[2] == arrayEntrada[contador]) {
                    estadoActual = 5;
                    contador++;

                } else {
                    errorExit.setVisible(true);
                    errorExit.setText("ERROR EN EL SIMBOLO: " + arrayEntrada[contador]);
                    reignited();
                    break;
                }

                continue;
            }

            if (estadoActual == 5) {
                break;
            }
        }


        if (estadoActual == estadoFinal) {
            reignited();
            salidaDatos.setText(String.valueOf(arrayEntrada));
            numeroEjecuciones.setText(String.valueOf(counterExecutions));
        }
    }

    public void reignited() {
        contador = 0;
        fin = false;
        estadoActual = 0;
        estadoInicio = estadoActual;
    }

}