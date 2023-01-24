--DEFINISANJE DISTINCT TIPA

CREATE OR REPLACE TYPE Racun AS OBJECT(
brojRacuna NUMBER(18), 
MEMBER FUNCTION get_brojRacuna RETURN NUMBER,
MEMBER FUNCTION set_brojRacuna RETURN NUMBER)
FINAL;

CREATE OR REPLACE TYPE BODY Racun AS 
MEMBER FUNCTION get_brojRacuna RETURN NUMBER IS
	BEGIN
		RETURN SELF.brojRacuna;
	END;
MEMBER FUNCTION get_brojRacuna RETURN NUMBER IS
	BEGIN
		RETURN SELF.brojRacuna;
	END;
END;



CREATE OR REPLACE TYPE Racun AS OBJECT(
brojRacuna NUMBER(20),
MEMBER FUNCTION get_brojRacuna RETURN NUMBER,
CONSTRUCTOR FUNCTION Racun(brojRacuna NUMBER) 
RETURN SELF AS RESULT)
INSTANTIABLE NOT FINAL;

CREATE OR REPLACE TYPE BODY Racun AS
MEMBER FUNCTION get_brojRacuna RETURN NUMBER IS
	BEGIN
		RETURN SELF.brojRacuna;
	END;
CONSTRUCTOR FUNCTION Racun(brojRacuna NUMBER)
RETURN SELF AS RESULT
AS
BEGIN
IF brojRacuna >= 100000000000000000 AND brojRacuna < 1000000000000000000
THEN SELF.brojRacuna := brojRacuna;
ELSE 
BEGIN
RAISE_APPLICATION_ERROR(NUM => -20000, MSG => 'Broj racuna mora imati tacno 18 cifara!');
END;
END IF;
RETURN;
END;
END;

CREATE TABLE platniPromet(
IdPlatniPromet  NUMBER(9) PRIMARY KEY,
Banka VARCHAR2(30),
Racun Racun);

INSERT INTO platniPromet VALUES (1, 'Unicredit Bank', Racun(100236154895214782));

SELECT pp.IdPlatniPromet AS Id, pp.Banka AS Banka, pp.Racun.get_brojRacuna() AS Racun FROM platniPromet pp ORDER BY pp.IdPlatniPromet;

UPDATE platniPromet pp SET pp.Racun.brojRacuna = 123652199947511366 WHERE pp.IdPlatniPromet = 1;

DELETE platniPromet  pp WHERE pp.Racun.brojRacuna = 123652199947511366;


--DEFINISANJE STRUKTUIRANOG TIPA
CREATE OR REPLACE TYPE NazivPO AS OBJECT(
ime VARCHAR2(20),
tip VARCHAR2(10),
MEMBER FUNCTION get_ime RETURN VARCHAR2,
MEMBER FUNCTION get_tip RETURN VARCHAR2,
CONSTRUCTOR FUNCTION NazivPO(ime VARCHAR2, tip VARCHAR2) 
RETURN SELF AS RESULT)
INSTANTIABLE NOT FINAL;

CREATE OR REPLACE TYPE BODY NazivPO AS
MEMBER FUNCTION get_ime RETURN VARCHAR2 IS
	BEGIN
		RETURN SELF.ime;
	END;
MEMBER FUNCTION get_tip RETURN VARCHAR2 IS
	BEGIN
		RETURN SELF.tip;
	END;
CONSTRUCTOR FUNCTION NazivPO(ime VARCHAR2, tip VARCHAR2)
RETURN SELF AS RESULT
AS
BEGIN
SELF.ime := ime;
IF tip NOT IN ('doo', 'ad', 'od', 'kd')
THEN BEGIN
RAISE_APPLICATION_ERROR(NUM => -20000, MSG => 'Tip preduzaca moze uzati samo neku od sledecih vrednosti: doo, ad, od, kd!');
END;
ELSE SELF.tip := tip;
END IF;
RETURN;
END;
END;


