/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tables;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entity.StavkaNaloga;

/**
 *
 * @author HP
 */
public class TableModelStavkaNaloga extends AbstractTableModel{

    List<StavkaNaloga> list;
    final String[] columnNames = {"Rb", "Datum od", "Datum do", "Vrsta prihoda", "Datum izdavanja"};

    public TableModelStavkaNaloga(List<StavkaNaloga> list) {
        this.list = list;
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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaNaloga sn = this.list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sn.getRb();
            case 1:
                return sn.getDatumOd();
            case 2:
                return sn.getDatumDo();
            case 3:
                return sn.getVrstaPrihoda();
            case 4:
                return sn.getDatumIzdavanja();
            default:
                return " ";
        }
    }

    public void setList(List<StavkaNaloga> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public StavkaNaloga getSelectedObject(int index) {
        return this.list.get(index);
    }
    
}
