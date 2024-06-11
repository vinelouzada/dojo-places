package br.com.alura.dojoplaces.form;

import br.com.alura.dojoplaces.entity.Address;

public record AddressData(Long id,
                          String street,
                          String code,
                          String neighborhood,
                          String city,
                          String createdAt) {

    public AddressData(Address address){
        this(address.getId(),
                address.getStreet(),
                address.getCode(),
                address.getNeighborhood(),
                address.getCity(),
                address.getCreatedAtFormatedYear());
    }
}
