create database lanchonete;

create table if not exists ingrediente
(
    ingrediente_id bigint      not null,
    descricao      varchar(100)   not null,
    preco          numeric(19, 2) not null,
    constraint ingrediente_pk primary key (ingrediente_id)
);

create table if not exists lanche
(
    lanche_id bigint      not null,
    descricao varchar(100)   not null,
    preco     numeric(19, 2) not null,
    constraint lanche_pk primary key (lanche_id)
);


create table if not exists cardapio
(
    cardapio_id    bigint not null,
    ingrediente_id bigint not null,
    lanche_id      bigint not null,
    constraint cardapio_pk primary key (cardapio_id)
);
