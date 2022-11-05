DROP DATABASE Markinhos_cut;
CREATE DATABASE Markinhos_cut;
USE Markinhos_cut;
CREATE TABLE funcionario(
cpf VARCHAR(11) NOT NULL,
nome VARCHAR(100),
ativo BOOLEAN,
PRIMARY KEY (cpf)
);

CREATE TABLE registroServico(
registroID INTEGER AUTO_INCREMENT,
cpf VARCHAR(11) NOT NULL,
metodoPagamento enum("Pix", "Crédito","Dinheiro","Débito"),
valorTotal DOUBLE,
dataServico DATE,
PRIMARY KEY (registroID),
FOREIGN KEY (cpf) REFERENCES funcionario (cpf)
);
CREATE TABLE servico(
servicoId INTEGER AUTO_INCREMENT,
preco DOUBLE,
nome VARCHAR (100),
ativo BOOLEAN,
PRIMARY KEY (servicoId)
);

CREATE TABLE quantServico(
servicoId INTEGER,
registroID INTEGER,
quantidade INTEGER,
FOREIGN KEY (servicoId) REFERENCES servico (servicoId),
FOREIGN KEY (registroID) REFERENCES registroServico (registroID)
on delete cascade
);

insert into funcionario (cpf, nome, ativo) values 
("48663164890", "Marcos", true),
("55698877455", "Henrique", true),
("77588933641", "Marcelo", true),
("11233255488", "Leandro", false),
("11158899966", "ana", false),
("22046678988", "joao", false),
("11447755588", "agata", false);

insert into servico (preco, nome, ativo) values 
(25.0, "Corte Degrade", true),
(10.0, "Sobrancelha", true),
(20.0, "Barba", true),
(35.0, "Mechas", false);

insert into servico (preco, nome, ativo) values
(50.0, "Progressiva", true);

insert into registroServico (cpf, metodoPagamento, valorTotal, dataServico) values 
("48663164890", "Pix", 0, '2020-03-01');

insert into quantServico ( registroID, servicoId,quantidade) values 
(1, 1, 2),
(1, 2, 3);

SELECT registroServico.cpf, registroServico.metodoPagamento, quantServico.quantidade, registroServico.valorTotal 
FROM registroServico INNER JOIN quantServico USING(registroID);

SELECT * FROM Markinhos_cut.servico;
