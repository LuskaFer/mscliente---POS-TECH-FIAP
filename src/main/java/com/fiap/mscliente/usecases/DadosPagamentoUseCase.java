package com.fiap.mscliente.usecases;

import com.fiap.mscliente.domain.DadosPagamento;

import java.util.Optional;

public interface DadosPagamentoUseCase {

    DadosPagamento salvar(DadosPagamento dadosPagamento);

    Optional<DadosPagamento> buscarPorClienteId(Long clienteId);

    DadosPagamento atualizar(Long id, DadosPagamento dadosPagamento);

    void excluir(Long id);
}
