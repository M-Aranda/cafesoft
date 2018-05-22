package model;

public class Venta {

    private int id;
    private String cliente;
    private String usuario;
    private int nRol;
    private String fecha;

    public Venta() {
    }

    public Venta(int id, String cliente, String usuario, int nRol, String fecha) {
        this.id = id;
        this.cliente = cliente;
        this.usuario = usuario;
        this.nRol = nRol;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getnRol() {
        return nRol;
    }

    public void setnRol(int nRol) {
        this.nRol = nRol;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", cliente=" + cliente + ", usuario=" + usuario + ", nRol=" + nRol + ", fecha=" + fecha + '}';
    }

}
