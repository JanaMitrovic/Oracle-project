/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tables;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entity.Zapisnik;

/**
 *
 * @author HP
 */
public class TableModelOsnovno extends AbstractTableModel{
    
    List<Zapisnik> list;
    final String[] columnNames = {"Rb", "Godina", "Filijala", "Poreski Obveznik"};
    
    public TableModelOsnovno() {
        list = new ArrayList<>();
    }

    public TableModelOsnovno(List<Zapisnik> list) {
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
        Zapisnik z = this.list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return z.getBroj();
            case 1:
                return z.getGodina();
            case 2:
                return z.getFilijala();
            case 3:
                return z.getPoreskiObveznik().getPIB();
            default:
                return " ";
        }
    }

    public void setList(List<Zapisnik> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public Zapisnik getSelectedObject(int index) {
        return this.list.get(index);
    }
    
}
