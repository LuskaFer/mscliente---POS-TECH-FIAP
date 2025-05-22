package com.fiap.mscliente.controller;

import com.fiap.mscliente.usecases.ClienteUseCase;
import com.fiap.mscliente.domain.Cliente;
import com.fiap.mscliente.dto.ClienteDTO;
import com.fiap.mscliente.gateway.database.jpa.mapper.ClienteMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteUseCase clienteUseCase;
    private final ClienteMapper clienteMapper;

    @PostMapping
    public ResponseEntity<ClienteDTO> salvar(@RequestBody ClienteDTO dto) {
        Cliente cliente = clienteMapper.toDomain(dto);
        Cliente salvo = clienteUseCase.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteMapper.toDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        List<Cliente> clientes = clienteUseCase.listar();
        return ResponseEntity.ok(clientes.stream().map(clienteMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        Cliente cliente = clienteUseCase.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        return ResponseEntity.ok(clienteMapper.toDto(cliente));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteDTO> buscarPorCpf(@PathVariable String cpf) {
        Cliente cliente = clienteUseCase.buscarPorCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        return ResponseEntity.ok(clienteMapper.toDto(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        Cliente atualizado = clienteMapper.toDomain(dto);
        Cliente cliente = clienteUseCase.atualizar(id, atualizado);
        return ResponseEntity.ok(clienteMapper.toDto(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteUseCase.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
