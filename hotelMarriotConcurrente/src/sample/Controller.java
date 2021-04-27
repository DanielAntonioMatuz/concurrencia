package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import sample.Model.Cliente;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Observer {
    @FXML
    public AnchorPane layout;

    @FXML
    public Rectangle ledOrden;

    Circle[] clientesArray = new Circle[1];
    int x = 54, y = 35, idM = 0;
    boolean access = false;
    boolean accessLevel2 = true;


    @FXML
    public void startRestaurant() {

        Cliente cliente;
        Circle clienteV = new Circle(x, y, 23, Color.ORANGE);
        clientesArray[0] = clienteV;
        cliente = new Cliente(x , y, 17);
        new Thread(cliente).start();
        cliente.addObserver(this);
        layout.getChildren().add(clienteV);


        Timer timer;
        timer = new Timer();

        TimerTask task = new TimerTask() {
            int tic=0;

            @Override
            public void run()
            {
                if (tic <= 60) {
                    if (tic%2==0) {

                    }
                    else{

                    }
                    tic++;
                }
            }
        };
        timer.schedule(task, 10000, 5000);

    }


    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            if (o instanceof Cliente) {


                // RUTA 01
                if (clientesArray[0].getCenterY() >= 23 && (clientesArray[0].getCenterY() <= 392) && !access) {
                    ((Cliente) o).setEjeY(((Cliente) o).getEjeY() + 5);
                    clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                }

                //RUTA 02
                if (clientesArray[0].getCenterY() >= 382 && (clientesArray[0].getCenterX() <= 190)) {
                    ((Cliente) o).setEjeX(((Cliente) o).getEjeX() + 5);
                    clientesArray[0].setCenterX(((Cliente) o).getEjeX());

                }

                //RUTA 03
                if (clientesArray[0].getCenterX() >= 180 && clientesArray[0].getCenterY() >= 140 && clientesArray[0].getCenterY() <= 400 && clientesArray[0].getCenterX() <=205 && clientesArray[0].getCenterY() != 227 && clientesArray[0].getCenterY() != 354 ) {
                    access = true;
                    ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 5);
                    System.out.println("ROUT 02 " + ((Cliente) o).getEjeY());
                    clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                }

                //RUTA 04 SUPERIOR A MESAS
                if (clientesArray[0].getCenterX() >= 180 && clientesArray[0].getCenterY() >= 135 && clientesArray[0].getCenterY() <= 155 && clientesArray[0].getCenterX() <= 1260 && ((Cliente) o).getIdMesa() >=1 && ((Cliente) o).getIdMesa() <= 14) {
                    access = true;
                    ((Cliente) o).setEjeX(((Cliente) o).getEjeX() + 5);
                    System.out.println("ROUT 03 " + ((Cliente) o).getEjeX());
                    clientesArray[0].setCenterX(((Cliente) o).getEjeX());

                    //CONTROLADORES DE POSICIONES DE MESAS

                    if (clientesArray[0].getCenterX() >= 460 && clientesArray[0].getCenterX()<= 475 && ((Cliente) o).getIdMesa() == 1) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 62);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 590 && clientesArray[0].getCenterX()<= 605 && ((Cliente) o).getIdMesa() == 2) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 62);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 730 && clientesArray[0].getCenterX()<= 745 && ((Cliente) o).getIdMesa() == 3) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 62);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 864 && clientesArray[0].getCenterX()<= 880 && ((Cliente) o).getIdMesa() == 4) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 62);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 998 && clientesArray[0].getCenterX()<= 1013 && ((Cliente) o).getIdMesa() == 5) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 62);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 1132 && clientesArray[0].getCenterX()<= 1147 && ((Cliente) o).getIdMesa() == 6) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 62);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 1254 && clientesArray[0].getCenterX()<= 1264 && ((Cliente) o).getIdMesa() == 7) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 62);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    //NIVEL INFERIOR DE LA PRIMERA SECCION

                    if (clientesArray[0].getCenterX() >= 460 && clientesArray[0].getCenterX()<= 475 && ((Cliente) o).getIdMesa() == 8) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() + 92);
                        System.out.println("ALAKMSKAL: " + ((Cliente) o).getEjeY());
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                        accessLevel2 = false;
                    }

                    if (clientesArray[0].getCenterX() >= 590 && clientesArray[0].getCenterX()<= 605 && ((Cliente) o).getIdMesa() == 9) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() + 92);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 730 && clientesArray[0].getCenterX()<= 745 && ((Cliente) o).getIdMesa() == 10) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() + 92);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 864 && clientesArray[0].getCenterX()<= 880 && ((Cliente) o).getIdMesa() == 11) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() + 92);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 998 && clientesArray[0].getCenterX()<= 1013 && ((Cliente) o).getIdMesa() == 12) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() + 92);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 1132 && clientesArray[0].getCenterX()<= 1147 && ((Cliente) o).getIdMesa() == 13) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() + 92);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 1254 && clientesArray[0].getCenterX()<= 1264 && ((Cliente) o).getIdMesa() == 14) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() + 92);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                }

                if (clientesArray[0].getCenterX() >= 180 && clientesArray[0].getCenterY() >= 135 && clientesArray[0].getCenterY() <= 155 && clientesArray[0].getCenterX() <= 355 && ((Cliente) o).getIdMesa() >=15 && ((Cliente) o).getIdMesa() <= 30) {
                    access = true;
                    ((Cliente) o).setEjeX(((Cliente) o).getEjeX() + 5);
                    System.out.println("ROUT 04-A " + ((Cliente) o).getEjeX());
                    clientesArray[0].setCenterX(((Cliente) o).getEjeX());
                }

                    //RUTA 05 INFERIOR PARA BAJAR A MESAS DE NIVEL INFERIOR - COMENTAR ESTE BLOQUE PARA HACER FUNCIONAR LA RUTA 04
                if (clientesArray[0].getCenterX() >= 340  && clientesArray[0].getCenterY() <= 550 && clientesArray[0].getCenterX() <= 350 && ((Cliente) o).getIdMesa() >=15 && ((Cliente) o).getIdMesa() <=30) {
                    ((Cliente) o).setEjeY(((Cliente) o).getEjeY() + 25);
                    ((Cliente) o).setEjeY(((Cliente) o).getEjeY() + 5);
                    System.out.println("ROUT 04 " + ((Cliente) o).getEjeY());
                    clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                }

                //RUTA 06 INFERIOR CAMINO HACIA MESAS - COMPLEMENTA RUTA 05
                if (clientesArray[0].getCenterX() >= 340 && clientesArray[0].getCenterY() >= 550 && clientesArray[0].getCenterY() <= 570 && clientesArray[0].getCenterX() <= 1260) {
                    ((Cliente) o).setEjeX(((Cliente) o).getEjeX() + 5);
                    System.out.println("ROUT 03 " + ((Cliente) o).getEjeX());
                    clientesArray[0].setCenterX(((Cliente) o).getEjeX());

                    //CONTROLADORES DE POSICIONES DE MESAS


                    if (clientesArray[0].getCenterX() >= 460 && clientesArray[0].getCenterX()<= 475 && ((Cliente) o).getIdMesa() == 15) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 202); //202
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 590 && clientesArray[0].getCenterX()<= 605 && ((Cliente) o).getIdMesa() == 16) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 202); //202
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 730 && clientesArray[0].getCenterX()<= 745 && ((Cliente) o).getIdMesa() == 17) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 202); //202
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 864 && clientesArray[0].getCenterX()<= 880 && ((Cliente) o).getIdMesa() == 18) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 202); //202
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 998 && clientesArray[0].getCenterX()<= 1013 && ((Cliente) o).getIdMesa() == 19) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 202); //202
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 1132 && clientesArray[0].getCenterX()<= 1147 && ((Cliente) o).getIdMesa() == 20) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 202); //202
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 1254 && clientesArray[0].getCenterX()<= 1264 && ((Cliente) o).getIdMesa() == 21) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 202); //202
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    //NIVEL INFERIOR DE LA PRIMERA SECCION

                    if (clientesArray[0].getCenterX() >= 460 && clientesArray[0].getCenterX()<= 475 && ((Cliente) o).getIdMesa() == 22) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 62);
                        System.out.println("ALAKMSKAL: " + ((Cliente) o).getEjeY());
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 590 && clientesArray[0].getCenterX()<= 605 && ((Cliente) o).getIdMesa() == 23) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 62);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 730 && clientesArray[0].getCenterX()<= 745 && ((Cliente) o).getIdMesa() == 24) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 62);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 864 && clientesArray[0].getCenterX()<= 880 && ((Cliente) o).getIdMesa() == 25) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 62);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 998 && clientesArray[0].getCenterX()<= 1013 && ((Cliente) o).getIdMesa() == 26) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 62);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 1132 && clientesArray[0].getCenterX()<= 1147 && ((Cliente) o).getIdMesa() == 27) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 62);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 1254 && clientesArray[0].getCenterX()<= 1264 && ((Cliente) o).getIdMesa() == 28) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() - 62);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 1200 && clientesArray[0].getCenterX()<= 1215 && ((Cliente) o).getIdMesa() == 29) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() + 115);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }

                    if (clientesArray[0].getCenterX() >= 1254 && clientesArray[0].getCenterX()<= 1264 && ((Cliente) o).getIdMesa() == 30) {
                        ((Cliente) o).setEjeY(((Cliente) o).getEjeY() + 115);
                        clientesArray[0].setCenterY(((Cliente) o).getEjeY());
                    }
                }


            }
        });
    }

    public void createTestClient() {
        Cliente clienteTest = new Cliente(x, y, idM++);
        new Thread(clienteTest).start();
        clienteTest.addObserver(this);
    }
}
