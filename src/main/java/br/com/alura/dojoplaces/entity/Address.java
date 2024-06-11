package br.com.alura.dojoplaces.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String code;

    private String neighborhood;

    private String city;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    public Address(String street, String code, String neighborhood, String city) {
        this.street = street;
        this.code = code;
        this.neighborhood  = neighborhood;
        this.city = city;
    }

    public Address() {}

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCode() {
        return code;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCreatedAtFormatedYear(){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return createdAt.format(pattern);
    }
}
