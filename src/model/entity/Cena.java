/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.DomainObject;

/**
 *
 * @author HP
 */
public class Cena extends DomainObject{
    
    private int IdCena;
    private Roba roba;
    private Date datumOd;
    private double iznos;

    public Cena() {
    }

    public Cena(int IdCena, Roba roba, Date datumOd, double iznos) {
        this.IdCena = IdCena;
        this.roba = roba;
        this.datumOd = datumOd;
        this.iznos = iznos;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public int getIdCena() {
        return IdCena;
    }

    public void setIdCena(int IdCena) {
        this.IdCena = IdCena;
    }

    public Roba getRoba() {
        return roba;
    }

    public void setRoba(Roba roba) {
        this.roba = roba;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    @Override
    public String getTableName() {
        return "Cena c";
    }

    @Override
    public String getAllColumnNames() {
        return "r.IdRoba, c.IdCena, c.datumOd, c.Iznos";
    }

    @Override
    public String joinTable1() {
        return "Roba r";
    }

    @Override
    public String joinTableClause1() {
        return "r.IdRoba = c.IdRoba WHERE c.IdRoba = " + roba.getIdRoba();
    }

    @Override
    public String getOrderByColumn() {
        return "r.IdRoba, c.IdCena";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();

        while (rs.next()) {
            int idr = rs.getInt("IdRoba");
            Roba r = new Roba();
            r.setIdRoba(idr);
            
            int idc = rs.getInt("IdCena");
            Date datumOd =  rs.getDate("DatumOd");
            double iznos = rs.getDouble("Iznos");
            
            Cena c = new Cena(idc, r, datumOd, iznos);
            list.add(c);
        }

        return list;
    }

    @Override
    public String getIdColumnName() {
        return "c.IdCena";
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
        return "c.IdCena, c.IdRoba, c.DatumOd, c.Iznos";
    }

    @Override
    public String getColumnValues() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String datumOd = formatter.format(this.datumOd.getTime());
        return roba.getIdRoba() + ", '" + datumOd + "', " + iznos;
    }

    @Override
    public String getUpdateWhereClause() {
        return "c.IdCena = " + IdCena;
    }

    @Override
    public String getDeleteWhereClause() {
        return "c.IdCena = " + IdCena;
    }

    @Override
    public String getUpdateClause() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String datumOd = formatter.format(this.datumOd.getTime());
        return "c.IdRoba = " + roba.getIdRoba() + ", c.datumOd  = '" + datumOd + "', c.iznos = " + iznos;
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
    public int getIdFromResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
