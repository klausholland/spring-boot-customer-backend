package com.hollandklaus.customer_backend.service;

import com.hollandklaus.customer_backend.domain.Address;
import com.hollandklaus.customer_backend.domain.Country;
import com.hollandklaus.customer_backend.domain.Customer;
import com.hollandklaus.customer_backend.exception.CustomerNotFoundException;
import com.hollandklaus.customer_backend.repository.AddressRepository;
import com.hollandklaus.customer_backend.repository.CustomerRepository;
import com.hollandklaus.customer_backend.web.dto.AddressDto;
import com.hollandklaus.customer_backend.web.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Customer createCustomer(CustomerDto dto) {
        Address address = handleAddress(dto.address());
        Customer customer = Customer.builder()
                .id(UUID.randomUUID())
                .name(dto.name())
                .surname(dto.surName())
                .taxId(dto.taxId())
                .info(dto.info())
                .address(address)
                .build();

        log.info("Persisting new customer: {}", customer);
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer updateCustomer(UUID id, CustomerDto dto) {
        Address address = handleAddress(dto.address());
        Optional<Customer> customerOpt = customerRepository.findById(id);

        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }

        Customer existingCustomer = customerOpt.get();
        existingCustomer.setName(dto.name());
        existingCustomer.setSurname(dto.surName());
        existingCustomer.setTaxId(dto.taxId());
        existingCustomer.setInfo(dto.info());
        existingCustomer.setAddress(address);

        log.info("Updating customer: {}", existingCustomer);
        return customerRepository.save(existingCustomer);
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer getCustomerById(UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }

        return customer.get();
    }

    private Address handleAddress(AddressDto dto) {
        return addressRepository.findByCountryAndStreetAndHouseNumberAndPostalCode(
                Country.valueOf(dto.country()),
                dto.street(),
                dto.houseNumber(),
                dto.postalCode()
        ).orElseGet(() -> {
            Address newAddress = Address.builder()
                    .id(UUID.randomUUID())
                    .country(Country.valueOf(dto.country()))
                    .street(dto.street())
                    .houseNumber(dto.houseNumber())
                    .postalCode(dto.postalCode())
                    .build();
            log.info("Persisting new address: {}", newAddress);
            return addressRepository.save(newAddress);
    });
    }
}
