package com.spo.validations;

import com.spo.model.Order;
import com.spo.validations.annotations.SeniorCapacityGreaterThanJunior;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CapacityValidator implements ConstraintValidator<SeniorCapacityGreaterThanJunior, Order> {

    @Override
    public boolean isValid(Order order, ConstraintValidatorContext constraintValidatorContext) {
        return order.getSenior() > order.getJunior();
    }
}
