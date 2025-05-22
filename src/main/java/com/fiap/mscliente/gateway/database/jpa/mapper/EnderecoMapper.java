package com.fiap.mscliente.gateway.database.jpa.mapper;

import com.fiap.mscliente.domain.Endereco;
import com.fiap.mscliente.dto.EnderecoDTO;
import com.fiap.mscliente.gateway.database.jpa.entity.EnderecoEntity;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public EnderecoEntity toEntity(Endereco domain) {
        if (domain == null) return null;
        return EnderecoEntity.builder()
                .id(domain.getId())
                .rua(domain.getRua())
                .numero(domain.getNumero())
                .bairro(domain.getBairro())
                .cidade(domain.getCidade())
                .estado(domain.getEstado())
                .cep(domain.getCep())
                .complemento(domain.getComplemento())
                .build();
    }

    public Endereco toDomain(EnderecoEntity entity) {
        if (entity == null) return null;
        return Endereco.builder()
                .id(entity.getId())
                .rua(entity.getRua())
                .numero(entity.getNumero())
                .bairro(entity.getBairro())
                .cidade(entity.getCidade())
                .estado(entity.getEstado())
                .cep(entity.getCep())
                .complemento(entity.getComplemento())
                .build();
    }

    public EnderecoDTO toDto(Endereco domain) {
        if (domain == null) return null;
        return EnderecoDTO.builder()
                .id(domain.getId())
                .rua(domain.getRua())
                .numero(domain.getNumero())
                .bairro(domain.getBairro())
                .cidade(domain.getCidade())
                .estado(domain.getEstado())
                .cep(domain.getCep())
                .complemento(domain.getComplemento())
                .build();
    }

    public Endereco toDomain(EnderecoDTO dto) {
        if (dto == null) return null;
        return Endereco.builder()
                .id(dto.getId())
                .rua(dto.getRua())
                .numero(dto.getNumero())
                .bairro(dto.getBairro())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .cep(dto.getCep())
                .complemento(dto.getComplemento())
                .build();
    }
}
