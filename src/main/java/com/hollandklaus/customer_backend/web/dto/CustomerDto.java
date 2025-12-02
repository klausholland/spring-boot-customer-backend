package com.hollandklaus.customer_backend.web.dto;

import com.hollandklaus.customer_backend.web.validation.annotation.ValidCustomer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

import static com.hollandklaus.customer_backend.web.validation.ValidationConstants.*;

@ValidCustomer
public record CustomerDto(
        UUID id,
        @NotNull(message = NOT_NULL_MESSAGE)
        @Size(min = 1, max = 255, message = MIN_MAX_SIZE_MESSAGE)
        String name,
        @NotNull(message = NOT_NULL_MESSAGE)
        @Size(min = 1, max = 255, message = MIN_MAX_SIZE_MESSAGE)
        String surName,
        @Size(max = 100, message = MAX_SIZE_MESSAGE)
        String info,
        @NotNull(message = NOT_NULL_MESSAGE)
        @Size(min = 1, max = 14, message = MIN_MAX_SIZE_MESSAGE)
        String taxId,
        @NotNull(message = NOT_NULL_MESSAGE)
        @Valid
        AddressDto address
) {
}
