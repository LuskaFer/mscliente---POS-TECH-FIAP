package com.fiap.mscliente.usecases;

import com.fiap.mscliente.domain.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteUseCase {
    Cliente salvar(Cliente cliente);

    List<Cliente> listar();

    Optional<Cliente> buscarPorId(Long id);

    Optional<Cliente> buscarPorCpf(String cpf);

    Cliente atualizar(Long id, Cliente cliente);

    void deletar(Long id);
}
