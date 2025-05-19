package com.fiap.mscliente.application.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnderecoDTOTest {

    @Test
    void deveCriarEnderecoDTO() {
        EnderecoDTO endereco = EnderecoDTO.builder()
                .rua("Rua A")
                .numero("123")
                .bairro("Centro")
                .cidade("São Paulo")
                .estado("SP")
                .cep("12345678")
                .complemento("Apto 1")
                .build();

        assertThat(endereco.getRua()).isEqualTo("Rua A");
        assertThat(endereco.getNumero()).isEqualTo("123");
        assertThat(endereco.getCep()).isEqualTo("12345678");
    }
}
