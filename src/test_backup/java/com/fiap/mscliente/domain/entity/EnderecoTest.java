package com.fiap.mscliente.domain.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class EnderecoTest {

    @Test
    public void deveCriarEnderecoComTodosOsCampos() {
        Endereco obj = Endereco.builder()
                .id(1L)
                .rua("Rua A")
                .numero("100")
                .bairro("Centro")
                .cidade("SP")
                .estado("SP")
                .cep("00000000")
                .complemento("Apto 1")
                .cliente(null)
                .build();
        assertNotNull(obj);
        assertEquals("Rua A", obj.getRua());
    }

    @Test
    public void deveSetarEAcessarCampos() {
        Endereco obj = new Endereco();
        obj.setId(2L);
        assertEquals(2L, obj.getId());
    }

    @Test
    public void deveTestarEqualsHashCodeToString() {
        Endereco obj1 = new Endereco();
        Endereco obj2 = new Endereco();
        assertEquals(obj1, obj2);
        assertEquals(obj1.hashCode(), obj2.hashCode());
        assertTrue(obj1.toString().contains("Endereco"));
    }
}