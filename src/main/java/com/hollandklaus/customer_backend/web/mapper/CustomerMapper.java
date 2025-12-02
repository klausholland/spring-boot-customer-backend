package com.hollandklaus.customer_backend.web.mapper;

import com.hollandklaus.customer_backend.domain.Customer;
import com.hollandklaus.customer_backend.web.dto.AddressDto;
import com.hollandklaus.customer_backend.web.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDto toDto(Customer entity) {
        AddressDto addressDto = new AddressDto(
                entity.getAddress().getId(),
                entity.getAddress().getCountry().name(),
                entity.getAddress().getStreet(),
                entity.getAddress().getHouseNumber(),
                entity.getAddress().getPostalCode()
        );

        return new CustomerDto(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getInfo(),
                entity.getTaxId(),
                addressDto
        );
    }
}
