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
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.DomainObject;

/**
 *
 * @author HP
 */
public class Nalog extends DomainObject {

    private int broj;
    private int godina;
    private Filijala filijala;
    private Obrazac obrazac;
    private Date datumIzdavanja;
    private Date datumUrucenja;
    private Timestamp vremeUrucenja;
    private Mesto mesto;
    private PoreskiObveznik poreskiObveznik;
    private boolean MestoIzmena = false;

    public Nalog() {
    }

    public Nalog(int broj, int godina, Filijala filijala, Obrazac obrazac, Date datumIzdavanja, Date datumUrucenja, Timestamp vremeUrucenja, Mesto mesto, PoreskiObveznik poreskiObveznik) {
        this.broj = broj;
        this.godina = godina;
        this.filijala = filijala;
        this.obrazac = obrazac;
        this.datumIzdavanja = datumIzdavanja;
        this.datumUrucenja = datumUrucenja;
        this.vremeUrucenja = vremeUrucenja;
        this.mesto = mesto;
        this.poreskiObveznik = poreskiObveznik;
    }

    public PoreskiObveznik getPoreskiObveznik() {
        return poreskiObveznik;
    }

    public void setPoreskiObveznik(PoreskiObveznik poreskiObveznik) {
        this.poreskiObveznik = poreskiObveznik;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public Filijala getFilijala() {
        return filijala;
    }

    public void setFilijala(Filijala filijala) {
        this.filijala = filijala;
    }

    public Obrazac getObrazac() {
        return obrazac;
    }

    public void setObrazac(Obrazac obrazac) {
        this.obrazac = obrazac;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public Date getDatumUrucenja() {
        return datumUrucenja;
    }

    public void setDatumUrucenja(Date datumUrucenja) {
        this.datumUrucenja = datumUrucenja;
    }

    public Timestamp getVremeUrucenja() {
        return vremeUrucenja;
    }

    public void setVremeUrucenja(Timestamp vremeUrucenja) {
        this.vremeUrucenja = vremeUrucenja;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }
    
    public boolean getMestoIzmena(){
        return MestoIzmena;
    }

    public void setMestoIzmena(boolean MestoIzmena) {
        this.MestoIzmena = MestoIzmena;
    }

    @Override
    public String getTableName() {
        return "Nalog n";
    }

    @Override
    public String getAllColumnNames() {
        return "n.Broj, n.Godina, n.DatumIzdavanja, n.DatumUrucenja, n.VremeUrucenja, m.IdMesto, m.Naziv AS Mesto, f.IdFilijala, o.SifraObrazac, o.Naziv AS Obrazac, po.PIB, po.naziv.get_ime() AS NazivPO, po.naziv.get_tip() AS TipPreduzeca";
    }

    @Override
    public String joinTable1() {
        return "Filijala f";
    }

    @Override
    public String joinTableClause1() {
        return "n.IdFilijala = f.IdFilijala";
    }

    @Override
    public String joinTable2() {
        return "Mesto m";
    }

    @Override
    public String joinTableClause2() {
        return "f.IdMesto = m.IdMesto";
    }

    @Override
    public String joinTable3() {
        return "Obrazac o";
    }

    @Override
    public String joinTableClause3() {
        return "n.SifraObrazac = o.SifraObrazac";
    }

    @Override
    public String joinTable4() {
        return "PoreskiObveznik po";
    }

    @Override
    public String joinTableClause4() {
        return "n.PIB = po.PIB";
    }

    @Override
    public String getOrderByColumn() {
        return "n.Godina, n.Broj";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();

        while (rs.next()) {
            int broj = rs.getInt("Broj");
            int godina = rs.getInt("Godina");
            Date datumIzdavanja = rs.getDate("DatumIzdavanja");
            Date datumUrucenja = rs.getDate("DatumUrucenja");
            Timestamp vremeUrucenja = rs.getTimestamp("VremeUrucenja");

            int idm = rs.getInt("IdMesto");
            String nazivm = rs.getString("Mesto");
            Mesto mesto = new Mesto(idm, nazivm);

            int idf = rs.getInt("IdFilijala");
            Filijala filijala = new Filijala(idf, mesto);

            String sifrao = rs.getString("sifraObrazac");
            String nazivo = rs.getString("Obrazac");
            Obrazac obrazac = new Obrazac(sifrao, nazivo);

            int pib = rs.getInt("PIB");
            String ime = rs.getString("NazivPO");
            String tip = rs.getString("TipPreduzeca");
            PoreskiObveznik po = new PoreskiObveznik(pib, null, ime, tip);

            Nalog n = new Nalog(broj, godina, filijala, obrazac, datumIzdavanja, datumUrucenja, vremeUrucenja, mesto, po);
            list.add(n);
        }

        return list;
    }

    @Override
    public String getIdColumnName() {
        return "n.Broj";
    }

    @Override
    public String getMaxIdWhere() {
        return " WHERE n.Godina = " + Integer.parseInt(String.valueOf(Year.now()));
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
        return "Broj, Godina, IdFilijala, SifraObrazac, DatumIzdavanja, DatumUrucenja, VremeUrucenja, IdMesto, PIB";
    }

    @Override
    public String getColumnValues() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String izdavanje = formatter.format(datumIzdavanja.getTime());
        String urucenje = formatter.format(datumUrucenja.getTime());
        return broj + ", " + godina + ", " + filijala.getIdFilijala() + ", '" + obrazac.getSifraObrazac() + "', '" + izdavanje + "', '" + urucenje +"', TIMESTAMP '" + vremeUrucenja + "', " + mesto.getIdMesto() + ", " + poreskiObveznik.getPIB();
    }

    @Override
    public String getUpdateClause() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String izdavanje = formatter.format(datumIzdavanja.getTime());
        String urucenje = formatter.format(datumUrucenja.getTime());
        String mesto = MestoIzmena?(", n.IdMesto = " + this.mesto.getIdMesto()):"";
        return "n.IdFilijala = " + filijala.getIdFilijala() + ", n.SifraObrazac = '" + obrazac.getSifraObrazac() + "', n.datumIzdavanja = '" + izdavanje + "', n.datumUrucenja = '" + urucenje + "', n.vremeUrucenja = TIMESTAMP '" + vremeUrucenja + "', n.PIB = " + poreskiObveznik.getPIB() + mesto;
    }

    @Override
    public String getUpdateWhereClause() {
        return "n.Broj = " + broj + " AND n.Godina = " + godina;
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
