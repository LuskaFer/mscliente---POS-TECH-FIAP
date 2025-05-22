package com.fiap.mscliente.gateway.database.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dados_pagamento")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadosPagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;
    private String nomeTitular;
    private String validade;
    private String cvv;

    @Column(nullable = false)
    private Long clienteId;
}
