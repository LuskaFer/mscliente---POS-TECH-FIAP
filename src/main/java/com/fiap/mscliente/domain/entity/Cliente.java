package com.fiap.mscliente.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    private String email;

    private LocalDate dataNascimento;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    @Builder.Default
    private Endereco endereco = null;
}
