package com.fiap.mscliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadosPagamentoDTO {
    private Long id;
    private String numeroCartao;
    private String nomeTitular;
    private String validade;
    private String cvv;
    private Long clienteId;
}
