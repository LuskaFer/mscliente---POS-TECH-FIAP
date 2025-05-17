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
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> excluir(Long id) {
        Optional<DadosPagamento> existente = repository.findById(id);
        if (existente.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<DadosPagamento> atualizar(Long id, DadosPagamento novo) {
        return repository.findById(id).map(dp -> {
            dp.setNomeTitular(novo.getNomeTitular());
            dp.setNumeroCartao(novo.getNumeroCartao());
            dp.setValidade(novo.getValidade());
            dp.setCvv(novo.getCvv());
            return ResponseEntity.ok(repository.save(dp));
        }).orElse(ResponseEntity.notFound().build());
    }
}
