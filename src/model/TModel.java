package model;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import clasesAusar.VistaVivienda;

public class TModel implements TableModel {

    private List<Vivienda> listaViviendas;

    public TModel(List<Vivienda> listaViviendasDisponibles) {
        this.listaViviendas = listaViviendasDisponibles;
    }

    @Override
    public int getRowCount() {
        return listaViviendas.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Numero de Rol";
            case 1:
                return "Nombre";
            case 2:
                return "Tipo de vivienda";
            case 3:
                return "Precio de arriendo";
            case 4:
                return "Precio de venta";
            case 5:
                return "Cantidad de banios";
            case 6:
                return "Cantidad de piezas";
            case 7:
                return "Direccion";
            case 8:
                return "Condicion";
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
        Vivienda v = listaViviendas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return v.getnRol();
            case 1:
                return v.getNombre();
            case 2:
                return v.getTipo();
            case 3:
                return v.getPrecioArriendo();
            case 4:
                return v.getPrecioVenta();
            case 5:
                return v.getCantBaños();
            case 6:
                return v.getCantPiezas();
            case 7:
                return v.getDireccion();
            case 8:
                return v.getPrecioVenta();
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
