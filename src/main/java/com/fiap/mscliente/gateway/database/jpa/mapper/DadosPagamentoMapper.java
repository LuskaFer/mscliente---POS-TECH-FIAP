package com.fiap.mscliente.gateway.database.jpa.mapper;

import com.fiap.mscliente.domain.DadosPagamento;
import com.fiap.mscliente.dto.DadosPagamentoDTO;
import com.fiap.mscliente.gateway.database.jpa.entity.DadosPagamentoEntity;
import org.springframework.stereotype.Component;

@Component
public class DadosPagamentoMapper {

    public DadosPagamentoEntity toEntity(DadosPagamento domain) {
        if (domain == null) {
            return null;
        }
        return DadosPagamentoEntity.builder()
                .id(domain.getId())
                .nomeTitular(domain.getNomeTitular())
                .numeroCartao(domain.getNumeroCartao())
                .validade(domain.getValidade())
                .cvv(domain.getCvv())
                .clienteId(domain.getClienteId())
                .build();
    }

    public DadosPagamento toDomain(DadosPagamentoEntity entity) {
        if (entity == null) {
            return null;
        }
        return DadosPagamento.builder()
                .id(entity.getId())
                .nomeTitular(entity.getNomeTitular())
                .numeroCartao(entity.getNumeroCartao())
                .validade(entity.getValidade())
                .cvv(entity.getCvv())
                .clienteId(entity.getClienteId())
                .build();
    }

    public DadosPagamentoDTO toDto(DadosPagamento domain) {
        if (domain == null) {
            return null;
        }
        return DadosPagamentoDTO.builder()
                .id(domain.getId())
                .nomeTitular(domain.getNomeTitular())
                .numeroCartao(domain.getNumeroCartao())
                .validade(domain.getValidade())
                .cvv(domain.getCvv())
                .clienteId(domain.getClienteId())
                .build();
    }

    public DadosPagamento toDomain(DadosPagamentoDTO dto) {
        if (dto == null) {
            return null;
        }
        return DadosPagamento.builder()
                .id(dto.getId())
                .nomeTitular(dto.getNomeTitular())
                .numeroCartao(dto.getNumeroCartao())
                .validade(dto.getValidade())
                .cvv(dto.getCvv())
                .clienteId(dto.getClienteId())
                .build();
    }
}
