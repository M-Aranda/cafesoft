package model;

import clasesAusar.VistaStatsVend;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import clasesAusar.VistaVivienda;

public class TModelStatVendedores implements TableModel {

    private List<VistaStatsVend> listaVendStats;

    public TModelStatVendedores(List<VistaStatsVend> listaVendStats) {
        this.listaVendStats = listaVendStats;
    }

    @Override
    public int getRowCount() {
        return listaVendStats.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "R.U.N";
            case 1:
                return "NOMBRE";
            case 2:
                return "Nº CONTRATOS";
            case 3:
                return "Nº LOG-IN";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            case 3:
                return Integer.class;
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
        VistaStatsVend v = listaVendStats.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return v.getRun();
            case 1:
                return v.getNombre();
            case 2:
                return v.getContratos();
            case 3:
                return v.getLogin();
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
