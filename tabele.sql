CREATE TABLE Mesto(
IdMesto NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(20));

CREATE TABLE Ulica(
IdUlica NUMBER(10),
Naziv VARCHAR2(50) NOT NULL,
IdMesto NUMBER(10)NOT NULL,
CONSTRAINT mesto_fk FOREIGN KEY (IdMesto) REFERENCES Mesto(IdMesto),
CONSTRAINT ulica_pk PRIMARY KEY (IdUlica, IdMesto)); 

CREATE TABLE Broj(
IdBroj NUMBER(10),
Broj VARCHAR2(20),
IdUlica NUMBER(10)NOT NULL,
IdMesto NUMBER(10)NOT NULL,
CONSTRAINT ulica_mesto_fk FOREIGN KEY (IdMesto, IdUlica) REFERENCES Ulica(IdMesto, IdUlica),
CONSTRAINT broj_pk PRIMARY KEY (IdBroj, IdUlica, IdMesto));

CREATE TABLE Filijala(
IdFilijala NUMBER(10) PRIMARY KEY,
IdMesto NUMBER(10),
CONSTRAINT mesto2_fk FOREIGN KEY (IdMesto) REFERENCES Mesto(IdMesto));

CREATE TABLE poreskiObveznik(
PIB  NUMBER(9) PRIMARY KEY,
Naziv NazivPO,
IdMesto  NUMBER(10) NOT NULL,
IdUlica NUMBER(10) NOT NULL,
IdBroj NUMBER(10) NOT NULL,
CONSTRAINT adresa_fk FOREIGN KEY (IdMesto, IdUlica, IdBroj) REFERENCES
Broj(IdMesto, IdUlica, IdBroj));

CREATE TABLE Uloga(
IdUloga NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(20)
);

CREATE TABLE Inspektor(
BrojLegitimacije NUMBER(6) PRIMARY KEY,
Ime VARCHAR2(20),
Prezime VARCHAR2(30),
IdUloga NUMBER(10) NOT NULL,
CONSTRAINT uloga FOREIGN KEY (IdUloga) REFERENCES Uloga(IdUloga)
);

CREATE TABLE Obrazac(
SifraObrazac VARCHAR2(3) PRIMARY KEY,
Naziv VARCHAR2(30));

CREATE TABLE Nalog(
Broj NUMBER(10) NOT NULL,
Godina NUMBER(4) NOT NULL,
IdFilijala NUMBER(10) NOT NULL,
SifraObrazac VARCHAR2(3) NOT NULL,
DatumIzdavanja DATE,
DatumUrucenja DATE,
VremeUrucenja TIMESTAMP,
IdMesto NUMBER(10),
PIB NUMBER(9) NOT NULL,
CONSTRAINT filijala_fk FOREIGN KEY (IdFilijala) REFERENCES Filijala(IdFilijala),
CONSTRAINT obrazac_fk FOREIGN KEY (SifraObrazac) REFERENCES Obrazac(SifraObrazac),
CONSTRAINT po_fk FOREIGN KEY (PIB) REFERENCES poreskiObveznik(PIB),
CONSTRAINT mesto3_fk FOREIGN KEY (IdMesto) REFERENCES Mesto(IdMesto),
CONSTRAINT nalog_pk PRIMARY KEY (Broj, Godina));

CREATE TABLE vrstaPrihoda(
IdVrstaPrihoda NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(20));

CREATE TABLE stavkaNaloga(
Rb NUMBER(10) NOT NULL,
Broj NUMBER(10) NOT NULL,
Godina NUMBER(10)NOT NULL,
DatumOd DATE,
DatumDo DATE,
IdVrstaPrihoda NUMERIC(10) NOT NULL,
DatumIzdavanja DATE,
CONSTRAINT nalog_fk FOREIGN KEY (Broj, Godina) REFERENCES Nalog(Broj,Godina),
CONSTRAINT vp_fk FOREIGN KEY (IdVrstaPrihoda) REFERENCES VrstaPrihoda(IdVrstaPrihoda),
CONSTRAINT stavkaNaloga_pk PRIMARY KEY (Rb,Broj, Godina));

