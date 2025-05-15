package com.fiap.ms_cliente_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fiap.ms_cliente_service.domain.entity.Cliente;
import com.fiap.ms_cliente_service.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente salvar(Cliente cliente) {
        repository.findByCpf(cliente.getCpf()).ifPresent(c -> {
            throw new RuntimeException("CPF já cadastrado");
        });
        return repository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public Cliente atualizar(Long id, Cliente atualizado) {
        Cliente cliente = buscarPorId(id);

        cliente.setNome(atualizado.getNome());
        cliente.setCpf(atualizado.getCpf());
        cliente.setDataNascimento(atualizado.getDataNascimento());
        cliente.setRua(atualizado.getRua());
        cliente.setNumero(atualizado.getNumero());
        cliente.setCep(atualizado.getCep());

        return repository.save(cliente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
