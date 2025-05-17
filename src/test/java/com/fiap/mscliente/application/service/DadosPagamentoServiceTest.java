package com.fiap.mscliente.application.service;

import com.fiap.mscliente.domain.entity.DadosPagamento;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.fiap.mscliente.domain.repository.DadosPagamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class DadosPagamentoServiceTest {

    private DadosPagamentoService service;
    private DadosPagamentoRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(DadosPagamentoRepository.class);
        service = new DadosPagamentoService(repository);
    }

    @Test
    void deveSalvarDadosPagamento() {
        DadosPagamento dados = getDados();
        when(repository.save(dados)).thenReturn(dados);

        ResponseEntity<DadosPagamento> response = service.salvar(dados);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dados, response.getBody());
    }

    @Test
    void deveBuscarPorClienteId() {
        DadosPagamento dados = getDados();
        when(repository.findByClienteId(1L)).thenReturn(Optional.of(dados));

        ResponseEntity<DadosPagamento> response = service.buscarPorClienteId(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dados, response.getBody());
    }

    @Test
    void deveExcluir() {
        DadosPagamento dados = getDados();
        when(repository.findById(1L)).thenReturn(Optional.of(dados));

        ResponseEntity<Void> response = service.excluir(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deveAtualizar() {
        DadosPagamento original = getDados();
        DadosPagamento novo = getDados();
        novo.setNomeTitular("Novo Titular");

        when(repository.findById(1L)).thenReturn(Optional.of(original));
        when(repository.save(any())).thenReturn(novo);

        ResponseEntity<DadosPagamento> response = service.atualizar(1L, novo);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Novo Titular", response.getBody().getNomeTitular());
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
