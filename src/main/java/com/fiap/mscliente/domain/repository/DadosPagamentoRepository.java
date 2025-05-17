package com.fiap.mscliente.domain.repository;

import com.fiap.mscliente.domain.entity.DadosPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DadosPagamentoRepository extends JpaRepository<DadosPagamento, Long> {

    Optional<DadosPagamento> findByClienteId(Long clienteId);
}
