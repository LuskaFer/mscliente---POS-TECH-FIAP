package com.fiap.mscliente.application.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DadosPagamentoDTOTest {

    @Test
    void deveCriarDadosPagamentoDTO() {
        DadosPagamentoDTO dto = DadosPagamentoDTO.builder()
                .nomeTitular("Lucas")
                .numeroCartao("1234123412341234")
                .validade("12/28")
                .cvv("123")
                .build();

        assertThat(dto.getNomeTitular()).isEqualTo("Lucas");
        assertThat(dto.getNumeroCartao()).isEqualTo("1234123412341234");
    }
}
