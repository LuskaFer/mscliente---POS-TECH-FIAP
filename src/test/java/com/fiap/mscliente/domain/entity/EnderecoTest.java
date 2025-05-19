package com.fiap.mscliente.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    void deveCriarEnderecoComValoresCorretos() {
        Endereco endereco = Endereco.builder()
                .rua("Rua A")
                .numero("123")
                .complemento("Apto 1")
                .bairro("Centro")
                .cep("12345-678")
                .estado("SP")
                .cidade("São Paulo")
                .build();

        assertEquals("Rua A", endereco.getRua());
        assertEquals("123", endereco.getNumero());
        assertEquals("Apto 1", endereco.getComplemento());
        assertEquals("Centro", endereco.getBairro());
        assertEquals("12345-678", endereco.getCep());
        assertEquals("SP", endereco.getEstado());
        assertEquals("São Paulo", endereco.getCidade());
    }

    @Test
    void devePermitirModificarEndereco() {
        Endereco endereco = new Endereco();
        endereco.setRua("Rua B");
        endereco.setCidade("Curitiba");

        assertEquals("Rua B", endereco.getRua());
        assertEquals("Curitiba", endereco.getCidade());
    }
}
