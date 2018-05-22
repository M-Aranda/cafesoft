package model;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TModelNoDisponibles implements TableModel {

    private List<VistaStatsViviendas> listaViviendas;

    public TModelNoDisponibles(List<VistaStatsViviendas> listaViviendas) {
        this.listaViviendas = listaViviendas;
    }

    @Override
    public int getRowCount() {
        return listaViviendas.size();
    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Numero de Rol";
            case 1:
                return "Tipo de Vivienda";
            case 2:
                return "Tipo de Contrato";
            case 3:
                return "Precio de arriendo";
            case 4:
                return "Precio de venta";
            case 5:
                return "Cantidad de baños";
            case 6:
                return "Cantidad de piezas";
            case 7:
                return "Direccion";
            case 8:
                return "RUN Cliente";
            case 9:
                return "RUN Vendedor";
            case 10:
                return "Fecha";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Integer.class;
            case 4:
                return Integer.class;
            case 5:
                return Integer.class;
            case 6:
                return Integer.class;
            case 7:
                return String.class;
            case 8:
                return String.class;
            case 9:
                return String.class;
            case 10:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VistaStatsViviendas v = listaViviendas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return v.getnDeRol();
            case 1:
                return v.getTipo();
            case 2:
                return v.getDisponibilidad();
            case 3:
                return v.getPrecioDeArriendo();
            case 4:
                return v.getPrecioDeVenta();
            case 5:
                return v.getCantBanios();
            case 6:
                return v.getCantPiezas();
            case 7:
                return v.getDireccion();
            case 8:
                return v.getRunCliente();
            case 9:
                return v.getRunVendedor();
            case 10:
                return v.getFecha();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // modificar la lista BD
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        //
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        //
    }

}
