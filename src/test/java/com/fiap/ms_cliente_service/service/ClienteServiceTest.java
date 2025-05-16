package com.fiap.ms_cliente_service.service;

import com.fiap.ms_cliente_service.domain.entity.Cliente;
import com.fiap.ms_cliente_service.domain.entity.Endereco;
import com.fiap.ms_cliente_service.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    private ClienteService service;
    private ClienteRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ClienteRepository.class);
        service = new ClienteService(repository);
    }

    @Test
    void deveSalvarClienteComEndereco() {
        Cliente cliente = Cliente.builder()
                .nome("Lucas Teste")
                .cpf("98765432100")
                .email("lucas@fiap.com")
                .dataNascimento("2000-01-01")
                .endereco(Endereco.builder()
                        .rua("Rua Teste")
                        .numero("123")
                        .bairro("Bairro Teste")
                        .cidade("Cidade")
                        .estado("SP")
                        .cep("00000-000")
                        .complemento("Apto")
                        .build())
                .build();

        when(repository.findByCpf("98765432100")).thenReturn(Optional.empty());
        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente salvo = service.salvar(cliente);

        assertNotNull(salvo);
        assertEquals("Lucas Teste", salvo.getNome());
        verify(repository).save(any(Cliente.class));
    }

    @Test
    void deveBuscarClientePorCpf() {
        Cliente cliente = Cliente.builder()
                .nome("Lucas")
                .cpf("12345678900")
                .email("lucas@email.com")
                .build();

        when(repository.findByCpf("12345678900")).thenReturn(Optional.of(cliente));

        Cliente resultado = service.buscarPorCpf("12345678900");

        assertNotNull(resultado);
        assertEquals("Lucas", resultado.getNome());
        verify(repository).findByCpf("12345678900");
    }

    @Test
    void deveAtualizarClienteComEndereco() {
        Long clienteId = 1L;

        Cliente existente = Cliente.builder()
                .id(clienteId)
                .nome("Antigo")
                .cpf("11122233344")
                .email("antigo@email.com")
                .dataNascimento("1990-01-01")
                .endereco(Endereco.builder()
                        .id(1L)
                        .rua("Rua Velha")
                        .build())
                .build();

        Cliente atualizado = Cliente.builder()
                .nome("Novo Nome")
                .cpf("11122233344")
                .email("novo@email.com")
                .dataNascimento("1990-01-01")
                .endereco(Endereco.builder()
                        .rua("Rua Nova")
                        .numero("123")
                        .bairro("Novo Bairro")
                        .cidade("Nova Cidade")
                        .estado("SP")
                        .cep("00000-000")
                        .complemento("Casa")
                        .build())
                .build();

        when(repository.findById(clienteId)).thenReturn(Optional.of(existente));
        when(repository.save(any(Cliente.class))).thenReturn(atualizado);

        Cliente result = service.atualizar(clienteId, atualizado);

        assertEquals("Novo Nome", result.getNome());
        assertEquals("Rua Nova", result.getEndereco().getRua());
        verify(repository).save(any(Cliente.class));
    }


}
