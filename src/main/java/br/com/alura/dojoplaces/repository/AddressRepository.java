package br.com.alura.dojoplaces.repository;

import br.com.alura.dojoplaces.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
