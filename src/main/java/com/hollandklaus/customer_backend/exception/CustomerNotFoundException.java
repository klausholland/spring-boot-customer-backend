package com.hollandklaus.customer_backend.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(UUID id) {
        super(String.format("Customer with %s does not exist", id));
    }
}
