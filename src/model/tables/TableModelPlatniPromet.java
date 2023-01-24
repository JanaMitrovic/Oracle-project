/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tables;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entity.PlatniPromet;

/**
 *
 * @author HP
 */
public class TableModelPlatniPromet extends AbstractTableModel{
    
    List<PlatniPromet> list;
    final String[] columnNames = {"Banka", "Broj racuna"};

    public TableModelPlatniPromet(List<PlatniPromet> list) {
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
        PlatniPromet pp = this.list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return pp.getBanka();
            case 1:
                return pp.getRacun();
            default:
                return " ";
        }
    }
    
    public void setList(List<PlatniPromet> list){
        this.list = list;
        fireTableDataChanged();
    }
    
    public PlatniPromet getSelectedObject(int index){
        return this.list.get(index);
    }
    
}