CREATE TABLE Mesto(
IdMesto NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(20));

INSERT INTO Mesto VALUES (1,'Beograd');
INSERT INTO Mesto VALUES (2,'Uzice');
SELECT * FROM Mesto;
SELECT IdMesto, Naziv FROM Mesto ORDER BY IdMesto;

CREATE TABLE Ulica(
IdUlica NUMBER(10),
Naziv VARCHAR2(50) NOT NULL,
IdMesto NUMBER(10)NOT NULL,
CONSTRAINT mesto_fk FOREIGN KEY (IdMesto) REFERENCES Mesto(IdMesto),
CONSTRAINT ulica_pk PRIMARY KEY (IdUlica, IdMesto)); 

INSERT INTO Ulica VALUES (1, 'Bulevar Peka Dapcevica', 1);
SELECT * FROM Ulica;

CREATE TABLE Broj(
IdBroj NUMBER(10),
Broj VARCHAR2(20),
IdUlica NUMBER(10)NOT NULL,
IdMesto NUMBER(10)NOT NULL,
CONSTRAINT ulica_mesto_fk FOREIGN KEY (IdMesto, IdUlica) REFERENCES Ulica(IdMesto, IdUlica),
CONSTRAINT broj_pk PRIMARY KEY (IdBroj, IdUlica, IdMesto));

INSERT INTO Broj VALUES (1,'29',1,1);
SELECT * FROM Broj;
SELECT COUNT(1) FROM Broj WHERE Broj = '29' AND IdUlica = 2 AND IdMesto = 1;

CREATE TABLE poreskiObveznik(
PIB  NUMBER(9) PRIMARY KEY,
naziv NazivPO,
IdMesto  NUMBER(10) NOT NULL,
IdUlica NUMBER(10) NOT NULL,
IdBroj NUMBER(10) NOT NULL,
CONSTRAINT adresa_fk FOREIGN KEY (IdMesto, IdUlica, IdBroj) REFERENCES
Broj(IdMesto, IdUlica, IdBroj));

ALTER TABLE poreskiObveznik DROP COLUMN naziv;
ALTER TABLE poreskiObveznik  ADD naziv NazivPO;


INSERT INTO poreskiObveznik VALUES (111111111, 1,1,1, NazivPO('Atlantic Štark', 'doo'));

SELECT po.PIB, po.naziv.get_ime() AS NazivPO, po.naziv.get_tip() AS TipPreduzeca FROM poreskiObveznik po;

SELECT po.PIB, m.IdMesto, m.Naziv AS Mesto, u.IdUlica, u.Naziv AS Ulica, b.IdBroj, b.Broj, po.naziv.get_ime() AS NazivPO, po.naziv.get_tip() AS TipPreduzeca FROM poreskiObveznik po JOIN Broj b ON po.IdBroj = b.IdBroj JOIN Ulica u ON po.IdUlica = u.IdUlica JOIN Mesto m ON po.IdMesto = m.IdMesto;
SELECT po.PIB, m.IdMesto, m.Naziv AS Mesto, u.IdUlica, u.Naziv AS Ulica, b.IdBroj, b.Broj, po.naziv.get_ime() AS NazivPO, po.naziv.get_tip() AS TipPreduzeca FROM poreskiObveznik po JOIN Mesto m ON po.IdMesto = m.IdMesto JOIN Ulica u ON po.IdUlica = u.IdUlica JOIN Broj b ON po.IdBroj = b.IdBroj;

UPDATE poreskiObveznik po SET po.naziv.ime = 'Štark' WHERE po.PIB = 123456789;

DELETE poreskiObveznik po WHERE po.naziv.get_ime() = 'Štark';

--------------------------------------------------------------------------------
CREATE TABLE Filijala(
IdFilijala NUMBER(10) PRIMARY KEY,
IdMesto NUMBER(10),
CONSTRAINT mesto2_fk FOREIGN KEY (IdMesto) REFERENCES Mesto(IdMesto));

