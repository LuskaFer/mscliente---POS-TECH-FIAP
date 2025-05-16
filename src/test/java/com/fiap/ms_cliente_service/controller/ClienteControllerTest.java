package com.fiap.ms_cliente_service.controller;

import java.util.Collections;
import com.fiap.ms_cliente_service.domain.entity.Cliente;
import com.fiap.ms_cliente_service.controller.ClienteController;
import com.fiap.ms_cliente_service.domain.entity.Endereco;
import com.fiap.ms_cliente_service.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    private ClienteService service;
    private ClienteController controller;

    @BeforeEach
    void setUp() {
        service = mock(ClienteService.class);
        controller = new ClienteController(service);
    }

    @Test
    void deveSalvarCliente() {
        Cliente cliente = new Cliente();
        when(service.salvar(any())).thenReturn(cliente);

        ResponseEntity<Cliente> response = controller.criar(cliente);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void deveListarClientes() {
        when(service.listarTodos()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Cliente>> response = controller.listarTodos();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void deveBuscarPorId() {
        Cliente cliente = new Cliente();
        when(service.buscarPorId(1L)).thenReturn(cliente);

        ResponseEntity<Cliente> response = controller.buscarPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void deveBuscarPorCpf() {
        Cliente cliente = new Cliente();
        when(service.buscarPorCpf("123")).thenReturn(cliente);

        ResponseEntity<Cliente> response = controller.buscarPorCpf("123");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void deveAtualizarCliente() {
        Cliente cliente = new Cliente();
        when(service.atualizar(eq(1L), any())).thenReturn(cliente);

        ResponseEntity<Cliente> response = controller.atualizar(1L, cliente);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void deveDeletarCliente() {
        ResponseEntity<Void> response = controller.deletar(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(service).deletar(1L);
    }
}
