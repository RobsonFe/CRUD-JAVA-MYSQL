create database dbcrud;
use dbcrud;
create table contatos(
id int primary key not null auto_increment,
nome varchar(50),
idade int,
dataCadastro date
);

describe contatos;
select * from contatos;