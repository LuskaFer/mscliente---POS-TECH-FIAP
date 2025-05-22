package com.fiap.mscliente.application.service;

import com.fiap.mscliente.domain.entity.DadosPagamento;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.fiap.mscliente.domain.repository.DadosPagamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
        DadosPagamento dados = DadosPagamento.builder()
                .id(1L)
                .numeroCartao("1234123412341234")
                .nomeTitular("Titular")
                .validade("12/30")
                .cvv("123")
                .clienteId(1L)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(dados));

        ResponseEntity<Void> response = service.excluir(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(repository).delete(dados); // ✅ agora está certo!
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

    @Test
    void deveLancarExcecaoAoAtualizarInexistente() {
        DadosPagamento novo = getDados();
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.atualizar(99L, novo));
    }

    @Test
    void deveLancarExcecaoAoExcluirInexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.excluir(99L));
    }

    @Test
    void deveTratarBuscaPorClienteIdInexistente() {
        when(repository.findByClienteId(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.buscarPorClienteId(999L));
    }

}
