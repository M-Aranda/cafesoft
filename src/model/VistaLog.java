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
public class VistaLog {
    
    private String fecha;
    private String descripcion;    
    private String run;
    private String nombre;

    public VistaLog() {
    }

    public VistaLog(String fecha, String descripcion, String run, String nombre) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.run = run;
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "VistaLog{" + "fecha=" + fecha + ", descripcion=" + descripcion + ", run=" + run + ", nombre=" + nombre + '}';
    }   
}
