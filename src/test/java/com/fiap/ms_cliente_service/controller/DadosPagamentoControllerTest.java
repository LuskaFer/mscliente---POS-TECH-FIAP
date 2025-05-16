package com.fiap.ms_cliente_service.controller;

import com.fiap.ms_cliente_service.domain.entity.DadosPagamento;
import com.fiap.ms_cliente_service.service.DadosPagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DadosPagamentoControllerTest {

    private DadosPagamentoService service;
    private DadosPagamentoController controller;

    @BeforeEach
    void setUp() {
        service = mock(DadosPagamentoService.class);
        controller = new DadosPagamentoController(service);
    }

    @Test
    void deveSalvarPagamento() {
        DadosPagamento dados = new DadosPagamento();
        when(service.salvar(eq(1L), any())).thenReturn(dados);

        ResponseEntity<DadosPagamento> response = controller.cadastrar(1L, dados);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dados, response.getBody());
    }

    @Test
    void deveBuscarPagamentoPorCliente() {
        DadosPagamento dados = new DadosPagamento();
        when(service.buscarPorClienteId(1L)).thenReturn(dados);

        ResponseEntity<DadosPagamento> response = controller.buscar(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dados, response.getBody());
    }

    @Test
    void deveAtualizarPagamento() {
        DadosPagamento dados = new DadosPagamento();
        when(service.atualizar(eq(1L), any())).thenReturn(dados);

        ResponseEntity<DadosPagamento> response = controller.atualizar(1L, dados);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dados, response.getBody());
    }

    @Test
    void deveDeletarPagamento() {
        ResponseEntity<Void> response = controller.deletar(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(service).deletarPorClienteId(1L);
    }
}
