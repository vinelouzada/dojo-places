package br.com.alura.dojoplaces.entity;

import br.com.alura.dojoplaces.form.UpdateForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Duration;
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

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void updateModel(UpdateForm updateForm) {
        setCity(updateForm.getCity());
        setNeighborhood(updateForm.getNeighborhood());
        setCode(updateForm.getCode());
        setStreet(updateForm.getStreet());
        setUpdatedAt();
    }

    public void setUpdatedAt(){
        this.updatedAt = LocalDateTime.now();
    }


    public String getUpdatedAtAsDaysAgo(){

        if (updatedAt == null){
            return null;
        }

        Duration daysAgo = Duration.between(updatedAt, LocalDateTime.now());

        if (daysAgo.toDays() == 1){
            return daysAgo.toDays() + " dia atrás";
        }

        return daysAgo.toDays() + " dias atrás";
    }

}
