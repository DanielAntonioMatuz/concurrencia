package sample.Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class plane extends Observable implements Runnable {
    private Semaphore mutex;
    private Semaphore lleno;
    private String id;
    private boolean estadoMovimiento;
    private boolean estadoCarril;
    private int ejeX;
    private int ejeY;
    private estadoPista estado;
    boolean accessConfig = false;
    boolean estadoVolando;
    boolean accesoAterrizaje = false;
    boolean acoplar = false;
    public int cluster;
    boolean aterrizadoReacoplar;
    boolean accesoAcoplamientoT = false;
    public boolean avionTypeA = false;


    public plane(String id, int x, int y, estadoPista estado, Semaphore mutex, Semaphore lleno, boolean volando, int cluster) throws InterruptedException {
        this.id = id;
        this.ejeX = x;
        this.ejeY = y;
        this.estadoMovimiento = false;
        this.estado = estado;
        this.mutex = mutex;
        this.estadoVolando = volando;
        this.cluster = cluster;
        this.aterrizadoReacoplar = false;
        this.lleno = lleno;
    }

    public plane(String id, int x, int y, estadoPista estado, Semaphore mutex, Semaphore lleno, int cluster) throws InterruptedException {
        this.id = id;
        this.ejeX = x;
        this.ejeY = y;
        this.estadoMovimiento = false;
        this.estado = estado;
        this.mutex = mutex;
        this.cluster = cluster;
        this.aterrizadoReacoplar = false;
        this.lleno = lleno;
    }

    @Override
    public void run() {
        while (true) {
            try {
                curso();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void curso() throws InterruptedException {
        ejeX = getEjeX();
        ejeY = getEjeY();

        if ( ((estado.ocupadoConsult() - 1) + estado.getAvionesAterrizados() ) == 18 || estado.getAvionesAterrizados() == 17) {
            lleno.acquire();
        }

        if (estado.idPlane() == Integer.parseInt(this.id) && estado.isAccesoDespegue()) {

            if (ejeX >= 548 && ejeX < 2310 && ejeY >= 830 && ejeY <= 845) {
                setEjeX(airport.pista1(ejeX, ejeY));
            }

            if (ejeX >= 2309 && ejeY < 285) {
                setEjeY(airport.pista2(ejeX, ejeY));
            }

            if (ejeX >= 584 && ejeX <= 2400 && ejeY >= 245 && ejeY <= 313) {
                setEjeX(airport.pista3(ejeX, ejeY));
            }


            if (ejeX >= 565 && ejeX <= 640 && ejeY >= 247 && ejeY < 460) {
                setEjeY(airport.pista4(ejeX, ejeY));
                if (!accesoAterrizaje) {
                    try{
                        estado.getClusterOcupados().get(Integer.parseInt(id)).setOcupado(false);
                    } catch (Exception e){}
                }
            }

            if (ejeX < 2080 && ejeY >= 460 && ejeY <= 480) {
                setEjeX(airport.pista5(ejeX, ejeY));
            }

            if (ejeX >= 2060 && ejeY > 441 && ejeY < 840) {
                setEjeY(airport.pista6(ejeX, ejeY));
            }

            if (ejeX >= 0 && ejeY >= 830) {
                setEjeX(airport.pista7(ejeX, ejeY));
                if (ejeX <= 40) {
                    setEstadoMovimiento(false);
                    estado.sale();
                    setAccesoAcoplamientoT(false);


                    mutex.acquire();


                    try {
                        ArrayList dataAux;
                        Random random = new Random(System.currentTimeMillis());
                        int data = random.nextInt(estado.ocupadoConsult()); //int data = random.nextInt(estado.ocupadoConsult());
                        dataAux = estado.getData();
                        // System.out.println("DATA: " + data + " EC: " + estado.ocupadoConsult());
                        if (data == 1) {
                            data += 1;
                        } else if (data == 0) {
                            estado.setAccesoDespegue(false);
                        }

                        if (!dataAux.contains(data)) {
                            estado.setId(data);
                            dataAux.add(data);
                            estado.setData(dataAux);
                            accessConfig = true;
                            accesoAterrizaje = false;
                        } else {
                            accessConfig = false;
                        }


                        if (!accessConfig) {
                            estado.setAccesoDespegue(false);
                        }

                    } catch (Exception e) {
                    }

                }

            }

            if (ejeX >= 577 && ejeX <= 2395 && ejeY >= 140 && ejeY <= 247 && estado.isAccesoDespegue()) {
                setEjeY(airport.puerta(ejeX, ejeY));
                setEstadoMovimiento(true);
                setAccesoAcoplamientoT(true);
                mutex.release();
            }


        }


        if (accesoAcoplamientoT) {

        }

        if (!estadoVolando) {
            Thread.sleep(45);
            /*setChanged();
            notifyObservers(Thread.currentThread().getName());*/
            mutex.release();
        }

        if (estadoVolando) {
            if (ejeX >= 0 && ejeX <= 2240 && ejeY >= 830 && !accesoAterrizaje) {
                setEjeX(airport.pista1R(ejeX, ejeY));
                mutex.release();
            }

            if (ejeX >= 2240 && ejeY >= 284) {
                setEjeY(ejeY - 20);

                if (ejeY < 284) {
                    accesoAterrizaje = true;
                }
            }
            Thread.sleep(15);

        }
        setChanged();
        notifyObservers(Thread.currentThread().getName());
        mutex.release();

    }

    public String getId() {
        return id;
    }

    public boolean isAccesoAterrizaje() {
        return accesoAterrizaje;
    }

    public boolean isAcoplar() {
        return acoplar;
    }

    public void setAcoplar(boolean acoplar) {
        this.acoplar = acoplar;
    }

    public void setEstadoMovimiento(boolean estadoMovimiento) {
        this.estadoMovimiento = estadoMovimiento;
    }

    public int getEjeX() {
        return ejeX;
    }

    public void setEjeX(int ejeX) {
        this.ejeX = ejeX;
    }

    public int getEjeY() {
        return ejeY;
    }

    public boolean isEstadoVolando() {
        return estadoVolando;
    }


    public void setEjeY(int ejeY) {
        this.ejeY = ejeY;
    }

    public int getCluster() {
        return cluster;
    }

    public boolean isAterrizadoReacoplar() {
        return aterrizadoReacoplar;
    }


    public void setAccesoAcoplamientoT(boolean accesoAcoplamientoT) {
        this.accesoAcoplamientoT = accesoAcoplamientoT;
    }

    public boolean isAvionTypeA() {
        return avionTypeA;
    }

    public void setAvionTypeA(boolean avionTypeA) {
        this.avionTypeA = avionTypeA;
    }
}
