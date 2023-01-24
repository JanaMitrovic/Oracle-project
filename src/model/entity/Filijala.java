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
import model.DomainObject;

/**
 *
 * @author HP
 */
public class Filijala extends DomainObject{
    
    private int IdFilijala;
    private Mesto mesto;

    public Filijala() {
    }

    public Filijala(int IdFilijala, Mesto mesto) {
        this.IdFilijala = IdFilijala;
        this.mesto = mesto;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public int getIdFilijala() {
        return IdFilijala;
    }

    public void setIdFilijala(int IdFilijala) {
        this.IdFilijala = IdFilijala;
    }

    @Override
    public String toString() {
        return mesto.getNaziv();
    }

    @Override
    public String getTableName() {
        return "Filijala f";
    }

    @Override
    public String getAllColumnNames() {
        return "f.IdFilijala, m.IdMesto, m.Naziv";
    }

    @Override
    public String joinTable1() {
        return "Mesto m";
    }

    @Override
    public String joinTableClause1() {
        return "f.IdMesto = m.IdMesto";
    }

    @Override
    public String getOrderByColumn() {
        return "f.IdFilijala";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
         List<DomainObject> list = new ArrayList<>();
        
        while(rs.next()){
            int id = rs.getInt("IdFilijala");
            
            int idM = rs.getInt("IdMesto");
            String naziv = rs.getString("Naziv");
            Mesto mesto = new Mesto(idM, naziv);
            
            Filijala f = new Filijala(id, mesto);
            list.add(f);
        }
        
        return list;
    }

    @Override
    public String getUpdateClause() {
        return "f.IdMesto = " + mesto.getIdMesto();
    }

    @Override
    public String getUpdateWhereClause() {
        return "f.IdFilijala = " + IdFilijala;
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
    public String getIdColumnName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMaxIdFromResultSet(ResultSet rs) throws SQLException {
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
    public String getInsertColumnNames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnValues() {
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
    public String joinTable4() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String joinTableClause4() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMaxIdWhere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIdFromResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
