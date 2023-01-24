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
public class StavkaResenja extends DomainObject{
    
    private int rb;
    private Resenje resenje;
    private Date datumOd;
    private Date datumDo;
    private VrstaPrihoda vrstaPrihoda;
    private double iznos;

    public StavkaResenja() {
    }

    public StavkaResenja(int rb, Resenje resenje, Date datumOd, Date datumDo, VrstaPrihoda vrstaPrihoda, double iznos) {
        this.rb = rb;
        this.resenje = resenje;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.vrstaPrihoda = vrstaPrihoda;
        this.iznos = iznos;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Resenje getResenje() {
        return resenje;
    }

    public void setResenje(Resenje resenje) {
        this.resenje = resenje;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public VrstaPrihoda getVrstaPrihoda() {
        return vrstaPrihoda;
    }

    public void setVrstaPrihoda(VrstaPrihoda vrstaPrihoda) {
        this.vrstaPrihoda = vrstaPrihoda;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    @Override
    public String getTableName() {
        return "StavkaResenja sr";
    }

    @Override
    public String getAllColumnNames() {
        return "sr.rb, r.broj, r.godina, sr.datumOd, sr.datumDo, vp.IdVrstaPrihoda, vp.Naziv, sr.Iznos";
    }

    @Override
    public String joinTable1() {
        return "Resenje r";
    }

    @Override
    public String joinTableClause1() {
        return "sr.broj = r.broj AND sr.godina = r.godina";
    }

    @Override
    public String joinTable2() {
        return "VrstaPrihoda vp";
    }

    @Override
    public String joinTableClause2() {
        return "sr.IdVrstaPrihoda = vp.IdVrstaPrihoda";
    }

    @Override
    public String getOrderByColumn() {
        return "sr.rb";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();

        while (rs.next()) {
            
            int rb = rs.getInt("rb");
            Date datumOd = rs.getDate("datumOd");
            Date datumDo = rs.getDate("datumDo");
            double iznos = rs.getDouble("Iznos");
            
            int broj = rs.getInt("Broj");
            int godina = rs.getInt("Godina");
            Resenje r = new Resenje();
            r.setBroj(broj);
            r.setGodina(godina);
            
            int idv = rs.getInt("IdVrstaPrihoda");
            String vrstaPrihoda = rs.getString("Naziv");
            VrstaPrihoda vp = new VrstaPrihoda(idv, vrstaPrihoda);
            
            StavkaResenja sr = new StavkaResenja(rb, r, datumOd, datumDo, vp, iznos);
            
            list.add(sr);
        }

        return list;
    }

    @Override
    public String getIdColumnName() {
        return "sr.rb";
    }

    @Override
    public String getMaxIdWhere() {
        return " WHERE sr.broj = " + resenje.getBroj() + " AND sr.godina = " + resenje.getGodina();
    }

    @Override
    public int getMaxIdFromResultSet(ResultSet rs) throws SQLException {
        int id = 0;

        while (rs.next()) {
            id = rs.getInt("Max");
        }

        return id;
    }

    @Override
    public String getInsertColumnNames() {
        return "rb, broj, godina, datumOd, datumDo, IdVrstaPrihoda, iznos";
    }

    @Override
    public String getColumnValues() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String dod = formatter.format(datumOd.getTime());
        String ddo = formatter.format(datumDo.getTime());
        return rb + ", " + resenje.getBroj() + ", " + resenje.getGodina() + ", '" + dod + "', '" + ddo + "', " + vrstaPrihoda.getIdVrstaPrihoda() + "," + iznos;
    }

    @Override
    public String getUpdateClause() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String dod = formatter.format(datumOd.getTime());
        String ddo = formatter.format(datumDo.getTime());
        return "sr.rb = " + rb + ", sr.datumOd = '" + dod + "', sr.datumDo = '" + ddo + "', sr.IdVrstaPrihoda = " + vrstaPrihoda.getIdVrstaPrihoda() + ", sr.iznos = " + iznos;
    }

    @Override
    public String getUpdateWhereClause() {
        return "sr.rb = " + rb + " AND sr.broj = " + resenje.getBroj() + " AND sr.godina = " + resenje.getGodina();
    }

    @Override
    public String getDeleteWhereClause() {
        return "sr.rb = " + rb + " AND sr.broj = " + resenje.getBroj() + " AND sr.godina = " + resenje.getGodina();
    }

    @Override
    public String getWhereIdClause() {
        return "sr.broj = " + resenje.getBroj() + " AND sr.godina = " + resenje.getGodina();
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
    public int getIdFromResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
