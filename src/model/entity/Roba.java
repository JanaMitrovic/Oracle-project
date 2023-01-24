/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.DomainObject;

/**
 *
 * @author HP
 */
public class Roba extends DomainObject{
    
    private int IdRoba;
    private String vrsta;
    private String marka;
    private String model;
    private String tip;
    private JedinicaMere jm;
    private double aktuelnaCena;
    private boolean cenaIzmena = false;

    public Roba() {
    }

    public Roba(int IdRoba, String vrsta, String marka, String model, String tip, JedinicaMere jm, double aktuelnaCena) {
        this.IdRoba = IdRoba;
        this.vrsta = vrsta;
        this.marka = marka;
        this.model = model;
        this.tip = tip;
        this.jm = jm;
        this.aktuelnaCena = aktuelnaCena;
    }

    public double getAktuelnaCena() {
        return aktuelnaCena;
    }

    public void setAktuelnaCena(double aktuelnaCena) {
        this.aktuelnaCena = aktuelnaCena;
    }

    public int getIdRoba() {
        return IdRoba;
    }

    public void setIdRoba(int IdRoba) {
        this.IdRoba = IdRoba;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public JedinicaMere getJm() {
        return jm;
    }

    public void setJm(JedinicaMere jm) {
        this.jm = jm;
    }

    public boolean isCenaIzmena() {
        return cenaIzmena;
    }

    public void setCenaIzmena(boolean cenaIzmena) {
        this.cenaIzmena = cenaIzmena;
    }
    
    @Override
    public String getTableName() {
        return "Roba r";
    }

    @Override
    public String getAllColumnNames() {
        return "r.IdRoba, r.vrsta, r.marka, r.model, r.tip, j.IdJedinicaMere, j.Naziv, r.aktuelnaCena";
    }

    @Override
    public String joinTable1() {
        return "JedinicaMere j";
    }

    @Override
    public String joinTableClause1() {
        return "r.IdJedinicaMere = j.IdJedinicaMere";
    }

    @Override
    public String getOrderByColumn() {
        return "r.IdRoba";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("IdRoba");
            String vrsta = rs.getString("Vrsta");
            String marka = rs.getString("Marka");
            String model = rs.getString("Model");
            String tip = rs.getString("Tip");
            
            int idjm = rs.getInt("IdJedinicaMere");
            String naziv = rs.getString("Naziv");
            JedinicaMere jm = new JedinicaMere(idjm, naziv);
            
            double aktuelnaCena = rs.getDouble("AktuelnaCena");

            Roba r = new Roba(id, vrsta, marka, model, tip, jm, aktuelnaCena);
            list.add(r);
        }

        return list;
    }

    @Override
    public String getIdColumnName() {
        return "r.IdRoba";
    }

    @Override
    public String getMaxIdWhere() {
        return "";
    }

    @Override
    public int getMaxIdFromResultSet(ResultSet rs) throws SQLException {
        int id = 0;
        
        while(rs.next()){
            id = rs.getInt("Max");
        }
        
        return id;
    }

    @Override
    public String getInsertColumnNames() {
        return "IdRoba, Vrsta, Marka, Model, Tip, AktuelnaCena, IdJedinicaMere";
    }

    @Override
    public String getColumnValues() {
        return "'" + vrsta + "', '" + marka + "', '" + model + "', '" + tip + "', " + aktuelnaCena + ", " + jm.getIdJedinicaMere();
    }

    @Override
    public String getUpdateClause() {
        String cena = cenaIzmena?(", r.AktuelnaCena = " + this.aktuelnaCena):"";
        return "r.vrsta = '" + vrsta + "', r.marka = '" + marka + "', r.model = '" + model + "', r.tip = '" + tip + "', r.IdJedinicaMere = " + jm.getIdJedinicaMere() + cena;
    }

    @Override
    public String getUpdateWhereClause() {
        return "r.IdRoba = " + IdRoba;
    }

    @Override
    public String joinTable2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTable3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTable4() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause4() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCountFromResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIdWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getWhereIdClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIdFromResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
