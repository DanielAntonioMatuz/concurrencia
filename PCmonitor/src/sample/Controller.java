package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Model.Consumidor;
import sample.Model.Monitor;
import sample.Model.Productor;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public AnchorPane layout;

    @FXML
    public Button btnIniciar;

    @FXML
    public Button btnSalir;

    @FXML
    public Label lblElementos;

    @FXML
    void iniciar(ActionEvent event) {
        
    }

    @FXML
    void salir(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Monitor monitor = new Monitor(10);


        Productor productor = new Productor(monitor);
        Consumidor consumidor = new Consumidor(monitor);

        Thread HiloP = new Thread(productor, "Pro");
        Thread HiloC = new Thread(consumidor, "Con");

        HiloP.setDaemon(true);
        HiloC.setDaemon(true);

        HiloP.start();
        HiloC.start();
    }

}
