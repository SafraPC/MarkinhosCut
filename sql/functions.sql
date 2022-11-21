USE MarkinhosCut;

DROP FUNCTION IF EXISTS getAllSellings;
DROP FUNCTION IF EXISTS getDeterminedSelling;

DELIMITER $$
CREATE FUNCTION getAllSellings()
RETURNS FLOAT DETERMINISTIC
BEGIN
DECLARE totalSeller FLOAT;
SELECT sum(total) INTO totalSeller
FROM Selling;

IF totalSeller = 0 THEN
SET totalSeller = -1;
END IF;

RETURN totalSeller;
END $$
DELIMITER ;


DELIMITER $$
CREATE FUNCTION getDeterminedSelling(dateIni DATE, dateFinal DATE)
RETURNS FLOAT DETERMINISTIC
BEGIN
DECLARE totalSeller FLOAT;
SELECT sum(total) INTO totalSeller
FROM Selling WHERE sellingDate BETWEEN dateIni AND dateFinal;

IF totalSeller = 0 THEN
SET totalSeller = -1;
END IF;

RETURN totalSeller;
END $$
DELIMITER ;

SELECT getDeterminedSelling("2022-11-18","2022-11-19");



