package sample.Modelos;

import java.util.Observable;
import java.util.concurrent.Semaphore;

public class Carro extends Observable implements Runnable {

    controlEstacionamiento control;

    Semaphore mutex;

    public int controladorX;
    public int conroladorY;
    public boolean accesoEntrada;
    public boolean accesoSalida;
    public int espacioEstacionamiento;
    private int idAuto;
    boolean estacionarse = false;
    boolean acceso = true;
    boolean filaA = true;
    int contador = 0;


    public Carro(int idAuto, int controladorX, int conroladorY, boolean accesoEntrada, boolean accesoSalida, int espacioEstacionamiento, Semaphore mutex) {
        this.idAuto = idAuto;
        this.controladorX = controladorX;
        this.conroladorY = conroladorY;
        this.accesoEntrada = accesoEntrada;
        this.accesoSalida = accesoSalida;
        this.espacioEstacionamiento = espacioEstacionamiento;
        this.mutex = mutex;
    }


    @Override
    public void run() {

        mutex.release();

        while (true) {

            if (idAuto == control.getPrioridadAcceso() && !control.isEstado()) {


                if (controladorX >= 1311 && controladorX <= 1386 && conroladorY >= 291 && conroladorY <= 940 && !estacionarse) {
                    setConroladorY(conroladorY - 18);
                }

                if (controladorX > 209 && controladorX < 1386 && conroladorY > 221 && conroladorY < 326 && !estacionarse) {
                    setControladorX(controladorX - 18);
                }

                if (controladorX > 162 && controladorX < 209 && conroladorY > 222 && conroladorY < 648 && !estacionarse) {
                    setConroladorY(conroladorY + 18);
                }

                if (controladorX > 160 && controladorX < 1150 && conroladorY > 648 && conroladorY < 670 && !estacionarse) {
                    setControladorX(controladorX + 18);
                }

                if (controladorX > 1100 && controladorX < 1175 && conroladorY > 640 && conroladorY < 938 && !estacionarse && espacioEstacionamiento != 19) {
                    setConroladorY(conroladorY + 18);
                }



                if (espacioEstacionamiento == 1) {
                    if (controladorX >= 1150 && controladorX <= 1170 && conroladorY >= 280 && conroladorY <= 430) {
                        setConroladorY(conroladorY + 18);
                        estacionarse = true;
                        asignar();
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 2) {
                    if (controladorX >= 1060 && controladorX <= 1080 && conroladorY >= 280 && conroladorY <= 430) {
                        setConroladorY(conroladorY + 18);
                        estacionarse = true;
                        asignar();
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 3) {
                    if (controladorX >= 960 && controladorX <= 980 && conroladorY >= 280 && conroladorY <= 430) {
                        setConroladorY(conroladorY + 18);
                        estacionarse = true;
                        asignar();
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 4) {
                    if (controladorX >= 880 && controladorX <= 900 && conroladorY >= 280 && conroladorY <= 430) {
                        setConroladorY(conroladorY + 18);
                        estacionarse = true;
                        asignar();
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 5) {
                    if (controladorX >= 780 && controladorX <= 800 && conroladorY >= 280 && conroladorY <= 430) {
                        setConroladorY(conroladorY + 18);
                        estacionarse = true;
                        asignar();
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 6) {
                    if (controladorX >= 700 && controladorX <= 720 && conroladorY >= 280 && conroladorY <= 430) {
                        setConroladorY(conroladorY + 18);
                        estacionarse = true;
                        asignar();
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 7) {
                    if (controladorX >= 600 && controladorX <= 620 && conroladorY >= 280 && conroladorY <= 430) {
                        setConroladorY(conroladorY + 18);
                        estacionarse = true;
                        asignar();
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 8) {
                    if (controladorX >= 520 && controladorX <= 540 && conroladorY >= 280 && conroladorY <= 430) {
                        setConroladorY(conroladorY + 18);
                        estacionarse = true;
                        asignar();
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 9) {
                    if (controladorX >= 420 && controladorX <= 440 && conroladorY >= 280 && conroladorY <= 430) {
                        setConroladorY(conroladorY + 18);
                        estacionarse = true;
                        asignar();
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 10) {
                    if (controladorX >= 330 && controladorX <= 350 && conroladorY >= 280 && conroladorY <= 430) {
                        setConroladorY(conroladorY + 18);
                        estacionarse = true;
                        asignar();
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 11) {
                    if (controladorX >= 340 && controladorX <= 360 && conroladorY >= 510 && conroladorY <= 650) {
                        setConroladorY(conroladorY - 18);
                        asignar();
                        estacionarse = true;
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 12) {
                    if (controladorX >= 440 && controladorX <= 460 && conroladorY >= 510 && conroladorY <= 650) {
                        setConroladorY(conroladorY - 18);
                        asignar();
                        estacionarse = true;
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 13) {
                    if (controladorX >= 620 && controladorX <= 640 && conroladorY >= 510 && conroladorY <= 650) {
                        setConroladorY(conroladorY - 18);
                        asignar();
                        estacionarse = true;
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 14) {
                    if (controladorX >= 710 && controladorX <= 725 && conroladorY >= 510 && conroladorY <= 650) {
                        setConroladorY(conroladorY - 18);
                        asignar();
                        estacionarse = true;
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 15) {
                    if (controladorX >= 890 && controladorX <= 910 && conroladorY >= 510 && conroladorY <= 650) {
                        setConroladorY(conroladorY - 18);
                        asignar();
                        estacionarse = true;
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 16) {
                    if (controladorX >= 980 && controladorX <= 1000 && conroladorY >= 510 && conroladorY <= 650) {
                        setConroladorY(conroladorY - 18);
                        asignar();
                        estacionarse = true;
                        // add mutex.acquare()
                    }
                }

                if (espacioEstacionamiento == 19) {
                    if (controladorX >= 1160 && controladorX <= 1180 && conroladorY >= 510 && conroladorY <= 650) {
                        setConroladorY(conroladorY - 18);
                        asignar();
                        estacionarse = true;
                        // add mutex.acquare()
                    }
                }

                //System.out.println("X: " + controladorX + " Y: " + conroladorY);


                if (estacionarse && conroladorY >= 420 && conroladorY <= 440) {
                    banderaAutoGenerar();
                }

                if (estacionarse && conroladorY >= 490 && conroladorY <= 510) {
                    banderaAutoGenerar();
                }

                if (controladorX > 1090 && controladorX < 1175 && conroladorY > 930 && !estacionarse) {
                    banderaAutoGenerar();
                }

            }


            if (idAuto == control.getUnidadSalir()) {

                accesoEntrada = false;


                if (controladorX >= 333 && controladorX <= 1187 && conroladorY >= 280 && conroladorY <= 440 && !accesoEntrada && acceso) {
                    setConroladorY(conroladorY - 18);
                    estacionarse = false;

                    if (filaA) {
                        mutex.release();
                    }

                    espacioEstacionamiento = 22;
                    control.setEstado(true);
                   // System.out.println("ESTACIONAMIENTO: " + getEspacioEstacionamiento());
                }

                if (controladorX >= 333 && controladorX <= 1187 && conroladorY >= 500 && conroladorY <= 660 && !accesoEntrada && acceso) {
                    setConroladorY(conroladorY + 18);
                    estacionarse = false;
                    if (filaA) {
                        mutex.release();
                    }
                    espacioEstacionamiento = 22;
                    control.setEstado(true);

                }


                if (controlEstacionamiento.getControlEspacio() != null) {
                    for (int i = 0; i < controlEstacionamiento.getControlEspacio().size(); i++) {
                        if (controlEstacionamiento.getControlEspacio().get(i).idCarro == idAuto) {
                            controlEstacionamiento.getControlEspacio().get(i).setDisponibilidad(true);
                            break;
                        }
                    }
                }

                if (controladorX > 209 && controladorX < 1386 && conroladorY > 221 && conroladorY < 326 && !estacionarse) {
                    setControladorX(controladorX - 18);
                }

                if (controladorX > 162 && controladorX < 209 && conroladorY > 222 && conroladorY < 648 && !estacionarse) {
                    setConroladorY(conroladorY + 18);
                    acceso = false;
                    control.setEstado(false);

                }

                if (controladorX > 160 && controladorX < 1150 && conroladorY > 648 && conroladorY < 670 && !estacionarse) {
                    setControladorX(controladorX + 18);
                    control.setEstado(false);

                }


                if (controladorX > 1100 && controladorX < 1175 && conroladorY > 640 && conroladorY < 938 && !estacionarse && espacioEstacionamiento != 19) {
                    setConroladorY(conroladorY + 18);
                }

                if (controladorX > 1090 && controladorX < 1175 && conroladorY > 930 && !estacionarse) {
                    if (idAuto == 19) {
                        control.setUnidadSalir(1);
                    } else {
                        control.setUnidadSalir(idAuto + 1);
                    }

                    try {
                        mutex.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // controlEstacionamiento.getControlEspacio().get(espacioEstacionamiento).setDisponibilidad(true);

                }

            }

            setChanged();
            notifyObservers(Thread.currentThread().getName());
            try {
                Thread.sleep(45);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void banderaAutoGenerar() {
        try {
            control.setPrioridadAcceso(idAuto + 1);
            control.setAccesoEntrada(true);
        } catch (Exception e) {
        }
    }

    public void asignar() {

        controlEstacionamiento.getControlEspacio().get(espacioEstacionamiento).setDisponibilidad(false);
        try {
            mutex.acquire();
        } catch (Exception e) {}
         /*if (controlEstacionamiento.getControlEspacio() != null) {
            for (int i = 0; i < controlEstacionamiento.getControlEspacio().size(); i++) {
                if (controlEstacionamiento.getControlEspacio().get(i).idCarro == idAuto) {
                    controlEstacionamiento.getControlEspacio().get(i).setDisponibilidad(false);
                    break;
                }
            }
        } */
    }

    public int getControladorX() {
        return controladorX;
    }

    public void setControladorX(int controladorX) {
        this.controladorX = controladorX;
    }

    public int getConroladorY() {
        return conroladorY;
    }

    public void setConroladorY(int conroladorY) {
        this.conroladorY = conroladorY;
    }

    public void setEspacioEstacionamiento(int espacioEstacionamiento) {
        this.espacioEstacionamiento = espacioEstacionamiento;
    }

    public int getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }
}
