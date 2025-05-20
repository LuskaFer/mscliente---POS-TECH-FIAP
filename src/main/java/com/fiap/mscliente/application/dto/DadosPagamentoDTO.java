package com.fiap.mscliente.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DadosPagamentoDTO {
    private String numeroCartao;
    private String nomeTitular;
    private String validade;
    private String cvv;
}
