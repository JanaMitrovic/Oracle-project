/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.DomainObject;

/**
 *
 * @author HP
 */
public class StavkaNaloga extends DomainObject{
    
    private int rb;
    private Nalog nalog;
    private Date datumOd;
    private Date datumDo;
    private VrstaPrihoda vrstaPrihoda;
    private Date datumIzdavanja;
    private boolean izmenaDatIzd = false;

    public StavkaNaloga() {
    }

    public StavkaNaloga(int rb, Nalog nalog, Date datumOd, Date datumDo, VrstaPrihoda vrstaPrihoda, Date datumIzdavanja) {
        this.rb = rb;
        this.nalog = nalog;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.vrstaPrihoda = vrstaPrihoda;
        this.datumIzdavanja = datumIzdavanja;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Nalog getNalog() {
        return nalog;
    }

    public void setNalog(Nalog nalog) {
        this.nalog = nalog;
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

    public boolean isIzmenaDatIzd() {
        return izmenaDatIzd;
    }

    public void setIzmenaDatIzd(boolean izmenaDatIzd) {
        this.izmenaDatIzd = izmenaDatIzd;
    }

    @Override
    public String getTableName() {
        return "stavkaNaloga sn";
    }

    @Override
    public String getAllColumnNames() {
        return "sn.rb, n.broj, n.godina, sn.datumOd, sn.datumDo, vp.IdVrstaPrihoda, vp.Naziv, sn.datumIzdavanja";
    }

    @Override
    public String joinTable1() {
        return "Nalog n";
    }

    @Override
    public String joinTableClause1() {
        return "sn.broj = n.broj AND sn.godina = n.godina";
    }

    @Override
    public String joinTable2() {
        return "VrstaPrihoda vp";
    }

    @Override
    public String joinTableClause2() {
        return "sn.IdVrstaPrihoda = vp.IdVrstaPrihoda";
    }

    @Override
    public String getOrderByColumn() {
        return "sn.rb";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();

        while (rs.next()) {
            
            int rb = rs.getInt("rb");
            Date datumOd = rs.getDate("datumOd");
            Date datumDo = rs.getDate("datumDo");
            Date datumIzdavanja = rs.getDate("DatumIzdavanja");
            
            int broj = rs.getInt("Broj");
            int godina = rs.getInt("Godina");
            Nalog n = new Nalog();
            n.setBroj(broj);
            n.setGodina(godina);
            
            int idv = rs.getInt("IdVrstaPrihoda");
            String vrstaPrihoda = rs.getString("Naziv");
            VrstaPrihoda vp = new VrstaPrihoda(idv, vrstaPrihoda);
            
            StavkaNaloga sn = new StavkaNaloga(rb, n, datumOd, datumDo, vp, datumIzdavanja);
            
            list.add(sn);
        }

        return list;
    }

    @Override
    public String getIdColumnName() {
        return "sn.rb";
    }

    @Override
    public String getMaxIdWhere() {
        return " WHERE sn.broj = " + nalog.getBroj() + " AND sn.godina = " + nalog.getGodina();
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
        return "rb, broj, godina, datumOd, datumDo, IdVrstaPrihoda";
    }

    @Override
    public String getColumnValues() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String dod = formatter.format(datumOd.getTime());
        String ddo = formatter.format(datumDo.getTime());
        return rb + ", " + nalog.getBroj() + ", " + nalog.getGodina() + ", '" + dod + "', '" + ddo + "', " + vrstaPrihoda.getIdVrstaPrihoda();
    }

    @Override
    public String getUpdateClause() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String dod = formatter.format(datumOd.getTime());
        String ddo = formatter.format(datumDo.getTime());
        String dizd = formatter.format(datumIzdavanja.getTime());
        String izdavanje = izmenaDatIzd?(", sn.datumIzdavanja = '" + dizd + "'"):"";
        return "sn.rb = " + rb + ", sn.datumOd = '" + dod + "', sn.datumDo = '" + ddo + "', sn.IdVrstaPrihoda = " + vrstaPrihoda.getIdVrstaPrihoda() + izdavanje;
    }

    @Override
    public String getUpdateWhereClause() {
        return "sn.rb = " + rb + " AND sn.broj = " + nalog.getBroj() + " AND sn.godina = " + nalog.getGodina();
    }

    @Override
    public String getWhereIdClause() {
        return "sn.broj = " + nalog.getBroj() + " AND sn.godina = " + nalog.getGodina();
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
    public String getDeleteWhereClause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIdFromResultSet(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
