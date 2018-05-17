/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Alex971
 */
public class Vivienda {

    private int nRol;
    private String nombre;
    private int tipo;
    private int precioArriendo;
    private int precioVenta;
    private int cantBaños;
    private int cantPiezas;
    private String direccion;
    private boolean usada;

    public Vivienda() {
    }

    public Vivienda(int nRol, String nombre, int tipo, int precioArriendo, int precioVenta, int cantBaños, int cantPiezas, String direccion, boolean usada) {
        this.nRol = nRol;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioArriendo = precioArriendo;
        this.precioVenta = precioVenta;
        this.cantBaños = cantBaños;
        this.cantPiezas = cantPiezas;
        this.direccion = direccion;
        this.usada = usada;
    }

    public int getnRol() {
        return nRol;
    }

    public void setnRol(int nRol) {
        this.nRol = nRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getPrecioArriendo() {
        return precioArriendo;
    }

    public void setPrecioArriendo(int precioArriendo) {
        this.precioArriendo = precioArriendo;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getCantBaños() {
        return cantBaños;
    }

    public void setCantBaños(int cantBaños) {
        this.cantBaños = cantBaños;
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

    public boolean isUsada() {
        return usada;
    }

    public void setUsada(boolean usada) {
        this.usada = usada;
    }

    @Override
    public String toString() {
        return "Vivienda{" + "nRol=" + nRol + ", nombre=" + nombre + ", tipo=" + tipo + ", precioArriendo=" + precioArriendo + ", precioVenta=" + precioVenta + ", cantBa\u00f1os=" + cantBaños + ", cantPiezas=" + cantPiezas + ", direccion=" + direccion + ", usada=" + usada + '}';
    }

}
