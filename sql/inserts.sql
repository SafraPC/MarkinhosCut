USE MarkinhosCut;

INSERT INTO Professional (cpf, professionalName, isActive) VALUES
("48663164890", "Marcos", TRUE),
("55698877455", "Henrique", TRUE),
("77588933641", "Marcelo", TRUE),
("11233255488", "Leandro", TRUE),
("11158899966", "Ana", FALSE),
("22046678988", "Joao", FALSE),
("11447755588", "Agata", FALSE);

INSERT INTO Service (price, serviceName, isActive) VALUES
(25.0, "Corte Degrade", TRUE),
(10.0, "Sobrancelha", TRUE),
(20.0, "Barba", TRUE),
(35.0, "Mechas", FALSE),
(50.0, "Progressiva", TRUE);

INSERT INTO PaymentMethod (paymentName) values
("Pix");
INSERT INTO PaymentMethod (paymentName) values
("Crédito"),
("Débito"),
("Dinheiro");

INSERT INTO Selling(cpf,paymentName,total,sellingDate) VALUES
('48663164890',"Pix",35.0,'2022-11-19'),
('11233255488',"Pix",35.0,'2022-11-18'),
('55698877455',"Pix",35.0,'2022-11-20');


INSERT INTO qtdService(sellingId,serviceId,quantity,price) VALUES
(1,1,1,25.0),
(1,2,1,10.0),
(2,1,1,25.0),
(2,2,1,10.0),
(3,1,1,25.0),
(3,2,1,10.0);
