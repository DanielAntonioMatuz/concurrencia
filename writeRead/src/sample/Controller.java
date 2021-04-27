package sample;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import sample.Model.*;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Controller implements Observer {
    @FXML
    private Circle ledLector;

    @FXML
    private Circle ledEscritor;

    @FXML
    private Label labelEscritor;

    @FXML
    private Button btnIniciar;

    @FXML
    private AnchorPane layout;

    @FXML
    private Text visibleL;

    @FXML
    private Text visibleE;

    private boolean bandera = true;
    private int x = 41, y = 87;
    private int ledEG = 0, ledLG = 0;
    Circle[] ledLectorArray = new Circle[50];
    Circle[] ledEscritorArray = new Circle[50];

    @FXML
    void startApp(ActionEvent event) {
        dataBase db = new dataBase();
        Semaphore access_write = new Semaphore(1);
        Semaphore lleno = new Semaphore(0);
        Random random = new Random(System.currentTimeMillis());
        write escritor;
        read lector;
        monitor monitor = new monitor(10);

        int lectores;
        int escritores;

        lectores = random.nextInt(50) + 1;
        escritores = random.nextInt(50) + 1;

        for (int i = 1; i <= lectores; i++) {
            lector = new read(access_write, lleno, db, String.valueOf(i));
            lector.addObserver(this);
            new Thread(lector).start();
            if (i <= lectores) {
                x+=51;
                Circle ledLectorG = new Circle(x,y,21,Color.BLACK);
                ledLectorG.setId(lector.getName());
                ledLectorArray[i] = ledLectorG;
                layout.getChildren().add(ledLectorG);
                ledLG++;
                if (i%20 == 0) {
                    y = 156;
                    x = 41;
                }
            }
            visibleL.setText("visible: " + ledLG);
        }

        x = 41;
        y = 243;

        for (int i = 1; i <= escritores; i++) {
            escritor = new write(monitor, db, String.valueOf(i));
            escritor.addObserver(this);
            new Thread(escritor).start();
            x+=51;
            if (i <= escritores) {
                Circle ledEscritorG = new Circle(x,y, 21, Color.BLACK);
                layout.getChildren().add(ledEscritorG);
                ledEscritorArray[i] = ledEscritorG;
                ledEscritorG.setId(escritor.getName());
                ledEG++;
                if (i%20 == 0) {
                    y = 303;
                    x = 41;
                }
            }
            visibleE.setText("visible: " + ledEG);
        }
    }

    int count = 0, countA = 0 ;
    @Override
    public void update(Observable o, Object arg) {

        Platform.runLater(() -> {
            if (o instanceof read) {
                ledLector.setFill(Color.GREEN);
                bandera = false;
                if (Integer.parseInt(((read) o).getName()) <= ledLG) {
                    ledLectorArray[Integer.parseInt(((read) o).getName())].setFill(Color.GREEN);
                }
                /*if(bandera) {
                    count++;
                    System.out.println(count);
                    ledLector.setFill(Color.GREEN);
                    bandera = false;
                    if (Integer.parseInt(((read) o).getName()) <= ledLG) {
                        ledLectorArray[Integer.parseInt(((read) o).getName())].setFill(Color.GREEN);
                    }
                } else {
                    ledLector.setFill(Color.BLACK);
                    bandera = true;
                    countA++;
                    System.out.println(countA);
                    if (Integer.parseInt(((read) o).getName()) <= ledLG) {
                        ledLectorArray[Integer.parseInt(((read) o).getName())].setFill(Color.BLACK);
                    }
                } */
            } else {
                ledEscritor.setFill(Color.RED);
                labelEscritor.setText("Último Thread en ejecutar: " + String.valueOf(arg));
                if (o instanceof write) {
                    if (Integer.parseInt(((write) o).getName()) <= ledEG) {
                        ledEscritorArray[Integer.parseInt(((write) o).getName())].setFill(Color.RED);
                    }
                }
            }
        });

    }
}
