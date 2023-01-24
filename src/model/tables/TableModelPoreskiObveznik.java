/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tables;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entity.PoreskiObveznik;

/**
 *
 * @author HP
 */
public class TableModelPoreskiObveznik extends AbstractTableModel {

    List<PoreskiObveznik> list;
    final String[] columnNames = {"PIB", "Naziv", "Adresa"};

    public TableModelPoreskiObveznik(List<PoreskiObveznik> list) {
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
        PoreskiObveznik po = this.list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return po.getPIB();
            case 1:
                return po.getIme() + " " + po.getTip();
            case 2:
                return po.getBroj().getUlica() + " " + po.getBroj() + ", " + po.getBroj().getUlica().getMesto();
            default:
                return " ";
        }
    }

    public void setList(List<PoreskiObveznik> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public PoreskiObveznik getSelectedObject(int index) {
        return this.list.get(index);
    }
}
