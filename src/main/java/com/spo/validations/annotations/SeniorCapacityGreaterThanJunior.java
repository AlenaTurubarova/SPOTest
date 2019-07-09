package com.spo.validations.annotations;

import com.spo.validations.CapacityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CapacityValidator.class})
@Documented
public @interface SeniorCapacityGreaterThanJunior {
    String message() default "Senior capacity should be greater than Junior";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