INSERT INTO Filijala VALUES (1, 1);
INSERT INTO Filijala VALUES (2, 2);
SELECT * FROM Filijala;


CREATE TABLE Obrazac(
SifraObrazac VARCHAR2(3) PRIMARY KEY,
Naziv VARCHAR2(20));

INSERT INTO Obrazac VALUES ('000', 'nalog');
SELECT * FROM Obrazac;

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


--UPISUJE IDMESTO ZA UNETO IDFILIJALA
CREATE OR REPLACE TRIGGER INS_NALOG
BEFORE INSERT ON Nalog
FOR EACH ROW
DECLARE v_mesto NUMBER(10);
BEGIN
SELECT IdMesto INTO v_mesto FROM Filijala WHERE IdFilijala = :NEW.IdFilijala;
:NEW.IdMesto := v_mesto;
END;

INSERT INTO Nalog VALUES (2, 2022, 1, '000', '04-DEC-22', '08-DEC-22', TIMESTAMP '2022-12-08 13:30:00', NULL, 123456789);
INSERT INTO Nalog n (Broj, Godina, IdFilijala, SifraObrazac, DatumIzdavanja, DatumUrucenja, VremeUrucenja, IdMesto, PIB) VALUES (3, 2022, 1, '000', null, null, TIMESTAMP '2022-09-03 14:00:00.0', 1, 222222222);

SELECT * FROM Nalog;
SELECT n.Broj, n.Godina, n.DatumIzdavanja, n.DatumUrucenja, n.VremeUrucenja, m.IdMesto, m.Naziv AS Mesto, f.IdFilijala, o.SifraObrazac, o.Naziv AS Obrazac, po.PIB FROM Nalog n JOIN Filijala f ON n.IdFilijala = f.IdFilijala JOIN Mesto m ON f.IdMesto = m.IdMesto JOIN Obrazac o ON n.SifraObrazac = o.SifraObrazac JOIN PoreskiObveznik po ON n.PIB = po.PIB;

--AZURIRA IDMESTO NA OSNOVU NOVE VREDNOSTI IDFILIJALA
CREATE OR REPLACE TRIGGER UPD_FILIJALA
BEFORE UPDATE OF IdFilijala ON Nalog
FOR EACH ROW
DECLARE
v_novo_mesto NUMBER(10);
BEGIN
SELECT IdMesto INTO v_novo_mesto FROM Filijala WHERE IdFilijala = :NEW.IdFilijala;
:NEW.IdMesto := v_novo_mesto;
END;

UPDATE Nalog SET IdFilijala = 1 WHERE Broj = 1 AND Godina = 2022;

SELECT * FROM Nalog;

--ZABRANA IZMENE IDMESTO U NALOGU
CREATE OR REPLACE TRIGGER UPD_MESTO_ZABRANA
BEFORE UPDATE OF IdMesto ON Nalog
FOR EACH ROW 
BEGIN
RAISE_APPLICATION_ERROR(NUM => -20000, MSG => 'Nije moguca direktno menjati IdMesto!');
END;

UPDATE Nalog SET IdMesto = 1 WHERE Broj = 1 AND Godina = 2022;

--AZURIRA IDMESTO U NALOGU NA OSNOVU NOVE VREDNOSTI IDMESTO U FILIJALI
CREATE OR REPLACE TRIGGER UPD_MESTO
AFTER UPDATE OF IdMesto ON Filijala
FOR EACH ROW
DECLARE
PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_MESTO_ZABRANA DISABLE';
UPDATE Nalog 
SET IdMesto = :NEW.IdMesto
WHERE IdFilijala = :NEW.IdFilijala;
BEGIN
EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_MESTO_ZABRANA ENABLE';
END;
END;

UPDATE Filijala SET IdMesto = 2 WHERE IdFilijala = 2;
SELECT * FROM FILIJALA;
SELECT * FROM Nalog;

