USE MarkinhosCut;

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
INSERT INTO PaymentMethod (paymentName) values
("Crédito"),
("Débito"),
("Dinheiro");

INSERT INTO Selling (cpf, paymentName, total, sellingDate) values
("48663164890", "Pix", 0, '2020-03-01'),
("55698877455", "Pix", 30, '2022-11-19'),
("55698877455", "Pix", 30, '2022-11-19'),
("55698877455", "Pix", 30, '2022-11-19');

insert into qtdService ( sellingId, serviceId,quantity,price) values
(1, 1, 2, 10.0),
(2, 1, 2, 10.0),
(3, 1, 2, 10.0),
(4, 1, 2, 10.0),
(1, 2, 3, 10.0);
