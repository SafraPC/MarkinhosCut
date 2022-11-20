USE MarkinhosCut;

DROP VIEW IF EXISTS viewBiggestSellerMark;

CREATE VIEW viewBiggestSellerMark AS
SELECT biggestSellerMark.analyticDate AS 
OcourredDate, Professional.professionalName as ProfessionalName FROM biggestSellerMark INNER JOIN Professional
ON biggestSellerMark.cpf = Professional.cpf;


SELECT * FROM viewBiggestSellerMark;

