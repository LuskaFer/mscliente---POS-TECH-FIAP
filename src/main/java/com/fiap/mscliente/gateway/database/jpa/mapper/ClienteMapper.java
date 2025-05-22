package com.fiap.mscliente.gateway.database.jpa.mapper;

import com.fiap.mscliente.domain.Cliente;
import com.fiap.mscliente.dto.ClienteDTO;
import com.fiap.mscliente.gateway.database.jpa.entity.ClienteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    private final EnderecoMapper enderecoMapper;

    public ClienteMapper(EnderecoMapper enderecoMapper) {
        this.enderecoMapper = enderecoMapper;
    }

    public ClienteEntity toEntity(Cliente domain) {
        if (domain == null) return null;
        return ClienteEntity.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .cpf(domain.getCpf())
                .email(domain.getEmail())
                .dataNascimento(domain.getDataNascimento())
                .endereco(enderecoMapper.toEntity(domain.getEndereco()))
                .build();
    }

    public Cliente toDomain(ClienteEntity entity) {
        if (entity == null) return null;
        return Cliente.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .cpf(entity.getCpf())
                .email(entity.getEmail())
                .dataNascimento(entity.getDataNascimento())
                .endereco(enderecoMapper.toDomain(entity.getEndereco()))
                .build();
    }

    public ClienteDTO toDto(Cliente domain) {
        if (domain == null) return null;
        return ClienteDTO.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .cpf(domain.getCpf())
                .email(domain.getEmail())
                .dataNascimento(domain.getDataNascimento())
                .endereco(enderecoMapper.toDto(domain.getEndereco()))
                .build();
    }

    public Cliente toDomain(ClienteDTO dto) {
        if (dto == null) return null;
        return Cliente.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .dataNascimento(dto.getDataNascimento())
                .endereco(enderecoMapper.toDomain(dto.getEndereco()))
                .build();
    }
}

