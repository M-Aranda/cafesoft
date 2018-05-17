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
public class Usuario {
    private String run;
    private String nombre;
    private int tipo;

    public Usuario() {
    }

    public Usuario(String run, String nombre, int tipo) {
        this.run = run;
        this.nombre = nombre;
        this.tipo = tipo;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "run=" + run + ", nombre=" + nombre + ", tipo=" + tipo + '}';
    }
    
    
}
