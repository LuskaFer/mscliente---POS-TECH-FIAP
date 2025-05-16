package com.fiap.ms_cliente_service.service;

import com.fiap.ms_cliente_service.domain.entity.Cliente;
import com.fiap.ms_cliente_service.domain.entity.DadosPagamento;
import com.fiap.ms_cliente_service.repository.ClienteRepository;
import com.fiap.ms_cliente_service.repository.DadosPagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DadosPagamentoService {

    private final DadosPagamentoRepository repository;
    private final ClienteRepository clienteRepository;

    public DadosPagamento salvar(Long clienteId, DadosPagamento dados) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        dados.setCliente(cliente);
        return repository.save(dados);
    }

    public DadosPagamento buscarPorClienteId(Long clienteId) {
        return repository.findByClienteId(clienteId)
                .orElseThrow(() -> new RuntimeException("Dados de pagamento não encontrados"));
    }


    public DadosPagamento atualizar(Long clienteId, DadosPagamento novo) {
        DadosPagamento atual = buscarPorClienteId(clienteId);
        atual.setNomeTitular(novo.getNomeTitular());
        atual.setNumeroCartao(novo.getNumeroCartao());
        atual.setValidade(novo.getValidade());
        atual.setCvv(novo.getCvv());
        return repository.save(atual);
    }

    public void deletarPorClienteId(Long clienteId) {
        DadosPagamento dados = buscarPorClienteId(clienteId);
        repository.delete(dados);
    }


}
