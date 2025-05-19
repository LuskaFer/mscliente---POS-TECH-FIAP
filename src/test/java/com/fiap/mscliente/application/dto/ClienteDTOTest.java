package com.fiap.mscliente.application.dto;

import com.fiap.mscliente.application.dto.EnderecoDTO;
import com.fiap.mscliente.application.dto.ClienteDTO;
import com.fiap.mscliente.domain.entity.Endereco;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ClienteDTOTest {

    @Test
    void deveCriarClienteDTOComEndereco() {
        EnderecoDTO endereco = EnderecoDTO.builder()
                .rua("Rua A")
                .numero("123")
                .bairro("Centro")
                .cidade("São Paulo")
                .estado("SP")
                .cep("12345678")
                .complemento("Apto 1")
                .build();

        ClienteDTO cliente = ClienteDTO.builder()
                .nome("Lucas")
                .cpf("12345678900")
                .email("lucas@fiap.com")
                .dataNascimento(LocalDate.of(2000, 1, 1))
                .endereco(endereco)
                .build();

        assertThat(cliente.getNome()).isEqualTo("Lucas");
        assertThat(cliente.getCpf()).isEqualTo("12345678900");
        assertThat(cliente.getEndereco().getCep()).isEqualTo("12345678");
    }
}
