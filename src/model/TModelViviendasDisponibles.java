package model;

import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TModelViviendasDisponibles implements TableModel {

    private List<VistaVivienda> listaViviendasDisponibles;

    public TModelViviendasDisponibles(List<VistaVivienda> listaViviendasDisponibles) {
        this.listaViviendasDisponibles = listaViviendasDisponibles;
    }

    @Override
    public int getRowCount() {
        return listaViviendasDisponibles.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Numero de Rol";
            case 1:
                return "Tipo de vivienda";
            case 2:
                return "Precio de arriendo";
            case 3:
                return "Precio de venta";
            case 4:
                return "Cantidad de banios";
            case 5:
                return "Cantidad de piezas";
            case 6:
                return "Direccion";
            case 7:
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
                return Integer.class;
            case 3:
                return Integer.class;
            case 4:
                return Integer.class;
            case 5:
                return Integer.class;
            case 6:
                return String.class;
            case 7:
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
        VistaVivienda v = listaViviendasDisponibles.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return v.getnDeRol();
            case 1:
                return v.getTipo();
            case 2:
                return v.getPrecioDeArriendo();
            case 3:
                return v.getPrecioDeVenta();
            case 4:
                return v.getCantBanios();
            case 5:
                return v.getCantPiezas();
            case 6:
                return v.getDireccion();
            case 7:
                return v.getCondicion();
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
