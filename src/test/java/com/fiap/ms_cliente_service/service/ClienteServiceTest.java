package com.fiap.ms_cliente_service.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import com.fiap.ms_cliente_service.domain.entity.Cliente;
import com.fiap.ms_cliente_service.repository.ClienteRepository;

class ClienteServiceTest {

    private ClienteService service;
    private ClienteRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ClienteRepository.class);
        service = new ClienteService(repository);
    }

    @Test
    void deveSalvarCliente() {
        Cliente cliente = Cliente.builder()
                .nome("Lucas")
                .cpf("123")
                .dataNascimento("2000-01-01")
                .rua("Rua A").numero("1").cep("00000-000")
                .build();

        when(repository.findByCpf("123")).thenReturn(Optional.empty());
        when(repository.save(cliente)).thenReturn(cliente);

        Cliente salvo = service.salvar(cliente);
        assertEquals("Lucas", salvo.getNome());
    }

    @Test
    void deveListarClientes() {
        when(repository.findAll()).thenReturn(List.of(new Cliente()));
        List<Cliente> lista = service.listarTodos();
        assertFalse(lista.isEmpty());
    }
}
