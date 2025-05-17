package com.fiap.mscliente.application.dto;

import com.fiap.mscliente.domain.entity.Endereco;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ClienteDTO {

    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private Endereco endereco;
}
