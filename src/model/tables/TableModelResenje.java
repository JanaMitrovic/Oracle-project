/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tables;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entity.Resenje;

/**
 *
 * @author HP
 */
public class TableModelResenje extends AbstractTableModel{
    
    List<Resenje> list;
    final String[] columnNames = {"Rb", "Godina", "Filijala", "Obrazac", "Datum donosenja", "Datum prijema",  "Poreski obveznik", "Ukupan iznos"};

    public TableModelResenje(List<Resenje> list) {
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
        Resenje r = this.list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getBroj();
            case 1:
                return r.getGodina();
            case 2:
                return r.getFilijala();
            case 3:
                return r.getObrazac();
            case 4:
                return r.getDatumDonosenja();
            case 5:
                return r.getDatumPrijema();
            case 6:
                return r.getPoreskiObveznik();
            case 7:
                return r.getUkupanIznos();
            default:
                return " ";
        }
    }

    public void setList(List<Resenje> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public Resenje getSelectedObject(int index) {
        return this.list.get(index);
    }
    
}