--------------------------------------------------------------------------------

CREATE TABLE vrstaPrihoda(
IdVrstaPrihoda NUMBER(10) PRIMARY KEY,
Naziv VARCHAR2(20));

INSERT INTO vrstaPrihoda VALUES (1, 'VP1');
SELECT * FROM vrstaPrihoda;

CREATE TABLE stavkaNaloga(
Rb NUMERIC(10) NOT NULL,
Broj NUMERIC(10) NOT NULL,
Godina NUMERIC(10)NOT NULL,
DatumOd DATE,
DatumDo DATE,
IdVrstaPrihoda NUMERIC(10) NOT NULL,
DatumIzdavanja DATE,
CONSTRAINT nalog_fk FOREIGN KEY (Broj, Godina) REFERENCES Nalog(Broj,Godina),
CONSTRAINT vp_fk FOREIGN KEY (IdVrstaPrihoda) REFERENCES VrstaPrihoda(IdVrstaPrihoda),
CONSTRAINT stavkaNaloga_pk PRIMARY KEY (Rb,Broj, Godina));


--UPISUJE DATUMIZDAVANJA NA OSNOVU BROJA I GODINE
CREATE OR REPLACE TRIGGER INS_STAVKA_NALOGA
BEFORE INSERT ON StavkaNaloga
FOR EACH ROW
DECLARE v_datumIzdavanja DATE;
BEGIN
SELECT DatumIzdavanja INTO v_datumIzdavanja FROM Nalog WHERE Broj =: NEW.Broj AND Godina = :NEW.Godina;
:NEW.DatumIzdavanja := v_datumIzdavanja;
END;


INSERT INTO StavkaNaloga VALUES (3, 1, 2022, '10-DEC-22', '20-DEC-22', 1, NULL);

SELECT * FROM StavkaNaloga;
SELECT sn.rb, n.broj, n.godina, sn.datumOd, sn.datumDo, vp.Naziv, sn.datumIzdavanja FROM stavkaNaloga sn JOIN Nalog n ON sn.broj = n.broj AND sn.godina = n.godina JOIN VrstaPrihoda vp ON sn.IdVrstaPrihoda = vp.IdVrstaPrihoda WHERE sn.broj = 1 AND sn.godina = 2022 ORDER BY sn.rb;

--ZABRANA IZMENE DATUMIZDAVANJA U STAVCI NALOGA
CREATE OR REPLACE TRIGGER UPD_DATUM_ZABRANA
BEFORE UPDATE OF DatumIzdavanja ON StavkaNaloga
FOR EACH ROW 
BEGIN
RAISE_APPLICATION_ERROR(NUM => -20000, MSG => 'Nije moguce direktno menjati Datum Izdavanja!');
END;

UPDATE StavkaNaloga SET DatumIzdavanja = '04-DEC-22' WHERE Rb = 1 AND Broj = 1 AND Godina = 2022;

SELECT * FROM StavkaNaloga;

--AZURIRANJE DATUM IZDAVANJA U STAVCI NALOGA NAKON AZURIRANJA DATUMIZDAVANJA U NALOGU
CREATE OR REPLACE TRIGGER UPD_DATUM_IZDAVANJA
AFTER UPDATE OF DatumIzdavanja ON Nalog
FOR EACH ROW
DECLARE
PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_DATUM_ZABRANA DISABLE';
UPDATE StavkaNaloga 
SET DatumIzdavanja = :NEW.DatumIzdavanja
WHERE Broj = :NEW.Broj AND Godina = :NEW.Godina;
BEGIN
EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_DATUM_ZABRANA ENABLE';
END;
END;

UPDATE Nalog  SET DatumIzdavanja = '03-DEC-22' WHERE Broj = 1 AND Godina = 2022;

SELECT * FROM Nalog;

SELECT * FROM StavkaNaloga;





