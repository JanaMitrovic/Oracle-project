/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tables;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entity.Roba;
import model.entity.Zapisnik;

/**
 *
 * @author HP
 */
public class TableModelRoba extends AbstractTableModel {

    List<Roba> list;
    final String[] columnNames = {"Vrsta", "Marka", "Model", "Tip", "Jedinica mere", "Aktuelna cena"};

    public TableModelRoba(List<Roba> list) {
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
        Roba r = this.list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getVrsta();
            case 1:
                return r.getMarka();
            case 2:
                return r.getModel();
            case 3:
                return r.getTip();
            case 4:
                return r.getJm();
            case 5:
                return r.getAktuelnaCena();
            default:
                return " ";
        }
    }

    public void setList(List<Roba> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public Roba getSelectedObject(int index) {
        return this.list.get(index);
    }

}
