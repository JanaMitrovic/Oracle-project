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
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.DomainObject;

/**
 *
 * @author HP
 */
public class Resenje extends DomainObject {

    private int broj;
    private int godina;
    private Filijala filijala;
    private Obrazac obrazac;
    private Date datumDonosenja;
    private Date datumPrijema;
    private PoreskiObveznik poreskiObveznik;
    private double ukupanIznos;
    private int particija;
    private boolean izmenaIznos = false;

    public Resenje() {
    }

    public Resenje(int broj, int godina, Filijala filijala, Obrazac obrazac, Date datumDonosenja, Date datumPrijema, PoreskiObveznik poreskiObveznik, double ukupanIznos) {
        this.broj = broj;
        this.godina = godina;
        this.filijala = filijala;
        this.obrazac = obrazac;
        this.datumDonosenja = datumDonosenja;
        this.datumPrijema = datumPrijema;
        this.poreskiObveznik = poreskiObveznik;
        this.ukupanIznos = ukupanIznos;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
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

    public Date getDatumDonosenja() {
        return datumDonosenja;
    }

    public void setDatumDonosenja(Date datumDonosenja) {
        this.datumDonosenja = datumDonosenja;
    }

    public Date getDatumPrijema() {
        return datumPrijema;
    }

    public void setDatumPrijema(Date datumPrijema) {
        this.datumPrijema = datumPrijema;
    }

    public PoreskiObveznik getPoreskiObveznik() {
        return poreskiObveznik;
    }

    public void setPoreskiObveznik(PoreskiObveznik poreskiObveznik) {
        this.poreskiObveznik = poreskiObveznik;
    }

    public int getParticija() {
        return particija;
    }

    public void setParticija(int particija) {
        this.particija = particija;
    }
    
    public boolean isIzmenaIznos() {
        return izmenaIznos;
    }

    public void setIzmenaIznos(boolean izmenaIznos) {
        this.izmenaIznos = izmenaIznos;
    }

    @Override
    public String toString() {
        return "Resenje broj " + broj + " (" + godina + ")"; 
    }

    @Override
    public String getTableName() {
        switch (this.particija) {
            case 1:
                return "Resenje PARTITION(prvi_kvartal2022) r";
            case 2:
                return "Resenje PARTITION(drugi_kvartal2022) r";
            case 3:
                return "Resenje PARTITION(treci_kvartal2022) r";
            case 4:
                return "Resenje PARTITION(cetvrti_kvartal2022) r";
            default:
                return "Resenje r";
        }
    }

    @Override
    public String getAllColumnNames() {
        return "r.godina, r.broj, f.IdFilijala, m.IdMesto, m.Naziv AS NazivM, o.SifraObrazac, o.Naziv as NazivO, r.DatumDonosenja, r.DatumPrijema, po.PIB, po.naziv.get_ime() AS NazivPO, po.naziv.get_tip() AS TipPreduzeca, r.ukupanIznos";
    }

    @Override
    public String joinTable1() {
        return "Filijala f";
    }

    @Override
    public String joinTableClause1() {
        return "r.IdFilijala = f.IdFilijala";
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
        return "r.SifraObrazac = o.SifraObrazac";
    }

    @Override
    public String joinTable4() {
        return "PoreskiObveznik po";
    }

    @Override
    public String joinTableClause4() {
        return "r.PIB = po.PIB";
    }

    @Override
    public String getOrderByColumn() {
        return "r.godina, r.broj";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();

        while (rs.next()) {
            int broj = rs.getInt("Broj");
            int godina = rs.getInt("Godina");
            Date datumDonosenja = rs.getDate("DatumDonosenja");
            Date datumPrijema = rs.getDate("DatumPrijema");
            int ukupanIznos = rs.getInt("ukupanIznos");

            int idm = rs.getInt("IdMesto");
            String nazivm = rs.getString("NazivM");
            Mesto mesto = new Mesto(idm, nazivm);

            int idf = rs.getInt("IdFilijala");
            Filijala filijala = new Filijala(idf, mesto);

            String sifrao = rs.getString("sifraObrazac");
            String nazivo = rs.getString("NazivO");
            Obrazac obrazac = new Obrazac(sifrao, nazivo);

            int pib = rs.getInt("PIB");
            String ime = rs.getString("NazivPO");
            String tip = rs.getString("TipPreduzeca");
            PoreskiObveznik po = new PoreskiObveznik(pib, null, ime, tip);

            Resenje r = new Resenje(broj, godina, filijala, obrazac, datumDonosenja, datumPrijema, po, ukupanIznos);
            list.add(r);
        }

        return list;
    }

    @Override
    public String getIdColumnName() {
        return "r.Broj";
    }

    @Override
    public String getMaxIdWhere() {
        return " WHERE r.Godina = 2022";
//        return " WHERE r.Godina = " + Integer.parseInt(String.valueOf(Year.now()));
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
        return "Broj, Godina, IdFilijala, SifraObrazac, DatumDonosenja, DatumPrijema, PIB";
    }

    @Override
    public String getColumnValues() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String donosenje = formatter.format(datumDonosenja.getTime());
        String prijem = formatter.format(datumPrijema.getTime());
        return broj + ", " + godina + ", " + filijala.getIdFilijala() + ", '" + obrazac.getSifraObrazac() + "', '" + donosenje + "', '" + prijem + "', " + poreskiObveznik.getPIB();
    }

    @Override
    public String getUpdateClause() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        String donosenje = formatter.format(datumDonosenja.getTime());
        String prijem = formatter.format(datumPrijema.getTime());
        String iznos = izmenaIznos?(", r.UkupanIznos = " + ukupanIznos):"";
        return "r.IdFilijala = " + filijala.getIdFilijala() + ", r.SifraObrazac = '" + obrazac.getSifraObrazac() + "', r.datumDonosenja = '" + donosenje + "', r.datumPrijema = '" + prijem + "', r.PIB = " + poreskiObveznik.getPIB() + iznos;
    }

    @Override
    public String getUpdateWhereClause() {
        return "r.Broj = " + broj + " AND r.Godina = " + godina;
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
