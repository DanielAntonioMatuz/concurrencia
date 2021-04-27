package sample.Modelos;

public class Estacionamiento {

    public boolean disponibilidad;
    public int idEspacio;
    public int idCarro;

    public Estacionamiento(boolean disponibilidad, int idEspacio, int idCarro) {
        this.disponibilidad = disponibilidad;
        this.idEspacio = idEspacio;
        this.idCarro = idCarro;
    }


    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(int idEspacio) {
        this.idEspacio = idEspacio;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }
}
