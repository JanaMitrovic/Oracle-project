--HORIZONTALNO PARTICIONISANJE

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
CONSTRAINT resenje_pk PRIMARY KEY (Broj, Godina))

PARTITION BY RANGE (DatumDonosenja)(
PARTITION prvi_kvartal2022 VALUES LESS THAN(TO_DATE('04/01/2022','MM/DD/YYYY')),
PARTITION drugi_kvartal2022 VALUES LESS THAN(TO_DATE('07/01/2022','MM/DD/YYYY')),
PARTITION treci_kvartal2022 VALUES LESS THAN(TO_DATE('10/01/2022','MM/DD/YYYY')),
PARTITION cetvrti_kvartal2022 VALUES LESS THAN(TO_DATE('01/01/2023','MM/DD/YYYY'))
);

SELECT * FROM Resenje PARTITION(prvi_kvartal2022);

--VERTIKALNO PARTICIONISANJE

CREATE TABLE Zapisnik_osnovno(
Broj NUMBER(10) NOT NULL,
Godina NUMBER(4) NOT NULL,
IdFilijala NUMBER(10) NOT NULL,
PIB NUMBER(9) NOT NULL,
CONSTRAINT filijala_vp_fk FOREIGN KEY (IdFilijala) REFERENCES Filijala(IdFilijala),
CONSTRAINT po_vp_fk FOREIGN KEY (PIB) REFERENCES poreskiObveznik(PIB),
CONSTRAINT nalog_vp1_pk PRIMARY KEY (Broj, Godina));

CREATE TABLE Zapisnik_detalji(
Broj NUMBER(10) NOT NULL,
Godina NUMBER(4) NOT NULL,
SifraObrazac VARCHAR2(3) NOT NULL,
DatumDonosenja DATE,
DatumPrijema DATE,
CONSTRAINT obrazac_vp_fk FOREIGN KEY (SifraObrazac) REFERENCES Obrazac(SifraObrazac),
CONSTRAINT nalog_vp2_pk PRIMARY KEY (Broj, Godina));

CREATE OR REPLACE VIEW Zapisnik AS 
SELECT zo.Broj, zo.Godina, zo.IdFilijala, zo.PIB, zd.SifraObrazac, zd.DatumDonosenja, zd.DatumPrijema
FROM Zapisnik_osnovno zo, Zapisnik_detalji zd 
WHERE zo.Broj = zd.Broj AND zo.Godina = zd.Godina;



CREATE OR REPLACE TRIGGER INSTEAD_INS_ZAPISNIK
INSTEAD OF INSERT ON Zapisnik
FOR EACH ROW
BEGIN
INSERT INTO Zapisnik_osnovno(Broj, Godina, IdFilijala, PIB)
VALUES (:NEW.Broj, :NEW.Godina, :NEW.IdFilijala, :NEW.PIB);
INSERT INTO Zapisnik_detalji(Broj, Godina, SifraObrazac, DatumDonosenja, DatumPrijema)
VALUES (:NEW.Broj, :NEW.Godina, :NEW.SifraObrazac, :NEW.DatumDonosenja,:NEW.DatumPrijema);
END;

INSERT INTO Zapisnik VALUES (1, 2022, 1, 111111111, '001', '01-APR-22', '02-APR-22');

CREATE OR REPLACE TRIGGER INSTEAD_UPD_ZAPISNIK
INSTEAD OF UPDATE ON Zapisnik
FOR EACH ROW
BEGIN
UPDATE Zapisnik_osnovno 
SET IdFilijala = :NEW.IdFilijala, PIB = :NEW.PIB
WHERE Broj = :NEW.Broj AND Godina = :NEW.Godina;
UPDATE Zapisnik_detalji 
SET SifraObrazac = :NEW.SifraObrazac, DatumDonosenja = :NEW.DatumDonosenja, DatumPrijema = :NEW.DatumPrijema
WHERE Broj = :NEW.Broj AND Godina = :NEW.Godina;
END;

UPDATE Zapisnik SET PIB = 222222222, SifraObrazac = '001' WHERE broj = 1 AND godina = 2022;

CREATE OR REPLACE TRIGGER INSTEAD_DEL_ZAPISNIK
INSTEAD OF DELETE ON Zapisnik
FOR EACH ROW
BEGIN
DELETE FROM Zapisnik_osnovno WHERE Broj = :OLD.Broj AND Godina = :OLD.Godina;
DELETE FROM Zapisnik_detalji WHERE Broj = :OLD.Broj AND Godina = :OLD.Godina;
END;

DELETE FROM Zapisnik WHERE broj = 1 AND godina = 2022;
