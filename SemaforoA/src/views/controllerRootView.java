package views;

import javafx.scene.paint.Color;
import root.Hilo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.util.Observable;
import java.util.Observer;

public class controllerRootView implements Observer {

    @FXML
    private Circle circleRed;

    @FXML
    private Circle circleYellow;

    @FXML
    private Circle circleGreen;

    @FXML
    private Button boton;

    @FXML
    void iniciarOnMouse(MouseEvent event) {
        Hilo hilo = new Hilo();
        hilo.addObserver(this);
        Thread hiloSemaforo = new Thread(hilo);

        hiloSemaforo.setDaemon(true);
        hiloSemaforo.start();
    }

    @FXML
    void salir(MouseEvent event) {
        System.exit(1);
    }

    @Override
    public void update(Observable o, Object arg) {
        int turno;
        turno = Integer.parseInt(arg.toString());

        if(turno == 1){
            circleRed.setFill(Color.RED);
            circleYellow.setFill(Color.BLACK);
            circleGreen.setFill(Color.BLACK);
        }

        if (turno == 2){
            circleRed.setFill(Color.BLACK);
            circleYellow.setFill(Color.YELLOW);
            circleGreen.setFill(Color.BLACK);
        }

        if(turno == 3){
            circleRed.setFill(Color.BLACK);
            circleYellow.setFill(Color.BLACK);
            circleGreen.setFill(Color.GREEN);
        }
    }
}