CREATE TABLE Plan(
IdPlan NUMBER(10) PRIMARY KEY,
Broj NUMBER(10) NOT NULL,
Godina NUMBER(4) NOT NULL,
IdFilijala NUMBER(10) NOT NULL,
SifraObrazac VARCHAR2(3) NOT NULL,
DatumIzrade DATE,
RokZavrsetka DATE,
DatumOdobrenja DATE,
PIB NUMBER(9) NOT NULL,
CONSTRAINT nalog_plan_fk FOREIGN KEY (Broj, Godina) REFERENCES Nalog(Broj, Godina),
CONSTRAINT filijala_plan_fk FOREIGN KEY (IdFilijala) REFERENCES Filijala(IdFilijala),
CONSTRAINT obrazac_plan_fk FOREIGN KEY (SifraObrazac) REFERENCES Obrazac(SifraObrazac),
CONSTRAINT po_plan_fk FOREIGN KEY (PIB) REFERENCES poreskiObveznik(PIB));

CREATE TABLE StavkaPlana(
Rb NUMBER(10) NOT NULL,
IdPlan NUMBER(10) NOT NULL,
Sadrzaj VARCHAR2(200),
CONSTRAINT plan_fk FOREIGN KEY (IdPlan) REFERENCES Plan(IdPlan),
CONSTRAINT stavkaPlana_pk PRIMARY KEY (Rb, IdPlan));

CREATE TABLE Zapisnik(
Broj NUMBER(10) NOT NULL,
Godina NUMBER(4) NOT NULL,
IdFilijala NUMBER(10) NOT NULL,
SifraObrazac VARCHAR2(3) NOT NULL,
DatumDonosenja DATE,
DatumPrijema DATE,
PIB NUMBER(9) NOT NULL,
CONSTRAINT filijala_zapisnik_fk FOREIGN KEY (IdFilijala) REFERENCES Filijala(IdFilijala),
CONSTRAINT obrazac_zapisnik_fk FOREIGN KEY (SifraObrazac) REFERENCES Obrazac(SifraObrazac),
CONSTRAINT po_zapisnik_fk FOREIGN KEY (PIB) REFERENCES poreskiObveznik(PIB),
CONSTRAINT zapisnik_pk PRIMARY KEY (Broj, Godina));

CREATE TABLE  Svojstvo(
IdSvojstvo NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(30)
);

CREATE TABLE Zaposleni(
PIB  NUMBER(9) NOT NULL,
JMBG NUMBER(13) NOT NULL,
Ime VARCHAR2(20),
Prezime VARCHAR2(30),
IdSvojstvo NUMBER(10) NOT NULL,
CONSTRAINT svojstvo_fk FOREIGN KEY (IdSvojstvo) REFERENCES Svojstvo(IdSvojstvo),
CONSTRAINT zaposleni_pk PRIMARY KEY (PIB,JMBG));

CREATE TABLE platniPromet(
IdPlatniPromet  NUMBER(9) PRIMARY KEY,
Banka VARCHAR2(30),
Racun Racun);

CREATE SEQUENCE pp_seq START WITH 1;

CREATE OR REPLACE TRIGGER PP_ID 
BEFORE INSERT ON PlatniPromet 
FOR EACH ROW
BEGIN
  SELECT pp_seq.NEXTVAL
  INTO   :NEW.IdPlatniPromet
  FROM   dual;
END;

SELECT MAX(IdPlatniPromet) FROM PlatniPromet;

INSERT INTO platniPromet (Banka, Racun) VALUES ('Unicredit Bank', Racun(111111111111111111));

CREATE TABLE  Tip(
IdTip NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(30)
);

CREATE TABLE  Delatnost(
IdDelatnost NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(30),
IdTip NUMBER(10) NOT NULL,
CONSTRAINT tip_fk FOREIGN KEY (IdTip) REFERENCES Tip(IdTip)
);

