package com.hollandklaus.customer_backend.web.validation;

import com.hollandklaus.customer_backend.web.dto.AddressDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static com.hollandklaus.customer_backend.web.validation.ValidationConstants.DE_POSTAL_CODE_VALIDATION_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddressValidatorTest {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldThrowErrorWhenWrongPostalCodeForDE() {
        AddressDto dto = new AddressDto(
                UUID.randomUUID(),
                "DE",
                "Test street",
                "11",
                "ABCDE"
        );

        List<ConstraintViolation<AddressDto>> violations = validator.validate(dto).stream().toList();

        assertFalse(violations.isEmpty());
        assertEquals(DE_POSTAL_CODE_VALIDATION_MESSAGE, violations.getFirst().getMessage());
    }
}
