package com.fiap.mscliente.application.dto;

import com.fiap.mscliente.domain.entity.Endereco;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteDTO {

    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private EnderecoDTO endereco;
}
