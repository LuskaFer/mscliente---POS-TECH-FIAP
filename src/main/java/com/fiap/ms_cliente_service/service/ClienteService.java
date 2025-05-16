package com.fiap.ms_cliente_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.fiap.ms_cliente_service.domain.entity.Endereco;
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

        // Vínculo bidirecional
        if (cliente.getEndereco() != null) {
            cliente.getEndereco().setCliente(cliente);
        }

        return repository.save(cliente);
    }


    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public Cliente buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com CPF: " + cpf));
    }


    public Cliente atualizar(Long id, Cliente atualizado) {
        Cliente cliente = buscarPorId(id);

        cliente.setNome(atualizado.getNome());
        cliente.setCpf(atualizado.getCpf());
        cliente.setDataNascimento(atualizado.getDataNascimento());

        if (atualizado.getEndereco() != null) {
            Endereco novoEndereco = atualizado.getEndereco();
            novoEndereco.setId(cliente.getEndereco() != null ? cliente.getEndereco().getId() : null);
            novoEndereco.setCliente(cliente);
            cliente.setEndereco(novoEndereco);
        }

        return repository.save(cliente);
    }


    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
