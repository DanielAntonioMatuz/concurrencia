package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label etiqueta;

    @FXML
    public void mouseClickStart(ActionEvent event) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                long count = 0;
                for (long x = 1; x < 1000000000; x++) {
                    count++;
                }
                final long  valor = count;
                Platform.runLater(
                        new Runnable() {
                            @Override
                            public void run() {
                                etiqueta.setText(String.valueOf(valor));
                            }
                        }
                );
            }
        }).start();
    }

}
