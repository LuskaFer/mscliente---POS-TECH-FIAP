package com.fiap.mscliente.usecases;

import com.fiap.mscliente.domain.DadosPagamento;
import com.fiap.mscliente.gateway.database.jpa.mapper.DadosPagamentoMapper;
import com.fiap.mscliente.gateway.database.jpa.repository.DadosPagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DadosPagamentoUseCaseImpl implements DadosPagamentoUseCase {

    private final DadosPagamentoRepository dadosPagamentoRepository;
    private final DadosPagamentoMapper mapper;

    @Override
    public DadosPagamento salvar(DadosPagamento dadosPagamento) {
        var entity = mapper.toEntity(dadosPagamento);
        var salvo = dadosPagamentoRepository.save(entity);
        return mapper.toDomain(salvo);
    }

    @Override
    public Optional<DadosPagamento> buscarPorClienteId(Long clienteId) {
        return dadosPagamentoRepository.findByClienteId(clienteId)
                .map(mapper::toDomain);
    }

    @Override
    public DadosPagamento atualizar(Long id, DadosPagamento dadosPagamento) {
        var existente = dadosPagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dados não encontrados"));

        existente.setNumeroCartao(dadosPagamento.getNumeroCartao());
        existente.setNomeTitular(dadosPagamento.getNomeTitular());
        existente.setValidade(dadosPagamento.getValidade());
        existente.setCvv(dadosPagamento.getCvv());

        var atualizado = dadosPagamentoRepository.save(existente);
        return mapper.toDomain(atualizado);
    }

    @Override
    public void excluir(Long id) {
        dadosPagamentoRepository.deleteById(id);
    }
}
