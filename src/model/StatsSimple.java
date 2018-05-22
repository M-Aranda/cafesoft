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
public class StatsSimple {
    private int casas;
    private int departamentos;
    private int todas;

    public StatsSimple() {
    }

    public StatsSimple(int casas, int departamentos, int todas) {
        this.casas = casas;
        this.departamentos = departamentos;
        this.todas = todas;
    }

    public int getCasas() {
        return casas;
    }

    public void setCasas(int casas) {
        this.casas = casas;
    }

    public int getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(int departamentos) {
        this.departamentos = departamentos;
    }

    public int getTodas() {
        return todas;
    }

    public void setTodas(int todas) {
        this.todas = todas;
    }
    
 
}
