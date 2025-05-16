package com.fiap.ms_cliente_service.repository;

import com.fiap.ms_cliente_service.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
