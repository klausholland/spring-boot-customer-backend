package com.hollandklaus.customer_backend.web.dto;

import com.hollandklaus.customer_backend.web.validation.annotation.ValidAddress;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

import static com.hollandklaus.customer_backend.web.validation.ValidationConstants.*;

@ValidAddress
public record AddressDto(
        UUID id,
        @NotNull(message = NOT_NULL_MESSAGE)
        @Pattern(regexp = "DE|AT|FR|GB|DK|NL", message = PATTERN_MESSAGE)
        String country,
        @NotNull(message = NOT_NULL_MESSAGE)
        @Size(min = 1, max = 100, message = MIN_MAX_SIZE_MESSAGE)
        String street,
        @NotNull(message = NOT_NULL_MESSAGE)
        @Size(min = 1, max = 10, message = MIN_MAX_SIZE_MESSAGE)
        String houseNumber,
        @NotNull(message = NOT_NULL_MESSAGE)
        @Size(min = 1, max = 8, message = MIN_MAX_SIZE_MESSAGE)
        String postalCode
) {
}
