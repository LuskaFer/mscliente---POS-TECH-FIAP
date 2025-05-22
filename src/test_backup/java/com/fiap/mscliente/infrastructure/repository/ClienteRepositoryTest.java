package com.fiap.mscliente.infrastructure.repository;


import com.fiap.mscliente.domain.entity.Cliente;
import com.fiap.mscliente.domain.entity.Endereco;
import com.fiap.mscliente.domain.repository.ClienteRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void devePersistirClienteComEndereco() {
        Endereco endereco = Endereco.builder()
                .rua("Rua XPTO")
                .numero("123")
                .bairro("Centro")
                .cidade("São Paulo")
                .estado("SP")
                .cep("12345678")
                .build();

        Cliente cliente = Cliente.builder()
                .nome("Lucas")
                .cpf("12345678901")
                .email("lucas@fiap.com")
                .dataNascimento(LocalDate.of(2000, 1, 1))
                .endereco(endereco)
                .build();

        Cliente salvo = clienteRepository.save(cliente);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getEndereco().getRua()).isEqualTo("Rua XPTO");
    }
}
