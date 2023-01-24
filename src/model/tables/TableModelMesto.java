/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tables;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entity.Mesto;

/**
 *
 * @author HP
 */
public class TableModelMesto extends AbstractTableModel{
    
    List<Mesto> list;
    final String[] columnNames = {"ID", "Mesto"};
    final Class[] columnClasses = {Integer.class, String.class};

    public TableModelMesto(List<Mesto> list) {
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
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mesto m = this.list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return m.getIdMesto();
            case 1:
                return m.getNaziv();
            default:
                return " ";
        }
    }
    
    public void setList(List<Mesto> list){
        this.list = list;
        fireTableDataChanged();
    }
    
    public Mesto getSelectedObject(int index){
        return this.list.get(index);
    }
    
}
