package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import sample.Modelos.Carro;
import sample.Modelos.Estacionamiento;
import sample.Modelos.controlEstacionamiento;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Controller implements Observer {

    @FXML
    public AnchorPane planoEstacionamiento;

    @FXML
    public Rectangle ledSalida;

    @FXML
    public Rectangle ledEntrada;

    Circle[] arregloAutos = new Circle[20];
    int movimientoX = 1376, movimientoY = 938;
    int contador = 0;
    Semaphore mutex = new Semaphore(0);
    ArrayList<Estacionamiento> espaciosOcupados = new ArrayList<Estacionamiento>();

    controlEstacionamiento estacionamiento = new controlEstacionamiento();

    @FXML
    public void iniciarEstacionamiento() {
        Carro carroUsuario;
        estacionamiento.setEstado(false);

        estacionamiento.setPrioridadAcceso(1);
        estacionamiento.setUnidadSalir(16);

        for (int y=1; y <=15; y++) {
            if (y == 1) {
                espaciosOcupados.add(new Estacionamiento(false, y, y));
            } else {
                espaciosOcupados.add(new Estacionamiento(true, y, y));
            }
        }

        //carros que se generan afuerta del estacionamiento / se cambio de i = 0 a i = 1
        for (int i = 1; i <= 15; i++) {
            System.out.println("NO ESTACIONADOS: " + i);
            carroUsuario = new Carro(i, movimientoX, movimientoY, true, false, i, mutex);
            carroUsuario.addObserver(this);
            new Thread(carroUsuario).start();

            Image ImagenCarro = new Image(getClass().getResourceAsStream("imagenes/car.png"));
            Circle carro = new Circle(movimientoX,movimientoY,35, Color.AQUA);
            carro.setFill(new ImagePattern(ImagenCarro));
            arregloAutos[i] = carro;
            planoEstacionamiento.getChildren().add(carro);

        }

        //carros que se generan estacionados
        for (int i = 16; i <= 19; i++) {

            System.out.println("ESTACIONADOS: " + i);

            Image ImagenCarro = new Image(getClass().getResourceAsStream("imagenes/car.png"));
            Circle carro = new Circle();
            if (i == 16) {
                carro = new Circle(347,417,35, Color.AQUA);
                carroUsuario = new Carro(i, 347, 417, false, true, i, mutex);
                carroUsuario.addObserver(this);
                new Thread(carroUsuario).start();
                arregloAutos[i] = carro;

            } else if (i == 17) {
                carro = new Circle(438,523,35, Color.AQUA);
                carroUsuario = new Carro(i, 428, 523, false, true, i, mutex);
                carroUsuario.addObserver(this);
                new Thread(carroUsuario).start();
                arregloAutos[i] = carro;

            } else if (i == 18) {
                carro = new Circle(530,417,35, Color.AQUA);
                carroUsuario = new Carro(i, 530, 417, false, true, i, mutex);
                carroUsuario.addObserver(this);
                new Thread(carroUsuario).start();
                arregloAutos[i] = carro;

            } else if (i == 19) {
                carro = new Circle(981,523,35, Color.AQUA);
                carroUsuario = new Carro(i, 981, 523, false, true, i, mutex);
                carroUsuario.addObserver(this);
                new Thread(carroUsuario).start();
                arregloAutos[i] = carro;

            }



            carro.setFill(new ImagePattern(ImagenCarro));
            planoEstacionamiento.getChildren().add(carro);

            espaciosOcupados.add(new Estacionamiento(false, i, i));


        }


        controlEstacionamiento.setControlEspacio(espaciosOcupados);

       /* for (int i =0; i <= espaciosOcupados.size() - 1; i++) {
            System.out.println(espaciosOcupados.get(i).idCarro + " | " + espaciosOcupados.get(i).idEspacio);
        }*/
        Random random = new Random(System.currentTimeMillis());
        int valor = random.nextInt(16) + 3;


    }

    boolean acceso = true;

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            if (o instanceof Carro) {

                if (controlEstacionamiento.isEstado()) {

                    ledSalida.setFill(Color.GREEN);
                    ledEntrada.setFill(Color.RED);


                } else {
                    ledEntrada.setFill(Color.GREEN);
                    ledSalida.setFill(Color.GREEN);
                }

                try {
                    if (estacionamiento.getPrioridadAcceso() <= 19) {
                        if (((Carro) o).getIdAuto() == estacionamiento.getPrioridadAcceso()) {
                            arregloAutos[((Carro) o).getIdAuto()].setCenterX(((Carro) o).getControladorX());
                            arregloAutos[((Carro) o).getIdAuto()].setCenterY(((Carro) o).getConroladorY());
                            /* aqui se hace el cambio de ID de estacionamiento del auto, se debera buscar los espacios
                            disponibles restante */

                            if (estacionamiento.getPrioridadAcceso() != 1) {
                                if (estacionamiento.getControlEspacio() != null) {
                                    for (int i = 0; i < estacionamiento.getControlEspacio().size(); i++) {
                                        if (estacionamiento.getControlEspacio().get(i).isDisponibilidad()) {
                                           // System.out.println(estacionamiento.getControlEspacio().get(i).getIdEspacio());
                                            break;
                                        }
                                    }
                                }
                            }

                            if (((Carro) o).getIdAuto() == 7) {
                                ((Carro) o).setEspacioEstacionamiento(1);
                            }


                            if (((Carro) o).getIdAuto() == 8) {
                                ((Carro) o).setEspacioEstacionamiento(2);
                            }

                            if (((Carro) o).getIdAuto() == 9) {
                                ((Carro) o).setEspacioEstacionamiento(3);
                            }

                            if (((Carro) o).getIdAuto() == 10) {
                                ((Carro) o).setEspacioEstacionamiento(4);
                            }

                            if (((Carro) o).getIdAuto() == 12) {
                                ((Carro) o).setEspacioEstacionamiento(6);
                            }

                            if (((Carro) o).getIdAuto() == 15) {
                                ((Carro) o).setEspacioEstacionamiento(16);
                            }
                        }
                    }
                } catch (Exception e) {}

                try {
                    if (estacionamiento.getPrioridadAcceso() <= 19) {
                        if (((Carro) o).getIdAuto() == estacionamiento.getUnidadSalir()) {
                            arregloAutos[((Carro) o).getIdAuto()].setCenterX(((Carro) o).getControladorX());
                            arregloAutos[((Carro) o).getIdAuto()].setCenterY(((Carro) o).getConroladorY());
                        }
                    }
                } catch (Exception e) {}
            }
        });
    }
}
