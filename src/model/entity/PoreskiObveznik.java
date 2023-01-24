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
public class PoreskiObveznik extends DomainObject{
    
    private int PIB;
    private Broj broj;
    private String ime;
    private String tip;

    public PoreskiObveznik() {
    }

    public PoreskiObveznik(int PIB, Broj broj, String ime, String tip) {
        this.PIB = PIB;
        this.broj = broj;
        this.ime = ime;
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getPIB() {
        return PIB;
    }

    public void setPIB(int PIB) {
        this.PIB = PIB;
    }

    public Broj getBroj() {
        return broj;
    }

    public void setBroj(Broj broj) {
        this.broj = broj;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public String toString() {
        return ime + "(" + PIB + ")";
    }

    @Override
    public String getTableName() {
        return "poreskiObveznik po";
    }

    @Override
    public String getAllColumnNames() {
        return "po.PIB, m.IdMesto, m.Naziv AS Mesto, u.IdUlica, u.Naziv AS Ulica, b.IdBroj, b.Broj, po.naziv.get_ime() AS NazivPO, po.naziv.get_tip() AS TipPreduzeca";
    }

    @Override
    public String joinTable1() {
        return "Mesto m";
    }

    @Override
    public String joinTableClause1() {
        return "po.IdMesto = m.IdMesto";
    }

    @Override
    public String joinTable2() {
        return "Ulica u";
    }

    @Override
    public String joinTableClause2() {
        return "po.IdUlica = u.IdUlica";
    }
    
    @Override
    public String joinTable3() {
        return "Broj b";
    }

    @Override
    public String joinTableClause3() {
        return "po.IdBroj = b.IdBroj";
    }
    
    @Override
    public String getOrderByColumn() {
        return "po.PIB";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();
        
        while(rs.next()){
            int pib = rs.getInt("PIB");
            String ime = rs.getString("NazivPO");
            String tip = rs.getString("TipPreduzeca");
            
            int mestoId = rs.getInt("IdMesto");
            String nazivMesta = rs.getString("Mesto");
            Mesto mesto = new Mesto(mestoId, nazivMesta);
            
            int ulicaId = rs.getInt("IdUlica");
            String nazivUlice = rs.getString("Ulica");
            Ulica ulica = new Ulica(ulicaId,nazivUlice, mesto);
            
            int brojId = rs.getInt("IdBroj");
            String br = rs.getString("Broj");
            Broj broj = new Broj(brojId, br, ulica);
            
            PoreskiObveznik po = new PoreskiObveznik(pib, broj, ime, tip);
            list.add(po);
        }
        
        return list;
    }

    @Override
    public String getIdWhereClause() {
        return "PIB = " + PIB;
    }
    
    @Override
    public int getCountFromResultSet(ResultSet rs) throws SQLException {
        int id = 0;
        
        while(rs.next()){
            id = rs.getInt("COUNT(1)");
        }
        
        return id;
    }

    @Override
    public String getInsertColumnNames() {
        return "PIB, IdMesto, IdUlica, IdBroj, Naziv";
    }

    @Override
    public String getColumnValues() {
        return PIB + ", " + broj.getUlica().getMesto().getIdMesto() + ", " + broj.getUlica().getIdUlica() + ", " + broj.getIdBroj() + ", NazivPO('" + ime + "', '" + tip + "')";
    }

    @Override
    public String getUpdateClause() {
        return "po.naziv.ime = '" + ime + "', po.naziv.tip = '" + tip + "', IdUlica = " + broj.getUlica().getIdUlica() + ", IdBroj = " + broj.getIdBroj() + ", IdMesto = " + broj.getUlica().getMesto().getIdMesto();
    }

    @Override
    public String getUpdateWhereClause() {
        return "PIB = " + PIB;
    }

    @Override
    public String getDeleteWhereClause() {
        return "PIB = " + PIB;
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
    public String getWhereIdClause() {
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
