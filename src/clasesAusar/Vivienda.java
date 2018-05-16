
package clasesAusar;


public class Vivienda {
    
    private int NDeRol;
    private String nombre;
    private String tipoDeVivienda;
    private int precioDeArriendo;
    private int precioDeVenta;
    private int cantBanios;
    private int cantPiezas;
    private String direccion;
    private String condicion;

    public Vivienda(int NDeRol, String nombre, String tipoDeVivienda, int precioDeArriendo, int precioDeVenta, int cantBanios, int cantPiezas, String direccion, String condicion) {
        this.NDeRol = NDeRol;
        this.nombre = nombre;
        this.tipoDeVivienda = tipoDeVivienda;
        this.precioDeArriendo = precioDeArriendo;
        this.precioDeVenta = precioDeVenta;
        this.cantBanios = cantBanios;
        this.cantPiezas = cantPiezas;
        this.direccion = direccion;
        this.condicion = condicion;
    }

    public int getNDeRol() {
        return NDeRol;
    }

    public void setNDeRol(int NDeRol) {
        this.NDeRol = NDeRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDeVivienda() {
        return tipoDeVivienda;
    }

    public void setTipoDeVivienda(String tipoDeVivienda) {
        this.tipoDeVivienda = tipoDeVivienda;
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
        return "Vivienda{" + "NDeRol=" + NDeRol + ", nombre=" + nombre + ", tipoDeVivienda=" + tipoDeVivienda + ", precioDeArriendo=" + precioDeArriendo + ", precioDeVenta=" + precioDeVenta + ", cantBanios=" + cantBanios + ", cantPiezas=" + cantPiezas + ", direccion=" + direccion + ", condicion=" + condicion + '}';
    }
    
    
    
    
}
