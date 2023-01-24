--STORING DERIVABLE VALUES

CREATE OR REPLACE NONEDITIONABLE PACKAGE RStavka AS 
Broj NUMBER(10);
Godina NUMBER(4);
END RStavka;


CREATE OR REPLACE TRIGGER UKUPAN_IZNOS_RESENJE1
BEFORE INSERT OR UPDATE OR DELETE ON StavkaResenja 
FOR EACH ROW
BEGIN
IF (INSERTING OR UPDATING)
THEN
BEGIN 
RStavka.Broj := :NEW.Broj;
RStavka.Godina := :NEW.Godina; 
END;
ELSE
BEGIN 
RStavka.Broj := :OLD.Broj;
RStavka.Godina := :OLD.Godina; 
END;
END IF;
END;

CREATE OR REPLACE TRIGGER UPD_RESENJE_UKUPNO_ZABRANA
BEFORE UPDATE OF UkupanIznos ON Resenje
FOR EACH ROW
BEGIN
RAISE_APPLICATION_ERROR(NUM => -20000,MSG => 'Ne mozete direktno menjati vrednost UkupanIznos!');
END;


CREATE OR REPLACE PROCEDURE ISKLJUCI_TRIGER AS                                                                           
PRAGMA AUTONOMOUS_TRANSACTION;                                                                                                                
BEGIN                                                                                                                                         
EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_RESENJE_UKUPNO_ZABRANA DISABLE';                                               
END ISKLJUCI_TRIGER;


CREATE OR REPLACE PROCEDURE UKLJUCI_TRIGER AS                                      
PRAGMA AUTONOMOUS_TRANSACTION;                                                                             
BEGIN                                                                                                                                                                                    
EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_RESENJE_UKUPNO_ZABRANA ENABLE';      
END UKLJUCI_TRIGER;

CREATE OR REPLACE PROCEDURE UKUPAN_IZNOS_RESENJA
(Br IN NUMBER, God IN NUMBER) AS
Ukupno NUMBER := 0;
BEGIN
SELECT SUM(sr.iznos) INTO Ukupno
FROM StavkaResenja sr
WHERE sr.Broj = Br AND sr.Godina = God;
UPDATE Resenje
SET UkupanIznos = Ukupno
WHERE Broj = Br AND Godina = God;
END;



CREATE OR REPLACE TRIGGER UKUPAN_IZNOS_RESENJE2
AFTER INSERT OR UPDATE OR DELETE ON StavkaResenja
DECLARE
Broj NUMBER(10) := RStavka.Broj;
Godina NUMBER(10) := RStavka.Godina;
BEGIN
ISKLJUCI_TRIGER();
UKUPAN_IZNOS_RESENJA(Broj, Godina);
UKLJUCI_TRIGER();
END;


--REPEATING SINGLE DETAIL WITH MASTER

CREATE OR REPLACE NONEDITIONABLE PACKAGE RobaPaket AS 
IdRoba NUMBER(10);
END RobaPaket;

CREATE OR REPLACE TRIGGER ROBA_CENA1
BEFORE INSERT OR UPDATE OR DELETE ON Cena
FOR EACH ROW
BEGIN
IF (INSERTING OR UPDATING)
THEN
BEGIN 
RobaPaket.IdRoba := :NEW.IdRoba; 
END;
ELSE
BEGIN 
RobaPaket.IdRoba := :OLD.IdRoba; 
END;
END IF;
END;

CREATE OR REPLACE TRIGGER UPD_AKTUELNA_CENA_ZABRANA
BEFORE UPDATE OF AktuelnaCena ON Roba
FOR EACH ROW
BEGIN
RAISE_APPLICATION_ERROR(NUM => -20000,MSG => 'Ne mozete direktno menjati vrednost AktuelnaCena!');
END;

CREATE OR REPLACE PROCEDURE ISKLJUCI_TRIGER_CENA AS                                                                           
PRAGMA AUTONOMOUS_TRANSACTION;                                                                                                                
BEGIN                                                                                                                                         
EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_AKTUELNA_CENA_ZABRANA DISABLE';                                               
END ISKLJUCI_TRIGER_CENA;

CREATE OR REPLACE PROCEDURE UKLJUCI_TRIGER_CENA AS                                                                           
PRAGMA AUTONOMOUS_TRANSACTION;                                                                                                                
BEGIN                                                                                                                                         
EXECUTE IMMEDIATE 'ALTER TRIGGER UPD_AKTUELNA_CENA_ZABRANA ENABLE';                                               
END UKLJUCI_TRIGER_CENA;

CREATE OR REPLACE PROCEDURE AKTUELNA_CENA_ROBE
(Roba IN NUMBER) AS
Aktuelna NUMBER := 0;
BEGIN
BEGIN
SELECT Iznos INTO Aktuelna FROM Cena
WHERE IdRoba = Roba AND DatumOd=(SELECT MAX(DatumOd) FROM Cena WHERE IdRoba = Roba AND DatumOd <= SYSDATE);
EXCEPTION WHEN no_data_found THEN Aktuelna := null;
END;
UPDATE Roba
SET AktuelnaCena = Aktuelna
WHERE IdRoba = Roba;
END;

CREATE OR REPLACE TRIGGER ROBA_CENA2
AFTER INSERT OR UPDATE OR DELETE ON Cena
DECLARE
Roba NUMBER(10) := RobaPaket.IdRoba;
BEGIN
ISKLJUCI_TRIGER_CENA();
AKTUELNA_CENA_ROBE(Roba);
UKLJUCI_TRIGER_CENA();
END;


