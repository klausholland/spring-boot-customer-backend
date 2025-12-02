package com.hollandklaus.customer_backend.web.validation.annotation;

import com.hollandklaus.customer_backend.web.validation.CustomerValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomerValidator.class)
public @interface ValidCustomer {

    String message() default "Customer validation failed";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
