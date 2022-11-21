USE MarkinhosCut;

DROP VIEW IF EXISTS viewBiggestSellerMark;

CREATE VIEW viewBiggestSellerMark AS
SELECT biggestSellerMark.analyticDate AS 
OcourredDate, Professional.professionalName as ProfessionalName FROM biggestSellerMark INNER JOIN Professional
ON biggestSellerMark.cpf = Professional.cpf;


SELECT * FROM viewBiggestSellerMark;

CREATE VIEW viewProfessionalSeller AS
SELECT Professional.professionalName, Service.serviceName, Selling.total, Selling.sellingDate
FROM Selling INNER JOIN Professional USING(cpf)
INNER JOIN qtdService USING(sellingId)
INNER JOIN Service USING(serviceId)
ORDER BY Selling.sellingDate;

SELECT * FROM viewProfessionalSeller;

