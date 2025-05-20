package com.fiap.mscliente.application.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DadosPagamentoDTOTest {

    @Test
    void deveCobrirConstrutoresGettersSettersBuilder() {
        DadosPagamentoDTO dto = new DadosPagamentoDTO();
        dto.setNumeroCartao("1234567890123456");
        dto.setNomeTitular("Lucas Godoy");
        dto.setValidade("12/29");
        dto.setCvv("123");

        assertEquals("1234567890123456", dto.getNumeroCartao());
        assertEquals("Lucas Godoy", dto.getNomeTitular());
        assertEquals("12/29", dto.getValidade());
        assertEquals("123", dto.getCvv());

        DadosPagamentoDTO dto2 = new DadosPagamentoDTO("1111222233334444", "Ana", "11/30", "999");
        assertEquals("Ana", dto2.getNomeTitular());

        DadosPagamentoDTO dto3 = DadosPagamentoDTO.builder()
                .numeroCartao("5555666677778888")
                .nomeTitular("Bruno")
                .validade("01/28")
                .cvv("321")
                .build();
        assertEquals("Bruno", dto3.getNomeTitular());
        assertNotNull(dto.toString());
        assertNotEquals(dto, dto2);
        assertNotEquals(dto.hashCode(), dto2.hashCode());

    }
}
