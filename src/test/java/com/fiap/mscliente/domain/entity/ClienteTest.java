package com.fiap.mscliente.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ClienteTest {

    @Test
    void deveCriarClienteComEndereco() {
        Endereco endereco = Endereco.builder()
                .rua("Rua A")
                .numero("123")
                .bairro("Centro")
                .cidade("SP")
                .estado("SP")
                .cep("12345678")
                .complemento("Apto 1")
                .build();

        Cliente cliente = Cliente.builder()
                .nome("Lucas")
                .cpf("12345678900")
                .email("lucas@fiap.com")
                .dataNascimento(LocalDate.of(2000, 1, 1))
                .endereco(endereco)
                .build();

        assertThat(cliente.getNome()).isEqualTo("Lucas");
        assertThat(cliente.getEndereco().getCidade()).isEqualTo("SP");
    }
}
