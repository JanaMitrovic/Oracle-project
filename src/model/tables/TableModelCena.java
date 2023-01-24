/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tables;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entity.Cena;
import model.entity.Roba;

/**
 *
 * @author HP
 */
public class TableModelCena extends AbstractTableModel{
    
    List<Cena> list;
    final String[] columnNames = {"Roba", "Datum od", "Iznos"};

    public TableModelCena(List<Cena> list) {
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

//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//        return columnClasses[columnIndex];
//    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cena c = this.list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getRoba().getIdRoba();
            case 1:
                return c.getDatumOd();
            case 2:
                return c.getIznos();
            default:
                return " ";
        }
    }

    public void setList(List<Cena> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public Cena getSelectedObject(int index) {
        return this.list.get(index);
    }
}
