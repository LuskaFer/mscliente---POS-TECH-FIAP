package com.fiap.mscliente.gateway.database.jpa;

import com.fiap.mscliente.domain.DadosPagamento;
import com.fiap.mscliente.gateway.database.jpa.entity.DadosPagamentoEntity;
import com.fiap.mscliente.gateway.database.jpa.interfaces.DadosPagamentoGateway;
import com.fiap.mscliente.gateway.database.jpa.mapper.DadosPagamentoMapper;
import com.fiap.mscliente.gateway.database.jpa.repository.DadosPagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DadosPagamentoGatewayImpl implements DadosPagamentoGateway {

    private final DadosPagamentoRepository repository;
    private final DadosPagamentoMapper mapper;

    @Override
    public DadosPagamento salvar(DadosPagamento dadosPagamento) {
        DadosPagamentoEntity entity = mapper.toEntity(dadosPagamento);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<DadosPagamento> buscarPorClienteId(Long clienteId) {
        return repository.findByClienteId(clienteId)
                .map(mapper::toDomain);
    }

    @Override
    public DadosPagamento atualizar(Long id, DadosPagamento dadosPagamento) {
        DadosPagamentoEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dados de pagamento não encontrado"));
        entity.setNumeroCartao(dadosPagamento.getNumeroCartao());
        entity.setNomeTitular(dadosPagamento.getNomeTitular());
        entity.setValidade(dadosPagamento.getValidade());
        entity.setCvv(dadosPagamento.getCvv());
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public void excluir(Long id) {
        DadosPagamentoEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dados de pagamento não encontrado"));
        repository.delete(entity);
    }
}
