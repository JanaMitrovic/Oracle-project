/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
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
public class Zapisnik extends DomainObject {

    private int broj;
    private int godina;
    private Filijala filijala;
    private Obrazac obrazac;
    private Date datumDonosenja;
    private Date datumPrijema;
    private PoreskiObveznik poreskiObveznik;
    private int particija = 0;

    public Zapisnik() {
    }

    public Zapisnik(int broj, int godina, Filijala filijala, Obrazac obrazac, Date datumDonosenja, Date datumPrijema, PoreskiObveznik poreskiObveznik) {
        this.broj = broj;
        this.godina = godina;
        this.filijala = filijala;
        this.obrazac = obrazac;
        this.datumDonosenja = datumDonosenja;
        this.datumPrijema = datumPrijema;
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

    @Override
    public String toString() {
        return "Zapisnik broj " + broj + " (" + godina + ")";
    }

    @Override
    public String getTableName() {
        switch (this.particija) {
            case 1:
                return "Zapisnik_osnovno z";
            case 2:
                return "Zapisnik_detalji z";
            default:
                return "Zapisnik z";
        }
    }

    @Override
    public String getAllColumnNames() {
        switch (this.particija) {
            case 1:
                return "z.godina, z.broj, f.IdFilijala, m.IdMesto, m.Naziv AS NazivM, po.PIB, po.naziv.get_ime() AS NazivPO, po.naziv.get_tip() AS TipPreduzeca";
            case 2:
                return "z.godina, z.broj, o.SifraObrazac, o.Naziv as NazivO, z.DatumDonosenja, z.DatumPrijema";
            default:
                return "z.godina, z.broj, f.IdFilijala, m.IdMesto, m.Naziv AS NazivM, o.SifraObrazac, o.Naziv as NazivO, z.DatumDonosenja, z.DatumPrijema, po.PIB, po.naziv.get_ime() AS NazivPO, po.naziv.get_tip() AS TipPreduzeca";
        }
    }

    @Override
    public String joinTable1() {
        switch (this.particija) {
            case 1:
                return "Filijala f";
            case 2:
                return "Obrazac o";
            default:
                return "Filijala f";
        }
    }

    @Override
    public String joinTableClause1() {
        switch (this.particija) {
            case 1:
                return "z.IdFilijala = f.IdFilijala";
            case 2:
                return "z.SifraObrazac = o.SifraObrazac";
            default:
                return "z.IdFilijala = f.IdFilijala";
        }
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
        switch (this.particija) {
            case 1:
                return "PoreskiObveznik po";
            default:
                return "Obrazac o";
        }
    }

    @Override
    public String joinTableClause3() {
        switch (this.particija) {
            case 1:
                return "z.PIB = po.PIB";
            default:
                return "z.SifraObrazac = o.SifraObrazac";
        }
    }

    @Override
    public String joinTable4() {
        return "PoreskiObveznik po";
    }

    @Override
    public String joinTableClause4() {
        return "z.PIB = po.PIB";
    }

    @Override
    public String getOrderByColumn() {
        return "z.godina, z.broj";
    }

    @Override
    public List<DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<DomainObject> list = new ArrayList<>();

        while (rs.next()) {

            Zapisnik z = new Zapisnik();

            int broj = rs.getInt("Broj");
            int godina = rs.getInt("Godina");
            z.setBroj(broj);
            z.setGodina(godina);

            if (particija != 2) {
                int idm = rs.getInt("IdMesto");
                String nazivm = rs.getString("NazivM");
                Mesto mesto = new Mesto(idm, nazivm);

                int idf = rs.getInt("IdFilijala");
                Filijala filijala = new Filijala(idf, mesto);
                z.setFilijala(filijala);

                int pib = rs.getInt("PIB");
                String ime = rs.getString("NazivPO");
                String tip = rs.getString("TipPreduzeca");
                PoreskiObveznik po = new PoreskiObveznik(pib, null, ime, tip);
                z.setPoreskiObveznik(po);
            }

            if (particija != 1) {
                Date datumDonosenja = rs.getDate("DatumDonosenja");
                Date datumPrijema = rs.getDate("DatumPrijema");
                z.setDatumDonosenja(datumDonosenja);
                z.setDatumPrijema(datumPrijema);

                String sifrao = rs.getString("sifraObrazac");
                String nazivo = rs.getString("NazivO");
                Obrazac obrazac = new Obrazac(sifrao, nazivo);
                z.setObrazac(obrazac);
            }

            list.add(z);
        }

        return list;
    }

    @Override
    public String getIdColumnName() {
        return "z.Broj";
    }

    @Override
    public String getMaxIdWhere() {
        return " WHERE z.Godina = " + Integer.parseInt(String.valueOf(Year.now()));
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
        return "z.IdFilijala = " + filijala.getIdFilijala() + ", z.SifraObrazac = '" + obrazac.getSifraObrazac() + "', z.datumDonosenja = '" + donosenje + "', z.datumPrijema = '" + prijem + "', z.PIB = " + poreskiObveznik.getPIB();
    }

    @Override
    public String getUpdateWhereClause() {
        return "z.Broj = " + broj + " AND z.Godina = " + godina;
    }

    @Override
    public String getDeleteWhereClause() {
        return "z.Broj = " + broj + " AND z.Godina = " + godina;
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
