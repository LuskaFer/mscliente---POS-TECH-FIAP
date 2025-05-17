-- init.sql

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(100),
    data_nascimento DATE
);

CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    rua VARCHAR(255),
    numero VARCHAR(10),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(2),
    cep VARCHAR(10),
    complemento VARCHAR(100),
    cliente_id BIGINT REFERENCES cliente(id) ON DELETE CASCADE
);

CREATE TABLE dados_pagamento (
    id SERIAL PRIMARY KEY,
    nome_titular VARCHAR(100),
    numero_cartao VARCHAR(20),
    validade VARCHAR(7),
    cvv VARCHAR(4),
    cliente_id BIGINT REFERENCES cliente(id) ON DELETE CASCADE
);
