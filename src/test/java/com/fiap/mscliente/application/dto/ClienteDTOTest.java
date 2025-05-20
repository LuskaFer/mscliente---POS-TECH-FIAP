package com.fiap.mscliente.application.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClienteDTOTest {

    @Test
    void deveCobrirConstrutoresGettersSettersBuilder() {
        EnderecoDTO endereco = new EnderecoDTO("Rua A", "123", "Centro", "SP", "SP", "00000-000", "Apto 10");

        ClienteDTO dto = new ClienteDTO();
        dto.setNome("Lucas");
        dto.setCpf("12345678901");
        dto.setEmail("lucas@teste.com");
        dto.setDataNascimento(LocalDate.of(2000, 1, 1));
        dto.setEndereco(endereco);

        assertEquals("Lucas", dto.getNome());
        assertEquals("12345678901", dto.getCpf());
        assertEquals("lucas@teste.com", dto.getEmail());
        assertEquals(LocalDate.of(2000, 1, 1), dto.getDataNascimento());
        assertEquals("Rua A", dto.getEndereco().getRua());

        ClienteDTO dto2 = new ClienteDTO("Ana", "98765432100", "ana@teste.com", LocalDate.of(1990, 5, 15), endereco);
        assertEquals("Ana", dto2.getNome());

        ClienteDTO dto3 = ClienteDTO.builder()
                .nome("Bruno")
                .cpf("11122233344")
                .email("bruno@teste.com")
                .dataNascimento(LocalDate.of(1985, 3, 20))
                .endereco(endereco)
                .build();
        assertEquals("Bruno", dto3.getNome());
        assertNotNull(dto.toString());
        assertNotEquals(dto, dto2);  // força equals
        assertNotEquals(dto.hashCode(), dto2.hashCode());

    }

}
