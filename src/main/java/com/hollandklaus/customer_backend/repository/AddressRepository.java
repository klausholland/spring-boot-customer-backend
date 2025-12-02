package com.hollandklaus.customer_backend.repository;

import com.hollandklaus.customer_backend.domain.Address;
import com.hollandklaus.customer_backend.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    Optional<Address> findByCountryAndStreetAndHouseNumberAndPostalCode(
            Country country,
            String street,
            String houseNumber,
            String postalCode
    );
}
