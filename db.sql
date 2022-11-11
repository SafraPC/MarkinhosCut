DROP DATABASE IF EXISTS MarkinhosCut;
CREATE DATABASE MarkinhosCut;
USE MarkinhosCut;

CREATE TABLE Professional(
professionalId INTEGER AUTO_INCREMENT PRIMARY KEY,
cpf VARCHAR(11) NOT NULL UNIQUE,
professionalName VARCHAR(100) NOT NULL UNIQUE,
isActive BOOLEAN
);

CREATE TABLE PaymentMethod(
paymentName VARCHAR(50) NOT NULL PRIMARY KEY
);

CREATE TABLE Selling(
sellingId INTEGER AUTO_INCREMENT PRIMARY KEY,
cpf VARCHAR(11) NOT NULL,
paymentName VARCHAR(50) NOT NULL,
total DOUBLE NOT NULL,
sellingDate DATE NOT NULL,
FOREIGN KEY (cpf) REFERENCES Professional (cpf),
FOREIGN KEY (paymentName) REFERENCES PaymentMethod (paymentName)
);

CREATE TABLE Service(
serviceId INTEGER AUTO_INCREMENT PRIMARY KEY,
price DOUBLE NOT NULL,
serviceName VARCHAR (100) NOT NULL,
isActive BOOLEAN NOT NULL
);

CREATE TABLE qtdService(
qtdId INTEGER AUTO_INCREMENT PRIMARY KEY,
serviceId INTEGER NOT NULL,
sellingId INTEGER NOT NULL,
quantity INTEGER NOT NULL,
price DOUBLE NOT NULL,
FOREIGN KEY (serviceId) REFERENCES Service (serviceId),
FOREIGN KEY (sellingId) REFERENCES Selling (sellingId)
ON DELETE CASCADE
);

INSERT INTO Professional (cpf, professionalName, isActive) VALUES
("48663164890", "Marcos", TRUE),
("55698877455", "Henrique", TRUE),
("77588933641", "Marcelo", TRUE),
("11233255488", "Leandro", TRUE),
("11158899966", "ana", FALSE),
("22046678988", "joao", FALSE),
("11447755588", "agata", FALSE);

INSERT INTO Service (price, serviceName, isActive) VALUES
(25.0, "Corte Degrade", TRUE),
(10.0, "Sobrancelha", TRUE),
(20.0, "Barba", TRUE),
(35.0, "Mechas", FALSE),
(50.0, "Progressiva", TRUE);

INSERT INTO PaymentMethod (paymentName) values
("Pix");

INSERT INTO Selling (cpf, paymentName, total, sellingDate) values
("48663164890", "Pix", 0, '2020-03-01');

insert into qtdService ( sellingId, serviceId,quantity,price) values
(1, 1, 2, 10.0),
(1, 2, 3, 10.0);


-- Professional Procedures

DELIMITER |
CREATE PROCEDURE createProfessional (nameParam VARCHAR(100), cpfParam VARCHAR(15))
       BEGIN
		   INSERT INTO Professional (cpf, professionalName, isActive) VALUES
		  (cpfParam, nameParam, TRUE);
		END |

CALL createProfessional("Safrudo","42413049872");

DELIMITER |
CREATE PROCEDURE editProfessional (nomeParam VARCHAR(100), cpfParam VARCHAR(15), professionalIdParam INTEGER)
       BEGIN
         UPDATE Professional SET professionalName = nomeParam, cpf = cpfParam WHERE professionalId = professionalIdParam;
		END |

CALL editProfessional("Henrique123","55698877455123123123", 2);
CALL editProfessional('Henriquee', 5569887745566,2);
SELECT * FROM Professional;

DELIMITER |
CREATE PROCEDURE changeProfessionalStatus (changeTo BOOLEAN,professionalIdParam INTEGER)
       BEGIN
         UPDATE Professional SET isActive = changeTo WHERE professionalId = professionalIdParam;
		END |

CALL changeProfessionalStatus(FALSE, 8);

-- Service Procedures

DELIMITER |
CREATE PROCEDURE createService (nameParam VARCHAR(100), priceParam DOUBLE)
       BEGIN
		   INSERT INTO Service (price, serviceName, isActive) VALUES
		  (priceParam, nameParam, TRUE);
		END |

CALL createService("Topete Ney",10.0);

DELIMITER |
CREATE PROCEDURE editService (nomeParam VARCHAR(100), priceParam DOUBLE, serviceIdParam INTEGER)
       BEGIN
         UPDATE Service SET serviceName = nomeParam, price = priceParam WHERE serviceId = serviceIdParam;
		END |

CALL editService("Corte Pica",100.0, 1);

DELIMITER |
CREATE PROCEDURE changeServiceStatus (changeTo BOOLEAN,serviceIdParam INTEGER)
       BEGIN
         UPDATE Service SET isActive = changeTo WHERE serviceId = serviceIdParam;
		END |

CALL changeServiceStatus(TRUE, 2);

-- SELLINGS

DELIMITER |
CREATE PROCEDURE createSelling (cpfParam VARCHAR(20) ,paymentParam VARCHAR(20), totalParam DOUBLE,sellingDateParam DATE)
       BEGIN
       INSERT INTO Selling (cpf, paymentName, total, sellingDate) VALUES
	  (cpfParam,paymentParam ,totalParam, sellingDateParam);
	   SELECT LAST_INSERT_ID() as createdElementId;
	   END |

CALL createSelling('48663164890',"Pix",200.0,'2020-03-01');

DELIMITER |
CREATE PROCEDURE createQtdSellingService (sellingIdParam INTEGER, serviceIdParam INTEGER, quantityParam INTEGER, priceParam DOUBLE)
       BEGIN
       INSERT INTO qtdService (sellingId, serviceId,quantity,price) values
	   (sellingIdParam, serviceIdParam, quantityParam, priceParam);
		END |

CALL createQtdSellingService(1,4,2,10.0);




