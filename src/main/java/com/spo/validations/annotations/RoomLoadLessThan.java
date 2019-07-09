package com.spo.validations.annotations;

import com.spo.validations.RoomLoadValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RoomLoadValidator.class})
@Documented
public @interface RoomLoadLessThan {

    String message() default "Room load should be less than {maxLoad}";

    int maxLoad() default 100;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
