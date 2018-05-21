/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesAusar;

/**
 *
 * @author Alex971
 */
public class VistaStatsViviendas extends VistaVivienda {
    private String runCliente;
    private String runVendedor;

    public VistaStatsViviendas(String runCliente, String runVendedor) {
        this.runCliente = runCliente;
        this.runVendedor = runVendedor;
    }

    public VistaStatsViviendas(String runCliente, String runVendedor, int nDeRol, String tipo, String disponibilidad, int precioDeArriendo, int precioDeVenta, int cantBanios, int cantPiezas, String direccion, String condicion) {
        super(nDeRol, tipo, disponibilidad, precioDeArriendo, precioDeVenta, cantBanios, cantPiezas, direccion, condicion);
        this.runCliente = runCliente;
        this.runVendedor = runVendedor;
    }

    public VistaStatsViviendas() {
    }

    public String getRunCliente() {
        return runCliente;
    }

    public void setRunCliente(String runCliente) {
        this.runCliente = runCliente;
    }

    public String getRunVendedor() {
        return runVendedor;
    }

    public void setRunVendedor(String runVendedor) {
        this.runVendedor = runVendedor;
    }

    @Override
    public String toString() {
        return "VistaStatsViviendas{" + "runCliente=" + runCliente + ", runVendedor=" + runVendedor + '}';
    }
    
    
}
