package com.fiap.mscliente.application.service;

import com.fiap.mscliente.domain.entity.DadosPagamento;
import com.fiap.mscliente.domain.repository.DadosPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DadosPagamentoService {

    private final DadosPagamentoRepository repository;

    @Autowired
    public DadosPagamentoService(DadosPagamentoRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<DadosPagamento> salvar(DadosPagamento dadosPagamento) {
        return ResponseEntity.ok(repository.save(dadosPagamento));
    }

    public ResponseEntity<DadosPagamento> buscarPorClienteId(Long clienteId) {
        return repository.findByClienteId(clienteId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public ResponseEntity<Void> excluir(Long id) {
        return repository.findById(id)
                .map(existing -> {
                    repository.delete(existing);
                    return ResponseEntity.noContent().<Void>build(); // este <Void> resolve o erro de tipo
                })
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    public ResponseEntity<DadosPagamento> atualizar(Long id, DadosPagamento dados) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setNumeroCartao(dados.getNumeroCartao());
                    existing.setNomeTitular(dados.getNomeTitular());
                    existing.setValidade(dados.getValidade());
                    existing.setCvv(dados.getCvv());
                    DadosPagamento atualizado = repository.save(existing);
                    return ResponseEntity.ok(atualizado);
                })
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

}
