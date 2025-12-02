package com.hollandklaus.customer_backend.web.validation;

import com.hollandklaus.customer_backend.domain.Country;
import com.hollandklaus.customer_backend.web.dto.AddressDto;
import com.hollandklaus.customer_backend.web.validation.annotation.ValidAddress;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

import static com.hollandklaus.customer_backend.web.validation.ValidationConstants.*;

public class AddressValidator implements ConstraintValidator<ValidAddress, AddressDto> {

    private static final Pattern DE_POSTAL_CODE = Pattern.compile("\\d{5}");
    private static final Pattern AT_POSTAL_CODE = Pattern.compile("\\d{4}");
    private static final Pattern FR_POSTAL_CODE = Pattern.compile("\\d{5}");
    private static final Pattern GB_POSTAL_CODE = Pattern.compile("([A-Z]{1,2}[0-9][0-9A-Z]?)\\s?[0-9][A-Z]{2}");
    private static final Pattern DK_POSTAL_CODE = Pattern.compile("\\d{4}");
    private static final Pattern NL_POSTAL_CODE = Pattern.compile("\\d{4}\\s?[A-Z]{2}");

    private static final String FIELD_NAME = "postalCode";

    @Override
    public boolean isValid(AddressDto addressDto, ConstraintValidatorContext constraintValidatorContext) {
        Country country;

        try {
             country = Country.valueOf(addressDto.country());
        } catch (IllegalArgumentException | NullPointerException e) {
            return reject(constraintValidatorContext, UNKNOWN_COUNTRY_POSTAL_CODE_MESSAGE);
        }

        switch (country) {
            case Country.DE -> {
                if (!DE_POSTAL_CODE.matcher(addressDto.postalCode()).matches()) {
                    return reject(constraintValidatorContext, DE_POSTAL_CODE_VALIDATION_MESSAGE);
                }
            }
            case Country.AT -> {
                if (!AT_POSTAL_CODE.matcher(addressDto.postalCode()).matches()) {
                    return reject(constraintValidatorContext, AT_POSTAL_CODE_VALIDATION_MESSAGE);
                }
            }
            case Country.FR -> {
                if (!FR_POSTAL_CODE.matcher(addressDto.postalCode()).matches()) {
                    return reject(constraintValidatorContext, FR_POSTAL_CODE_VALIDATION_MESSAGE);
                }
            }
            case Country.GB -> {
                if (!GB_POSTAL_CODE.matcher(addressDto.postalCode()).matches()) {
                    return reject(constraintValidatorContext, GB_POSTAL_CODE_VALIDATION_MESSAGE);
                }
            }
            case Country.DK -> {
                if (!DK_POSTAL_CODE.matcher(addressDto.postalCode()).matches()) {
                    return reject(constraintValidatorContext, DK_POSTAL_CODE_VALIDATION_MESSAGE);
                }
            }
            case Country.NL -> {
                if (!NL_POSTAL_CODE.matcher(addressDto.postalCode()).matches()) {
                    return reject(constraintValidatorContext, NL_POSTAL_CODE_VALIDATION_MESSAGE);
                }
            }
        }
        return true;
    }

    private boolean reject(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(FIELD_NAME)
                .addConstraintViolation();
        return false;
    }
}
