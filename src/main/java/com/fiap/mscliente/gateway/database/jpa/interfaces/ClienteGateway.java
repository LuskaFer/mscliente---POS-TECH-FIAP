package com.fiap.mscliente.gateway.database.jpa.interfaces;

import com.fiap.mscliente.gateway.database.jpa.entity.ClienteEntity;

import java.util.List;
import java.util.Optional;

public interface ClienteGateway {
    ClienteEntity salvar(ClienteEntity cliente);
    List<ClienteEntity> listar();
    Optional<ClienteEntity> buscarPorId(Long id);
    Optional<ClienteEntity> buscarPorCpf(String cpf);
    ClienteEntity atualizar(ClienteEntity cliente);
    void deletar(Long id);
}
