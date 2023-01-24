/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tables;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entity.Filijala;
import model.entity.Mesto;

/**
 *
 * @author HP
 */
public class TableModelFilijala extends AbstractTableModel{
    List<Filijala> list;
    final String[] columnNames = {"Id", "Mesto"};
    final Class[] columnClasses = {Integer.class, String.class};

    public TableModelFilijala(List<Filijala> list) {
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
        Filijala f = this.list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return f.getIdFilijala();
            case 1:
                return f.getMesto().getNaziv();
            default:
                return " ";
        }
    }
    
    public void setList(List<Filijala> list){
        this.list = list;
        fireTableDataChanged();
    }
    
    public Filijala getSelectedObject(int index){
        return this.list.get(index);
    }
}
