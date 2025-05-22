package com.fiap.mscliente.application.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnderecoDTOTest {

    @Test
    void deveCobrirConstrutoresGettersSettersBuilder() {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setRua("Rua A");
        dto.setNumero("123");
        dto.setBairro("Centro");
        dto.setCidade("São Paulo");
        dto.setEstado("SP");
        dto.setCep("00000-000");
        dto.setComplemento("Apto 10");

        assertEquals("Rua A", dto.getRua());
        assertEquals("123", dto.getNumero());
        assertEquals("Centro", dto.getBairro());

        EnderecoDTO dto2 = new EnderecoDTO("Rua B", "456", "Bairro B", "Campinas", "SP", "11111-111", "Casa 2");
        assertEquals("Rua B", dto2.getRua());

        EnderecoDTO dto3 = EnderecoDTO.builder()
                .rua("Rua C")
                .numero("789")
                .bairro("Bairro C")
                .cidade("Osasco")
                .estado("SP")
                .cep("22222-222")
                .complemento("Fundos")
                .build();
        assertEquals("Fundos", dto3.getComplemento());
        assertNotNull(dto.toString());
        assertNotEquals(dto, dto2);
        assertNotEquals(dto.hashCode(), dto2.hashCode());

    }
}
