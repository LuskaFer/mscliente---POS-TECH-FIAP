package com.fiap.mscliente.usecases;

import com.fiap.mscliente.domain.Cliente;
import com.fiap.mscliente.gateway.database.jpa.mapper.ClienteMapper;
import com.fiap.mscliente.gateway.database.jpa.mapper.EnderecoMapper;
import com.fiap.mscliente.gateway.database.jpa.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteUseCaseImpl implements ClienteUseCase {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final EnderecoMapper enderecoMapper;

    @Override
    public Cliente salvar(Cliente cliente) {
        var entity = clienteMapper.toEntity(cliente);
        var salvo = clienteRepository.save(entity);
        return clienteMapper.toDomain(salvo);
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toDomain);
    }

    @Override
    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .map(clienteMapper::toDomain);
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
        var existente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        existente.setNome(cliente.getNome());
        existente.setCpf(cliente.getCpf());
        existente.setEmail(cliente.getEmail());
        existente.setDataNascimento(cliente.getDataNascimento());
        existente.setEndereco(enderecoMapper.toEntity(cliente.getEndereco()));

        var atualizado = clienteRepository.save(existente);
        return clienteMapper.toDomain(atualizado);
    }

    @Override
    public void deletar(Long id) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }
}
