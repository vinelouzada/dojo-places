package br.com.alura.dojoplaces.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address("A","B","C","D");
    }

    @Test
    public void should__return_0_dias_atras() {
        address.setUpdatedAt(LocalDateTime.now());
        assertEquals(Optional.of("0 dias atrás"), address.getUpdatedAtAsDaysAgo());
    }

    @Test
    public void should__return_1_dia_atras() {
        address.setUpdatedAt(LocalDateTime.now().minusDays(1));
        assertEquals(Optional.of("1 dia atrás"), address.getUpdatedAtAsDaysAgo());
    }

    @Test
    public void should__return_2_dias_atras() {
        address.setUpdatedAt(LocalDateTime.now().minusDays(2));
        assertEquals(Optional.of("2 dias atrás"), address.getUpdatedAtAsDaysAgo());
    }

    @Test
    public void should__return_null() {
        assertEquals(Optional.empty(),address.getUpdatedAtAsDaysAgo());
    }

}