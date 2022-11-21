USE MarkinhosCut;

DROP PROCEDURE IF EXISTS createProfessional;
DROP PROCEDURE IF EXISTS editProfessional;
DROP PROCEDURE IF EXISTS changeProfessionalStatus;


DROP PROCEDURE IF EXISTS createService;
DROP PROCEDURE IF EXISTS editService;
DROP PROCEDURE IF EXISTS changeServiceStatus;

DROP PROCEDURE IF EXISTS createSelling;
DROP PROCEDURE IF EXISTS createQtdSellingService;
DROP PROCEDURE IF EXISTS getResultCharts;
DROP PROCEDURE IF EXISTS getRegisterSelling;


-- Professional Procedures

DELIMITER |
CREATE PROCEDURE createProfessional (nameParam VARCHAR(100), cpfParam VARCHAR(15))
       BEGIN
		   INSERT INTO Professional (cpf, professionalName, isActive) VALUES
		  (cpfParam, nameParam, TRUE);
		END |

DELIMITER |
CREATE PROCEDURE editProfessional (nomeParam VARCHAR(100), cpfParam VARCHAR(15), professionalIdParam INTEGER)
       BEGIN
         UPDATE Professional SET professionalName = nomeParam, cpf = cpfParam WHERE professionalId = professionalIdParam;
		END |


DELIMITER |
CREATE PROCEDURE changeProfessionalStatus (changeTo BOOLEAN,professionalIdParam INTEGER)
       BEGIN
         UPDATE Professional SET isActive = changeTo WHERE professionalId = professionalIdParam;
		END |


-- Service Procedures

DELIMITER |
CREATE PROCEDURE createService (nameParam VARCHAR(100), priceParam DOUBLE)
       BEGIN
		   INSERT INTO Service (price, serviceName, isActive) VALUES
		  (priceParam, nameParam, TRUE);
		END |


DELIMITER |
CREATE PROCEDURE editService (nomeParam VARCHAR(100), priceParam DOUBLE, serviceIdParam INTEGER)
       BEGIN
         UPDATE Service SET serviceName = nomeParam, price = priceParam WHERE serviceId = serviceIdParam;
		END |


DELIMITER |
CREATE PROCEDURE changeServiceStatus (changeTo BOOLEAN,serviceIdParam INTEGER)
       BEGIN
         UPDATE Service SET isActive = changeTo WHERE serviceId = serviceIdParam;
		END |


-- SELLINGS

DELIMITER |
CREATE PROCEDURE createSelling (cpfParam VARCHAR(20) ,paymentParam VARCHAR(20), totalParam DOUBLE,sellingDateParam DATE)
       BEGIN
       INSERT INTO Selling (cpf, paymentName, total, sellingDate) VALUES
	  (cpfParam,paymentParam ,totalParam, sellingDateParam);
	   SELECT LAST_INSERT_ID() as createdElementId;
	   END |

DELIMITER |
CREATE PROCEDURE createQtdSellingService (sellingIdParam INTEGER, serviceIdParam INTEGER, quantityParam INTEGER, priceParam DOUBLE)
       BEGIN
       INSERT INTO qtdService (sellingId, serviceId,quantity,price) values
	   (sellingIdParam, serviceIdParam, quantityParam, priceParam);
		END |
        

DELIMITER |
CREATE PROCEDURE getResultCharts (dateInitialParam VARCHAR(20) ,dateFinalParam VARCHAR(20), 
professionalNameParam VARCHAR(50),paymentNameParam VARCHAR(50))
       BEGIN
		   SELECT sellingDate, sum(total) as totalDate FROM Selling INNER JOIN Professional using(cpf) WHERE
		   sellingDate BETWEEN dateInitialParam AND dateFinalParam AND professionalName LIKE professionalNameParam
		   AND paymentName LIKE paymentNameParam GROUP BY sellingDate ORDER BY sellingDate ;
	   END |


DELIMITER |
CREATE PROCEDURE getRegisterSelling (dateInitialParam VARCHAR(20) ,dateFinalParam VARCHAR(20),
professionalNameParam VARCHAR(50),paymentNameParam VARCHAR(50))
       BEGIN
		   SELECT Selling.sellingId, Professional.professionalName, Selling.paymentName, Selling.total, Selling.sellingDate
           FROM Selling INNER JOIN Professional using(cpf) WHERE
		   sellingDate BETWEEN dateInitialParam AND dateFinalParam AND professionalName LIKE professionalNameParam
		   AND paymentName LIKE paymentNameParam ORDER BY sellingDate ;
	   END |
       
       
DELIMITER |
CREATE PROCEDURE getRegisterSellingDetailed (dateInitialParam VARCHAR(20) ,dateFinalParam VARCHAR(20),
professionalNameParam VARCHAR(50),paymentNameParam VARCHAR(50))
       BEGIN
			SELECT * FROM Selling INNER JOIN Professional USING(cpf)
			INNER JOIN qtdService USING(sellingId)
			INNER JOIN Service USING(serviceId)
			WHERE sellingDate BETWEEN dateInitialParam AND dateFinalParam AND professionalName LIKE professionalNameParam
			AND paymentName LIKE paymentNameParam
			ORDER BY Selling.sellingDate ;
	   END |
       
SELECT * FROM Selling;
       
