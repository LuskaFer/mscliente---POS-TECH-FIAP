package com.fiap.mscliente.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DadosPagamentoTest {

    @Test
    void deveCriarDadosPagamentoComValoresCorretos() {
        DadosPagamento pagamento = DadosPagamento.builder()
                .numeroCartao("1234-5678-9012-3456")
                .nomeTitular("Lucas Fernandes")
                .validade("12/26")
                .cvv("123")
                .build();

        assertEquals("1234-5678-9012-3456", pagamento.getNumeroCartao());
        assertEquals("Lucas Fernandes", pagamento.getNomeTitular());
        assertEquals("12/26", pagamento.getValidade());
        assertEquals("123", pagamento.getCvv());
    }

}
