package com.fiap.mscliente.application.service;

import com.fiap.mscliente.application.dto.ClienteDTO;
import com.fiap.mscliente.domain.entity.Cliente;
import com.fiap.mscliente.domain.repository.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Cliente> salvar(Cliente cliente) {
        Optional<Cliente> existente = repository.findByCpf(cliente.getCpf());
        if (existente.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Cliente salvo = repository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<Cliente> buscarPorId(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        return ResponseEntity.ok(cliente);
    }

    public ResponseEntity<Cliente> buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Cliente> atualizar(Long id, Cliente novoCliente) {
        return repository.findById(id)
                .map(cliente -> {
                    cliente.setNome(novoCliente.getNome());
                    cliente.setCpf(novoCliente.getCpf());
                    cliente.setDataNascimento(novoCliente.getDataNascimento());
                    cliente.setEmail(novoCliente.getEmail());
                    return ResponseEntity.ok(repository.save(cliente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deletar(Long id) {
        return repository.findById(id)
                .map(cliente -> {
                    repository.delete(cliente);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
