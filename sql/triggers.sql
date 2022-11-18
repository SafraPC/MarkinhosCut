USE MarkinhosCut;

DROP TRIGGER IF EXISTS createBiggestSellerMark;

DELIMITER $$
CREATE TRIGGER createBiggestSellerMark
AFTER INSERT ON Selling
FOR EACH ROW
BEGIN

DECLARE totalSeller INTEGER;

SELECT COUNT(*) INTO totalSeller from Selling 
INNER JOIN Professional WHERE sellingDate = NEW.sellingDate AND Professional.cpf = NEW.cpf;

IF totalSeller = 10 THEN
INSERT INTO biggestSellerMark (analyticDate, cpf) 
VALUES (NEW.sellingDate, NEW.cpf);
END IF;

END
$$
