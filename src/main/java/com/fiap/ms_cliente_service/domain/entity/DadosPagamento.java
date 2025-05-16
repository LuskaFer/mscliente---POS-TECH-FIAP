package com.fiap.ms_cliente_service.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeTitular;
    private String numeroCartao;
    private String validade;
    private String cvv;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
