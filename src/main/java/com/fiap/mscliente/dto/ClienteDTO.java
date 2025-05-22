package com.fiap.mscliente.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private EnderecoDTO endereco;
}
