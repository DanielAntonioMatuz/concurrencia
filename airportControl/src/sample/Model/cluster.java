package sample.Model;

public class cluster extends Thread {


    private int idCluster;
    private boolean ocupado;
    private boolean primary;

    public cluster(int id, boolean ocupado) {
        this.idCluster = id;
        this.ocupado = ocupado;
    }

    public cluster(int id, boolean ocupado, boolean primary) {
        this.idCluster = id;
        this.ocupado = ocupado;
        this.primary = primary;
    }

    public int getIdCluster() {
        return idCluster;
    }

    public void setIdCluster(int idCluster) {
        this.idCluster = idCluster;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}