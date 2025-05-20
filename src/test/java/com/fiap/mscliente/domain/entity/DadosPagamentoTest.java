package com.fiap.mscliente.domain.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class DadosPagamentoTest {

    @Test
    public void deveCriarDadosPagamentoComTodosOsCampos() {
        DadosPagamento obj = DadosPagamento.builder()
                .id(1L)
                .numeroCartao("1234123412341234")
                .nomeTitular("Lucas")
                .validade("12/30")
                .cvv("123")
                .clienteId(1L)
                .build();
        assertNotNull(obj);
        assertEquals("Lucas", obj.getNomeTitular());
    }

    @Test
    public void deveSetarEAcessarCampos() {
        DadosPagamento obj = new DadosPagamento();
        obj.setId(2L);
        assertEquals(2L, obj.getId());
    }

    @Test
    public void deveTestarEqualsHashCodeToString() {
        DadosPagamento obj1 = new DadosPagamento();
        DadosPagamento obj2 = new DadosPagamento();
        assertEquals(obj1, obj2);
        assertEquals(obj1.hashCode(), obj2.hashCode());
        assertTrue(obj1.toString().contains("DadosPagamento"));
    }
}