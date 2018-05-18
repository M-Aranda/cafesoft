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
public class Cliente {

    private String run;
    private String nombre;
    private int sueldo;

    public Cliente() {
    }

    public Cliente(String run, String nombre, int sueldo) {
        this.run = run;
        this.nombre = nombre;
        this.sueldo = sueldo;
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

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "run=" + run + ", nombre=" + nombre + ", sueldo=" + sueldo + '}';
    }

}
