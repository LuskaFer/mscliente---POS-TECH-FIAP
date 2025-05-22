package com.fiap.mscliente.gateway.database.jpa.interfaces;

import com.fiap.mscliente.domain.DadosPagamento;

import java.util.Optional;

public interface DadosPagamentoGateway {

    DadosPagamento salvar(DadosPagamento dadosPagamento);

    Optional<DadosPagamento> buscarPorClienteId(Long clienteId);

    DadosPagamento atualizar(Long id, DadosPagamento dadosPagamento);

    void excluir(Long id);
}
