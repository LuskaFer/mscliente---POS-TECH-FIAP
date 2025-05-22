package com.fiap.mscliente.gateway.database.jpa;

import com.fiap.mscliente.gateway.database.jpa.entity.ClienteEntity;
import com.fiap.mscliente.gateway.database.jpa.interfaces.ClienteGateway;
import com.fiap.mscliente.gateway.database.jpa.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteGatewayImpl implements ClienteGateway {

    private final ClienteRepository clienteRepository;

    @Override
    public ClienteEntity salvar(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<ClienteEntity> listar() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<ClienteEntity> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Optional<ClienteEntity> buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Override
    public ClienteEntity atualizar(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
