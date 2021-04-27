package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import sample.Model.cluster;
import sample.Model.estadoPista;
import sample.Model.plane;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Controller implements Observer {
    @FXML
    public AnchorPane layoutMain;

    @FXML
    public Circle led1;

    @FXML
    public Circle led2;

    @FXML
    public Circle led3;

    @FXML
    public Rectangle ledAlert;

    @FXML
    public Text txtPlazas;

    @FXML
    public Text txtDespegue;

    @FXML
    public Text txtEstado;

    @FXML
    public Text txtClosed;

    @FXML
    public Rectangle airSpace;

    Circle[] avionesArray = new Circle[50];
    Circle[] avionesAterrizar = new Circle[20];

    int x = 577, y = 140, igPlane = 0;
    estadoPista estado = new estadoPista();
    ArrayList avionesDespegados = new ArrayList();
    ArrayList<cluster> clusterOcupados = new ArrayList<cluster>();
    int avionG = 0;
    int avionGData = 0;
    int contador = 0;
    int contadoGAvionesA = 0;
    Semaphore mutex = new Semaphore(0);
    Semaphore lleno = new Semaphore(1);
    boolean accesoGNA = false;
    int contadorOcupado = 0;
    int contadorDespegues = 0;
    int auxD = 0;

    @FXML
    public void starAirport() throws InterruptedException {
        Random random = new Random(System.currentTimeMillis());

        plane avion;
        int avionesGenerados;

        avionesGenerados = random.nextInt(15) + 5;

        int primerCluster = 1; //int primerCluster = 1;
        avionGData = primerCluster;
        avionesDespegados.add(primerCluster);
        avionesDespegados.add(1);
        estado.setData(avionesDespegados);
        estado.setAvionesGenerados(avionesGenerados);
        avionG = avionesGenerados;

        if (primerCluster == 0) {
            estado.setAccesoDespegue(false);
            avionesGenerados = 8; //new line
        }

        for (int i = 1; i <= 20; i++) {
            clusterOcupados.add(new cluster(i, false));
        }

        for (int i = 1; i < avionesGenerados; i++) {
            estado.setId(primerCluster);
            avion = new plane(String.valueOf(i), x, y, estado, mutex, lleno, i);
            avion.addObserver(this);
            new Thread(avion).start();
            new Thread(avion).join();
            clusterOcupados.get(i).setOcupado(true);
            clusterOcupados.get(i).setPrimary(true);
            estado.entra();


            if (i == 1) {
                x += 0;
            } else if (i == 2) {
                x += 83;
            } else if (i == 3) {
                x += 158;
            } else if (i >= 4 && i < 17 || i == 18) {
                x += 96;
            } else if (i == 17) {
                x += 195;
            }

            Image img = new Image(getClass().getResourceAsStream("images/C.png"));
            Circle avionData = new Circle(x, y, 60, Color.ORANGE);
            avionData.setId(avion.getId());
            avionData.setFill(new ImagePattern(img));
            avionesArray[i] = avionData;
            layoutMain.getChildren().add(avionData);
            igPlane++;
        }

        estado.setClusterOcupados((ArrayList<cluster>) clusterOcupados.clone());
        estado.setAccesoDespegue(false);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(() -> {
            if (o instanceof plane) {
                if (Integer.parseInt(((plane) o).getId()) <= igPlane) {
                    if (avionesArray[Integer.parseInt(((plane) o).getId())].getId().equals("1")) {
                        avionesArray[Integer.parseInt(((plane) o).getId())].setVisible(false);
                    }

                    if (!((plane) o).isAvionTypeA()) {
                        avionesArray[Integer.parseInt(((plane) o).getId())].setCenterX(((plane) o).getEjeX());
                        avionesArray[Integer.parseInt(((plane) o).getId())].setCenterY(((plane) o).getEjeY());
                    }

                    if (avionesArray[Integer.parseInt(((plane) o).getId())].getCenterX() <= 100) {
                        avionesArray[Integer.parseInt(((plane) o).getId())].setVisible(false);
                    }

                    if (estado.isAccesoDespegue()) {
                        led1.setFill(Color.RED);
                        led2.setFill(Color.RED);
                        led3.setFill(Color.RED);
                        ledAlert.setFill(Color.RED);
                        txtEstado.setText("DESPEGANDO");

                    } else {
                        led1.setFill(Color.GREEN);
                        led2.setFill(Color.GREEN);
                        led3.setFill(Color.GREEN);
                        ledAlert.setFill(Color.GREEN);
                        txtEstado.setText("ATERRIZAJE");

                    }

                    if (estado.ocupadoConsult() == 18) {
                        ledAlert.setFill(Color.RED);
                    }

                    if (((plane) o).isAterrizadoReacoplar()) {
                        avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setFill(Color.BLUEVIOLET);
                    }

                }

                if (((plane) o).isEstadoVolando()) {
                    avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterX(((plane) o).getEjeX());
                    avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(((plane) o).getEjeY());

                    /*System.out.println(estado.getAvionesAterrizados());
                    System.out.println(estado.ocupadoConsult());*/

                    if (estado.isAccesoDespegue()) {
                        contador = 0;
                    }

                    if (((plane) o).getCluster() == 3 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 575 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 585 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            } else {
                                estado.setAccesoDespegue(false);
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 4 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 670 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 685 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            } else {
                                estado.setAccesoDespegue(false);
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 5 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 815 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 825 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            } else {
                                estado.setAccesoDespegue(false);
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 6 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 905 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 915 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            } else {
                                estado.setAccesoDespegue(false);
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 7 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 1005 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 1015 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            } else {
                                estado.setAccesoDespegue(false);
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 8 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 1100 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 1115 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            } else {
                                estado.setAccesoDespegue(false);
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 9 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 1200 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 1215 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            } else {
                                estado.setAccesoDespegue(false);
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 10 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 1295 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 1305 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            } else {
                                estado.setAccesoDespegue(false);
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 11 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 1392 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 1402 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 12 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 1485 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 1495 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 13 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 1582 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 1592 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 14 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 1677 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 1687 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 15 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 1772 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 1782 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 16 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 1870 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 1880 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 17 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 1960 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 1975 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 130);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 18 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 2055 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 2065 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("18 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() - 10);
                                //System.out.println("AA 77");
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 19 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 2270 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 2290 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //   System.out.println("19 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            ((plane) o).setEjeX(((plane) o).getEjeX() - 20);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);

                            // avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);

                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() + 10);
                                //  System.out.println("AA 77");
                            }
                        }
                    }

                    if (((plane) o).getCluster() == 20 && !((plane) o).isAcoplar()) {
                        if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() > 2350 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 2370 && avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() >= 125 && ((plane) o).isAccesoAterrizaje()) {
                            //  System.out.println("20 ACCESS");
                            ((plane) o).setEjeY(((plane) o).getEjeY() - 130);
                            avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setCenterY(avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterY() - 85);
                            ((plane) o).setAcoplar(true);
                            //   ((plane) o).setEstadoVolando(false);
                            //   avionesAterrizar[Integer.parseInt(((plane) o).getId()) -1].setVisible(false);
                            if (estado.ocupadoConsult() > 3) {
                                estado.setAccesoDespegue(true);

                            } else {
                                estado.setAccesoDespegue(false);
                            }
                            settings(o);
                            // System.out.println("ACOPLAR");
                            //configAccess();
                        } else {
                            if (((plane) o).isAccesoAterrizaje()) {
                                ((plane) o).setEjeX(((plane) o).getEjeX() + 10);
                                //  System.out.println("AA 77");
                            }
                        }
                    }

                    if (avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].getCenterX() <= 100) {
                        avionesAterrizar[Integer.parseInt(((plane) o).getId()) - 1].setVisible(false);
                        estado.setAccesoDespegue(true);
                    }
                }

                if (!estado.isAccesoDespegue()) {
                    contador++;
                    if (contador == 1 || estado.ocupadoConsult() == 1 || estado.ocupadoConsult() == 2) {
                        plane avion;
                        int avionesGenerados;

                        int clusterAsignado = 0;
                        int idCluster = 0;
                        for (int i = 0; i <= estado.getClusterOcupados().size() - 1; i++) {
                            if (!estado.getClusterOcupados().get(i).isOcupado()) {
                                if (estado.getClusterOcupados().get(i).getIdCluster() != 1 && estado.getClusterOcupados().get(i).getIdCluster() != 2) {
                                    clusterAsignado = estado.getClusterOcupados().get(i).getIdCluster();
                                    idCluster = i;
                                }
                            }
                        }

                        if (clusterAsignado != 0) {
                            try {
                                avion = new plane(String.valueOf(contadoGAvionesA + 1), 76, 888, estado, mutex, lleno, true, clusterAsignado);
                                avion.setAvionTypeA(true);
                                estado.getClusterOcupados().get(idCluster).setOcupado(true);
                                avion.addObserver(this);
                                new Thread(avion).start();

                                Image img = new Image(getClass().getResourceAsStream("images/RA.png"));
                                Circle avionData = new Circle(76, 888, 60, Color.RED);
                                avionData.setFill(new ImagePattern(img));
                                avionData.setId(avion.getId());

                                avionesAterrizar[contadoGAvionesA] = avionData;
                                layoutMain.getChildren().add(avionData);
                                contadoGAvionesA++;
                                accesoGNA = false;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }

            }
        });
    }

    public void settings(Observable o) {
        ArrayList dataAux;
        int aux = 0;
        int data = estado.ocupadoConsult() - 1; //int data = estado.ocupadoConsult() -1;
        dataAux = estado.getData();
        estado.aterrizan();

        if (!dataAux.contains(data)) {
            estado.setId(data);
            dataAux.add(data);
            estado.setData(dataAux);
        } else {
            for (int i = 1; i < estado.getClusterOcupados().size() - 1; i++) {
                if (estado.getClusterOcupados().get(i).isOcupado()) {
                    contadorOcupado++;
                }
            }

            auxD = 0;

            contadorDespegues++;
            for (int i = 0; i < estado.getClusterOcupados().size() - 1; i++) {
                if (estado.getClusterOcupados().get(i).isPrimary() && estado.getClusterOcupados().get(i).isOcupado()) {
                    aux = estado.getClusterOcupados().get(i).getIdCluster() - contadorDespegues;
                }
            }

            if (contadorOcupado >= 8 && estado.ocupadoConsult() -1 >= 4) {

                estado.setAccesoDespegue(true);
                estado.setId(aux);
                dataAux.add(aux);
                estado.setData(dataAux);
                //System.out.println("DESPEGADO AAC: " + aux);

            } else {
                estado.setAccesoDespegue(false);
                contador = 0;
            }
            //System.out.println("CONTADOR OCUPADO:" + contadorOcupado);
            if (contadorOcupado == 18) {
                Platform.runLater(() -> {
                    txtClosed.setVisible(true);
                    airSpace.setFill(Color.RED);
                });
            }
            contadorOcupado = 0;


        }
        //System.out.println("COT: " + estado.ocupadoConsult());
        accesoGNA = true;

    }
}
