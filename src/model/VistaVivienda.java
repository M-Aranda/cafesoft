
package model;


public class VistaVivienda {
    
    private int nDeRol;
    private String tipo;
    private String disponibilidad;
    private int precioDeArriendo;
    private int precioDeVenta;
    private int cantBanios;
    private int cantPiezas;
    private String direccion;
    private String condicion;

    public VistaVivienda() {
    }

    public VistaVivienda(int nDeRol, String tipo, String disponibilidad, int precioDeArriendo, int precioDeVenta, int cantBanios, int cantPiezas, String direccion, String condicion) {
        this.nDeRol = nDeRol;
        this.tipo = tipo;
        this.disponibilidad = disponibilidad;
        this.precioDeArriendo = precioDeArriendo;
        this.precioDeVenta = precioDeVenta;
        this.cantBanios = cantBanios;
        this.cantPiezas = cantPiezas;
        this.direccion = direccion;
        this.condicion = condicion;
    }

    public int getnDeRol() {
        return nDeRol;
    }

    public void setnDeRol(int nDeRol) {
        this.nDeRol = nDeRol;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getPrecioDeArriendo() {
        return precioDeArriendo;
    }

    public void setPrecioDeArriendo(int precioDeArriendo) {
        this.precioDeArriendo = precioDeArriendo;
    }

    public int getPrecioDeVenta() {
        return precioDeVenta;
    }

    public void setPrecioDeVenta(int precioDeVenta) {
        this.precioDeVenta = precioDeVenta;
    }

    public int getCantBanios() {
        return cantBanios;
    }

    public void setCantBanios(int cantBanios) {
        this.cantBanios = cantBanios;
    }

    public int getCantPiezas() {
        return cantPiezas;
    }

    public void setCantPiezas(int cantPiezas) {
        this.cantPiezas = cantPiezas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    @Override
    public String toString() {
        return "VistaVivienda{" + "nDeRol=" + nDeRol + ", tipo=" + tipo + ", disponibilidad=" + disponibilidad + ", precioDeArriendo=" + precioDeArriendo + ", precioDeVenta=" + precioDeVenta + ", cantBanios=" + cantBanios + ", cantPiezas=" + cantPiezas + ", direccion=" + direccion + ", condicion=" + condicion + '}';
    }
}
