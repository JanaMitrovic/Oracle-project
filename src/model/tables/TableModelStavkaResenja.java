/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tables;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entity.StavkaResenja;

/**
 *
 * @author HP
 */
public class TableModelStavkaResenja extends AbstractTableModel{
    
    List<StavkaResenja> list;
    final String[] columnNames = {"Rb", "Datum od", "Datum do", "Vrsta prihoda", "Iznos"};
//    final Class[] columnClasses = {Integer.class, Integer.class, String.class, String.class, Date.class, Date.class, Date.class, String.class, String.class};

    public TableModelStavkaResenja(List<StavkaResenja> list) {
        this.list = list;
    }

    public TableModelStavkaResenja() {
        list = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//        return columnClasses[columnIndex];
//    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaResenja sr = this.list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getDatumOd();
            case 2:
                return sr.getDatumDo();
            case 3:
                return sr.getVrstaPrihoda();
            case 4:
                return sr.getIznos();
            default:
                return " ";
        }
    }

    public void setList(List<StavkaResenja> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public StavkaResenja getSelectedObject(int index) {
        return this.list.get(index);
    }
}