CREATE TABLE Resenje(
Broj NUMBER(10) NOT NULL,
Godina NUMBER(4) NOT NULL,
IdFilijala NUMBER(10) NOT NULL,
SifraObrazac VARCHAR2(3) NOT NULL,
DatumDonosenja DATE,
DatumPrijema DATE,
PIB NUMBER(9) NOT NULL,
UkupanIznos NUMERIC(30),
CONSTRAINT filijala_r_fk FOREIGN KEY (IdFilijala) REFERENCES Filijala(IdFilijala),
CONSTRAINT obrazac_r_fk FOREIGN KEY (SifraObrazac) REFERENCES Obrazac(SifraObrazac),
CONSTRAINT po_r_fk FOREIGN KEY (PIB) REFERENCES poreskiObveznik(PIB),
CONSTRAINT resenje_pk PRIMARY KEY (Broj, Godina));

CREATE TABLE stavkaResenja(
Rb NUMERIC(10) NOT NULL,
Broj NUMERIC(10) NOT NULL,
Godina NUMERIC(10)NOT NULL,
DatumOd DATE,
DatumDo DATE,
Iznos NUMERIC(30),
IdVrstaPrihoda NUMERIC(10) NOT NULL,
CONSTRAINT resenje_fk FOREIGN KEY (Broj, Godina) REFERENCES Resenje(Broj,Godina),
CONSTRAINT vp_r_fk FOREIGN KEY (IdVrstaPrihoda) REFERENCES VrstaPrihoda(IdVrstaPrihoda),
CONSTRAINT stavkaResenja_pk PRIMARY KEY (Rb, Broj, Godina));

CREATE TABLE ResenjeOR(
Broj NUMBER(10) NOT NULL,
Godina NUMBER(4) NOT NULL,
IdFilijala NUMBER(10) NOT NULL,
SifraObrazac VARCHAR2(3) NOT NULL,
DatumDonosenja DATE,
DatumPrijema DATE,
PIB NUMBER(9) NOT NULL,
UkupanIznos NUMERIC(30),
CONSTRAINT filijala_or_fk FOREIGN KEY (IdFilijala) REFERENCES Filijala(IdFilijala),
CONSTRAINT obrazac_or_fk FOREIGN KEY (SifraObrazac) REFERENCES Obrazac(SifraObrazac),
CONSTRAINT po_or_fk FOREIGN KEY (PIB) REFERENCES poreskiObveznik(PIB),
CONSTRAINT resenjeOR_pk PRIMARY KEY (Broj, Godina));

CREATE TABLE  JedinicaMere(
IdJedinicaMere NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(30)
);

CREATE TABLE  Roba(
IdRoba NUMBER(10) PRIMARY KEY,
Vrsta VARCHAR2(20),
Marka VARCHAR2(20),
Model VARCHAR2(20),
Tip VARCHAR2(20),
IdJedinicaMere NUMBER(10),
AktuelnaCena NUMBER(10),
CONSTRAINT jm_fk FOREIGN KEY (IdJedinicaMere) REFERENCES JedinicaMere(IdJedinicaMere));

CREATE TABLE  Cena(
IdCena NUMBER(10),
IdRoba NUMBER(10),
DatumOd DATE,
Iznos NUMBER(10),
CONSTRAINT roba_fk FOREIGN KEY (IdRoba) REFERENCES Roba(IdRoba),
CONSTRAINT cena_pk PRIMARY KEY (IdCena, IdRoba));

CREATE TABLE stavkaResenjaOR(
Rb NUMERIC(10) NOT NULL,
Broj NUMERIC(10) NOT NULL,
Godina NUMERIC(10)NOT NULL,
Kolicina NUMERIC(20),
IdRoba NUMERIC(10) NOT NULL,
CONSTRAINT resenjeOR_fk FOREIGN KEY (Broj, Godina) REFERENCES ResenjeOR(Broj,Godina),
CONSTRAINT robaOR_fk FOREIGN KEY (IdRoba) REFERENCES Roba(IdRoba),
CONSTRAINT stavkaResenjaOR_pk PRIMARY KEY (Rb,Broj, Godina));
