package com.fiap.mscliente.interfaces.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.ResponseEntity;
import com.fiap.mscliente.application.service.DadosPagamentoService;
import com.fiap.mscliente.domain.entity.DadosPagamento;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

@ActiveProfiles("test")
@WebMvcTest(DadosPagamentoController.class)
class DadosPagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DadosPagamentoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveSalvarPagamento() throws Exception {
        DadosPagamento dados = getDados();
        when(service.salvar(Mockito.any(DadosPagamento.class))).thenReturn(ResponseEntity.ok(dados));

        mockMvc.perform(post("/pagamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dados)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCartao").value("1234123412341234"));
    }

    @Test
    void deveBuscarPorClienteId() throws Exception {
        DadosPagamento dados = getDados();
        when(service.buscarPorClienteId(1L)).thenReturn(ResponseEntity.of(Optional.of(dados)));

        mockMvc.perform(get("/pagamentos/cliente/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeTitular").value("Titular"));
    }

    private DadosPagamento getDados() {
        return DadosPagamento.builder()
                .id(1L)
                .nomeTitular("Titular")
                .numeroCartao("1234123412341234")
                .validade("12/30")
                .cvv("123")
                .clienteId(1L)
                .build();
    }
}
