package com.fiap.mscliente.interfaces.controller;

import com.fiap.mscliente.application.service.ClienteService;
import com.fiap.mscliente.domain.entity.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveSalvarCliente() throws Exception {
        Cliente cliente = getCliente();
        when(clienteService.salvar(cliente)).thenReturn(ResponseEntity.status(201).body(cliente));

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Lucas Godoy"));
    }

    @Test
    void deveBuscarClientePorId() throws Exception {
        Cliente cliente = getCliente();
        when(clienteService.buscarPorId(1L)).thenReturn(ResponseEntity.ok(cliente));

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value("12345678901"));
    }

    private Cliente getCliente() {
        return Cliente.builder()
                .id(1L)
                .nome("Lucas Godoy")
                .cpf("12345678901")
                .email("lucas@teste.com")
                .dataNascimento(LocalDate.of(2000, 1, 1))
                .build();
    }
}
