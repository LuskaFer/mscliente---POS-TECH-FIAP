package com.fiap.mscliente.application.service;

import com.fiap.mscliente.domain.entity.Cliente;
import com.fiap.mscliente.domain.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        Long id = 1L;
        Cliente cliente = getCliente();
        when(repository.findById(id)).thenReturn(Optional.of(cliente));

        ResponseEntity<Cliente> response = service.buscarPorId(id);

        assertNotNull(response.getBody());
        assertEquals("Lucas Godoy", response.getBody().getNome());
        verify(repository).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        Long id = 999L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.buscarPorId(id));
        verify(repository).findById(id);
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

    @Test
    void deveTratarClienteComCamposNulos() {
        Cliente cliente = new Cliente(); // Tudo null
        when(repository.findByCpf(null)).thenReturn(Optional.empty());
        when(repository.save(cliente)).thenReturn(cliente);

        ResponseEntity<Cliente> response = service.salvar(cliente);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}
