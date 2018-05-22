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
public class VistaStatsVend {
    private String run;
    private String nombre;
    private int contratos;
    private int login;

    public VistaStatsVend() {
    }

    public VistaStatsVend(String run, String nombre, int contratos, int login) {
        this.run = run;
        this.nombre = nombre;
        this.contratos = contratos;
        this.login = login;
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

    public int getContratos() {
        return contratos;
    }

    public void setContratos(int contratos) {
        this.contratos = contratos;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "VistaStatsVend{" + "run=" + run + ", nombre=" + nombre + ", contratos=" + contratos + ", login=" + login + '}';
    }
    
}
