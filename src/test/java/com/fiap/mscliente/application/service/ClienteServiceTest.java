package com.fiap.mscliente.application.service;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.fiap.mscliente.domain.entity.Cliente;
import com.fiap.mscliente.domain.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class ClienteServiceTest {

    private ClienteService service;
    private ClienteRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(ClienteRepository.class);
        service = new ClienteService(repository);
    }

    @Test
    void deveSalvarCliente() {
        Cliente cliente = getCliente();
        when(repository.findByCpf(cliente.getCpf())).thenReturn(Optional.empty());
        when(repository.save(cliente)).thenReturn(cliente);

        ResponseEntity<Cliente> response = service.salvar(cliente);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void naoDeveSalvarClienteComCpfDuplicado() {
        Cliente cliente = getCliente();
        when(repository.findByCpf(cliente.getCpf())).thenReturn(Optional.of(cliente));

        ResponseEntity<Cliente> response = service.salvar(cliente);

        assertEquals(409, response.getStatusCodeValue());
    }

    @Test
    void deveBuscarClientePorId() {
        Cliente cliente = getCliente();
        when(repository.findById(1L)).thenReturn(Optional.of(cliente));

        ResponseEntity<Cliente> response = service.buscarPorId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void deveRetornarNotFoundAoBuscarIdInexistente() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Cliente> response = service.buscarPorId(2L);

        assertEquals(404, response.getStatusCodeValue());
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
