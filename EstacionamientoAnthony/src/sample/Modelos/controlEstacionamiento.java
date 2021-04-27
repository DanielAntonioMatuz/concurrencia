package sample.Modelos;

import java.util.ArrayList;

public class controlEstacionamiento {

    public int espacioTotal;
    public int espaciosOcupados;
    boolean accesoSalida;
    boolean accesoEntrada;
    public static int prioridadAcceso;
    public static int unidadSalir;
    public static ArrayList<Estacionamiento> controlEspacio;
    public static boolean estado;



    public static int getPrioridadAcceso() {
        return prioridadAcceso;
    }

    public static void setPrioridadAcceso(int prioridadAcceso) {
        controlEstacionamiento.prioridadAcceso = prioridadAcceso;
    }

    public static ArrayList<Estacionamiento> getControlEspacio() {
        return controlEstacionamiento.controlEspacio;
    }

    public static void setControlEspacio(ArrayList<Estacionamiento> controlEspacio) {
        controlEstacionamiento.controlEspacio = controlEspacio;
    }

    public int getEspacioTotal() {

        return espacioTotal;
    }

    public void setEspacioTotal(int espacioTotal) {

        this.espacioTotal = espacioTotal;
    }

    public int getEspaciosOcupados() {
        return espaciosOcupados;
    }

    public void setEspaciosOcupados(int espaciosOcupados) {

        this.espaciosOcupados = espaciosOcupados;
    }

    public boolean isAccesoSalida() {

        return accesoSalida;
    }

    public void setAccesoSalida(boolean accesoSalida) {

        this.accesoSalida = accesoSalida;
    }

    public boolean isAccesoEntrada() {
        return accesoEntrada;
    }

    public void setAccesoEntrada(boolean accesoEntrada) {

        this.accesoEntrada = accesoEntrada;
    }

    public static int getUnidadSalir() {
        return unidadSalir;
    }

    public static void setUnidadSalir(int unidadSalir) {
        controlEstacionamiento.unidadSalir = unidadSalir;
    }

    public static boolean isEstado() {
        return estado;
    }

    public static void setEstado(boolean estado) {
        controlEstacionamiento.estado = estado;
    }
}
