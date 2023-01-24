/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DatabaseBroker;
import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import model.DomainObject;
import model.entity.Cena;
import model.entity.Filijala;
import model.entity.JedinicaMere;
import model.entity.Mesto;
import model.entity.Nalog;
import model.entity.Obrazac;
import model.entity.PlatniPromet;
import model.entity.PoreskiObveznik;
import model.entity.Resenje;
import model.entity.Roba;
import model.entity.StavkaNaloga;
import model.entity.StavkaResenja;
import model.entity.VrstaPrihoda;
import model.entity.Zapisnik;

/**
 *
 * @author HP
 */
public class Controller {

    public static Controller instance;
    private DatabaseBroker db;

    private Controller() throws Exception {
        db = new DatabaseBroker();
        db.connect();
    }

    public static Controller getInstance() throws Exception {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public List<PlatniPromet> getPlatniPrometList() throws Exception {
        PlatniPromet pp = new PlatniPromet();
        db.connect();
        List<DomainObject> domainObjects = db.getAll(pp);
        db.disconnect();
        List<PlatniPromet> objects = domainObjects.stream().map(PlatniPromet.class::cast).collect(toList());
        return objects;
    }

    public List<PoreskiObveznik> getPoreskiObveznikList() throws Exception {
        PoreskiObveznik po = new PoreskiObveznik();
        db.connect();
        List<DomainObject> domainObjects = db.getJoined3(po);
        db.disconnect();
        List<PoreskiObveznik> objects = domainObjects.stream().map(PoreskiObveznik.class::cast).collect(toList());
        return objects;
    }
    
    public List<Mesto> getMestaList() throws Exception {
        Mesto m = new Mesto();
        db.connect();
        List<DomainObject> domainObjects = db.getAll(m);
        db.disconnect();
        List<Mesto> objects = domainObjects.stream().map(Mesto.class::cast).collect(toList());
        return objects;
    }

    public List<Filijala> getFilijalaList() throws Exception {
        Filijala f = new Filijala();
        db.connect();
        List<DomainObject> domainObjects = db.getJoined1(f);
        db.disconnect();
        List<Filijala> objects = domainObjects.stream().map(Filijala.class::cast).collect(toList());
        return objects;
    }

    public List<Obrazac> getObrazacList() throws Exception {
        Obrazac o = new Obrazac();
        db.connect();
        List<DomainObject> domainObjects = db.getAll(o);
        db.disconnect();
        List<Obrazac> objects = domainObjects.stream().map(Obrazac.class::cast).collect(toList());
        return objects;
    }

    public List<Nalog> getNalogList() throws Exception {
        Nalog n = new Nalog();
        db.connect();
        List<DomainObject> domainObjects = db.getJoined4(n);
        db.disconnect();
        List<Nalog> objects = domainObjects.stream().map(Nalog.class::cast).collect(toList());
        return objects;
    }

    public List<StavkaNaloga> getStavkeNalogaList(Nalog nalog) throws Exception {
        StavkaNaloga sn = new StavkaNaloga();
        sn.setNalog(nalog);
        db.connect();
        List<DomainObject> domainObjects = db.getJoined2(sn);
        db.disconnect();
        List<StavkaNaloga> objects = domainObjects.stream().map(StavkaNaloga.class::cast).collect(toList());
        return objects;
    }

    public List<VrstaPrihoda> getVrstaPrihodaList() throws Exception {
        VrstaPrihoda vp = new VrstaPrihoda();
        db.connect();
        List<DomainObject> domainObjects = db.getAll(vp);
        db.disconnect();
        List<VrstaPrihoda> objects = domainObjects.stream().map(VrstaPrihoda.class::cast).collect(toList());
        return objects;
    }

    public List<Resenje> getResenjeList(Resenje r) throws Exception {
        db.connect();
        List<DomainObject> domainObjects = db.getJoined4(r);
        db.disconnect();
        List<Resenje> objects = domainObjects.stream().map(Resenje.class::cast).collect(toList());
        return objects;
    }

    public List<StavkaResenja> getStavkeResenjaList(Resenje r) throws Exception {
        StavkaResenja sr = new StavkaResenja();
        sr.setResenje(r);
        db.connect();
        List<DomainObject> domainObjects = db.getJoined2(sr);
        db.disconnect();
        List<StavkaResenja> objects = domainObjects.stream().map(StavkaResenja.class::cast).collect(toList());
        return objects;
    }

    public List<Zapisnik> getZapisnikList(Zapisnik z) throws Exception {
        db.connect();
        List<DomainObject> domainObjects;
        switch(z.getParticija()){
            case 1:
                domainObjects = db.getJoined3(z);
                break;
            case 2:
                domainObjects = db.getJoined1(z);
                break;
            default:
                domainObjects = db.getJoined4(z);
        }
        db.disconnect();
        List<Zapisnik> objects = domainObjects.stream().map(Zapisnik.class::cast).collect(toList());
        return objects;
    }

    public List<Roba> getRobaList() throws Exception {
        Roba r = new Roba();
        db.connect();
        List<DomainObject> domainObjects = db.getJoined1(r);
        db.disconnect();
        List<Roba> objects = domainObjects.stream().map(Roba.class::cast).collect(toList());
        return objects;
    }

    public List<JedinicaMere> getJmList() throws Exception {
        JedinicaMere jm = new JedinicaMere();
        db.connect();
        List<DomainObject> domainObjects = db.getAll(jm);
        db.disconnect();
        List<JedinicaMere> objects = domainObjects.stream().map(JedinicaMere.class::cast).collect(toList());
        return objects;
    }

    public List<Cena> getCenaList(Roba r) throws Exception {
        Cena c = new Cena();
        c.setRoba(r);
        db.connect();
        List<DomainObject> domainObjects = db.getJoined1(c);
        db.disconnect();
        List<Cena> objects = domainObjects.stream().map(Cena.class::cast).collect(toList());
        return objects;
    }

    public int getNextBroj(DomainObject object) throws Exception {
        db.connect();
        int broj = db.maxId(object);
        db.disconnect();
        return broj;
    }

    public int exists(DomainObject object) throws Exception {
        db.connect();
        int exists = db.exists(object);
        db.disconnect();
        return exists;
    }

    public void insert(DomainObject object) throws Exception {
        db.connect();
        db.insert(object);
        db.disconnect();
    }

    public void insert2(DomainObject object) throws Exception {
        db.connect();
        db.insert2(object);
        db.disconnect();
    }

    public int getId(DomainObject object) throws Exception {
        db.connect();
        int id = db.getId(object);
        db.disconnect();
        return id;
    }

    public void update(DomainObject object) throws Exception {
        db.connect();
        db.update(object);
        db.disconnect();
    }

    public void delete(DomainObject object) throws Exception {
        db.connect();
        db.delete(object);
        db.disconnect();
    }

    

}
