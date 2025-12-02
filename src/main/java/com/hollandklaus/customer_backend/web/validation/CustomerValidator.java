package com.hollandklaus.customer_backend.web.validation;

import com.hollandklaus.customer_backend.domain.Country;
import com.hollandklaus.customer_backend.web.dto.CustomerDto;
import com.hollandklaus.customer_backend.web.validation.annotation.ValidCustomer;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

import static com.hollandklaus.customer_backend.web.validation.ValidationConstants.*;

public class CustomerValidator implements ConstraintValidator<ValidCustomer, CustomerDto> {

    private static final Pattern DE_TAX_ID = Pattern.compile("DE\\d{9}");
    private static final Pattern AT_TAX_ID = Pattern.compile("ATU\\d{8}");
    private static final Pattern FR_TAX_ID = Pattern.compile("FR[A-Z0-9]{0,2}\\d{9}");
    private static final Pattern GB_TAX_ID = Pattern.compile("GB(?:\\d{12}|\\d{9}|GD\\d{3}|HA\\d{3})");
    private static final Pattern DK_TAX_ID = Pattern.compile("DK\\d{8}");
    private static final Pattern NL_TAX_ID = Pattern.compile("NL\\d{9}B\\d{2}");

    private static final String FIELD_NAME = "taxId";

    @Override
    public boolean isValid(CustomerDto customerDto, ConstraintValidatorContext constraintValidatorContext) {
        Country country;

        try {
            country = Country.valueOf(customerDto.address().country());
        } catch (IllegalArgumentException | NullPointerException e) {
            return reject(constraintValidatorContext, UNKNOWN_COUNTRY_TAX_ID_MESSAGE);
        }

        switch (country) {
            case Country.DE -> {
                if (!DE_TAX_ID.matcher(customerDto.taxId()).matches()) {
                    return reject(constraintValidatorContext, DE_TAX_ID_VALIDATION_MESSAGE);
                }
            }
            case Country.AT -> {
                if (!AT_TAX_ID.matcher(customerDto.taxId()).matches()) {
                    return reject(constraintValidatorContext, AT_TAX_ID_VALIDATION_MESSAGE);
                }
            }
            case Country.FR -> {
                if (!FR_TAX_ID.matcher(customerDto.taxId()).matches()) {
                    return reject(constraintValidatorContext, FR_TAX_ID_VALIDATION_MESSAGE);
                }
            }
            case Country.GB -> {
                if (!GB_TAX_ID.matcher(customerDto.taxId()).matches()) {
                    return reject(constraintValidatorContext, GB_TAX_ID_VALIDATION_MESSAGE);
                }
            }
            case Country.DK -> {
                if (!DK_TAX_ID.matcher(customerDto.taxId()).matches()) {
                    return reject(constraintValidatorContext, DK_TAX_ID_VALIDATION_MESSAGE);
                }
            }
            case Country.NL -> {
                if (!NL_TAX_ID.matcher(customerDto.taxId()).matches()) {
                    return reject(constraintValidatorContext, NL_TAX_ID_VALIDATION_MESSAGE);
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
