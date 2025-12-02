package com.hollandklaus.customer_backend.web.controller;

import com.hollandklaus.customer_backend.domain.Customer;
import com.hollandklaus.customer_backend.service.CustomerService;
import com.hollandklaus.customer_backend.web.dto.CustomerDto;
import com.hollandklaus.customer_backend.web.mapper.CustomerMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto dto) {
        Customer customer = customerService.createCustomer(dto);
        return ResponseEntity.ok(customerMapper.toDto(customer));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customerDtos = customerService.getAllCustomers().stream().map(customerMapper::toDto).toList();
        return ResponseEntity.ok(customerDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") UUID id) {
        CustomerDto customerDto = customerMapper.toDto(customerService.getCustomerById(id));
        return ResponseEntity.ok(customerDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") UUID id, @Valid @RequestBody CustomerDto dto) {
        CustomerDto customerDto = customerMapper.toDto(customerService.updateCustomer(id, dto));
        return ResponseEntity.ok(customerDto);
    }
}
