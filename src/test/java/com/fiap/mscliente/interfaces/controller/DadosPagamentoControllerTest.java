package com.fiap.mscliente.interfaces.controller;

import com.fiap.mscliente.application.service.DadosPagamentoService;
import com.fiap.mscliente.domain.entity.DadosPagamento;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DadosPagamentoController.class)
class DadosPagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DadosPagamentoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveSalvarDadosPagamento() throws Exception {
        DadosPagamento pagamento = getPagamento();
        when(service.salvar(pagamento)).thenReturn(ResponseEntity.status(201).body(pagamento));

        mockMvc.perform(post("/pagamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pagamento)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numeroCartao").value("1234567890123456"));
    }

    @Test
    void deveBuscarPorClienteId() throws Exception {
        DadosPagamento pagamento = getPagamento();
        when(service.buscarPorClienteId(10L)).thenReturn(ResponseEntity.ok(pagamento));

        mockMvc.perform(get("/pagamentos/cliente/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeTitular").value("Lucas Godoy"));
    }

    @Test
    void deveAtualizarDadosPagamento() throws Exception {
        DadosPagamento pagamento = getPagamento();
        when(service.atualizar(1L, pagamento)).thenReturn(ResponseEntity.ok(pagamento));

        mockMvc.perform(put("/pagamentos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pagamento)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cvv").value("123"));
    }

    @Test
    void deveExcluirDadosPagamento() throws Exception {
        when(service.excluir(1L)).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(delete("/pagamentos/1"))
                .andExpect(status().isNoContent());
    }

    private DadosPagamento getPagamento() {
        return DadosPagamento.builder()
                .numeroCartao("1234567890123456")
                .nomeTitular("Lucas Godoy")
                .validade("12/29")
                .cvv("123")
                .build();
    }
}
