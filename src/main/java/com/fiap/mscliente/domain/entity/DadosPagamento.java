package com.fiap.mscliente.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DadosPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;
    private String nomeTitular;
    private String validade;
    private String cvv;

    @Column(name = "cliente_id")
    private Long clienteId;
}
