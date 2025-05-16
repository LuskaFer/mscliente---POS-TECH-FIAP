package com.fiap.ms_cliente_service.repository;

import com.fiap.ms_cliente_service.domain.entity.DadosPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DadosPagamentoRepository extends JpaRepository<DadosPagamento, Long> {
    Optional<DadosPagamento> findByClienteId(Long clienteId);
    Optional<DadosPagamento> findByClienteEmail(String email);
}
