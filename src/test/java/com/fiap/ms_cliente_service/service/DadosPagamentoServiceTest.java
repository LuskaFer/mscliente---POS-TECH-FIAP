package com.fiap.ms_cliente_service.service;

import com.fiap.ms_cliente_service.domain.entity.Cliente;
import com.fiap.ms_cliente_service.domain.entity.DadosPagamento;
import com.fiap.ms_cliente_service.repository.ClienteRepository;
import com.fiap.ms_cliente_service.repository.DadosPagamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DadosPagamentoServiceTest {

    private DadosPagamentoService service;
    private DadosPagamentoRepository pagamentoRepository;
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        pagamentoRepository = mock(DadosPagamentoRepository.class);
        clienteRepository = mock(ClienteRepository.class);
        service = new DadosPagamentoService(pagamentoRepository, clienteRepository);
    }

    @Test
    void deveSalvarDadosPagamento() {
        Cliente cliente = Cliente.builder().id(1L).build();
        DadosPagamento pagamento = DadosPagamento.builder().nomeTitular("Lucas").build();

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(pagamentoRepository.save(any())).thenReturn(pagamento);

        DadosPagamento salvo = service.salvar(1L, pagamento);

        assertEquals("Lucas", salvo.getNomeTitular());
        verify(pagamentoRepository).save(pagamento);
    }

    @Test
    void deveBuscarDadosPagamentoPorClienteId() {
        DadosPagamento pagamento = DadosPagamento.builder().id(1L).nomeTitular("Lucas").build();
        when(pagamentoRepository.findByClienteId(1L)).thenReturn(Optional.of(pagamento));

        DadosPagamento resultado = service.buscarPorClienteId(1L);

        assertNotNull(resultado);
        assertEquals("Lucas", resultado.getNomeTitular());
        verify(pagamentoRepository).findByClienteId(1L);
    }

    @Test
    void deveAtualizarDadosPagamento() {
        DadosPagamento existente = DadosPagamento.builder()
                .id(1L)
                .nomeTitular("Antigo")
                .numeroCartao("1111")
                .validade("01/25")
                .cvv("123")
                .build();

        DadosPagamento novo = DadosPagamento.builder()
                .nomeTitular("Novo")
                .numeroCartao("2222")
                .validade("02/26")
                .cvv("321")
                .build();

        when(pagamentoRepository.findByClienteId(1L)).thenReturn(Optional.of(existente));
        when(pagamentoRepository.save(any())).thenReturn(novo);

        DadosPagamento atualizado = service.atualizar(1L, novo);

        assertEquals("Novo", atualizado.getNomeTitular());
        verify(pagamentoRepository).save(existente);
    }

    @Test
    void deveDeletarDadosPagamentoPorClienteId() {
        DadosPagamento pagamento = DadosPagamento.builder().id(1L).nomeTitular("Lucas").build();
        when(pagamentoRepository.findByClienteId(1L)).thenReturn(Optional.of(pagamento));

        service.deletarPorClienteId(1L);

        verify(pagamentoRepository).delete(pagamento);
    }
}
