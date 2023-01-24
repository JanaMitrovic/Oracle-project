/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tables;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.entity.Filijala;
import model.entity.Nalog;

/**
 *
 * @author HP
 */
public class TableModelNalog extends AbstractTableModel {

    List<Nalog> list;
    final String[] columnNames = {"Rb", "Godina", "Filijala", "Obrazac", "Datum izdavanja", "Datum urucenja", "Vreme urucenja", "Mesto", "Poreski obveznik"};

    public TableModelNalog(List<Nalog> list) {
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
        Nalog n = this.list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return n.getBroj();
            case 1:
                return n.getGodina();
            case 2:
                return n.getFilijala();
            case 3:
                return n.getObrazac();
            case 4:
                return n.getDatumIzdavanja();
            case 5:
                return n.getDatumUrucenja();
            case 6:
                return n.getVremeUrucenja();
            case 7:
                return n.getMesto();
            case 8:
                return n.getPoreskiObveznik();

            default:
                return " ";
        }
    }

    public void setList(List<Nalog> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public Nalog getSelectedObject(int index) {
        return this.list.get(index);
    }
}
