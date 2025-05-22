package com.fiap.mscliente.gateway.database.jpa.repository;

import com.fiap.mscliente.gateway.database.jpa.entity.DadosPagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DadosPagamentoRepository extends JpaRepository<DadosPagamentoEntity, Long> {
    Optional<DadosPagamentoEntity> findByClienteId(Long clienteId);
}
