package com.fiap.mscliente.domain.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class ClienteTest {

    @Test
    public void deveCriarClienteComTodosOsCampos() {
        Cliente obj = Cliente.builder()
                .id(1L)
                .nome("Lucas")
                .cpf("12345678900")
                .email("lucas@email.com")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .endereco(null)
                .build();
        assertNotNull(obj);
        assertEquals("Lucas", obj.getNome());
    }

    @Test
    public void deveSetarEAcessarCampos() {
        Cliente obj = new Cliente();
        obj.setId(2L);
        assertEquals(2L, obj.getId());
    }

    @Test
    public void deveTestarEqualsHashCodeToString() {
        Cliente obj1 = new Cliente();
        Cliente obj2 = new Cliente();
        assertEquals(obj1, obj2);
        assertEquals(obj1.hashCode(), obj2.hashCode());
        assertTrue(obj1.toString().contains("Cliente"));
    }
}