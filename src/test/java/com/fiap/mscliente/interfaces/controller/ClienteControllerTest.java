package com.fiap.mscliente.interfaces.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.fiap.mscliente.application.service.ClienteService;
import com.fiap.mscliente.domain.entity.Cliente;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveListarClientes() throws Exception {
        Cliente cliente = getCliente();
        when(service.listar()).thenReturn(org.springframework.http.ResponseEntity.ok(List.of(cliente)));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Lucas Godoy"));
    }

    @Test
    void deveSalvarCliente() throws Exception {
        Cliente cliente = getCliente();
        when(service.salvar(Mockito.any(Cliente.class))).thenReturn(org.springframework.http.ResponseEntity.status(201).body(cliente));

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cpf").value("12345678901"));
    }

    private Cliente getCliente() {
        return Cliente.builder()
                .id(1L)
                .nome("Lucas Godoy")
                .cpf("12345678901")
                .email("lucas@fiap.com")
                .dataNascimento(LocalDate.of(2000, 1, 1))
                .build();
    }
}
