package com.spo.validations;

import com.spo.validations.annotations.RoomLoadLessThan;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class RoomLoadValidator implements ConstraintValidator<RoomLoadLessThan, List<Integer>> {
    private int maxLoad;

    @Override
    public void initialize(RoomLoadLessThan constraintAnnotation) {
        this.maxLoad = constraintAnnotation.maxLoad();
    }

    @Override
    public boolean isValid(List<Integer> roomLoads, ConstraintValidatorContext constraintValidatorContext) {
        return roomLoads == null || roomLoads.stream().noneMatch(load -> load > this.maxLoad);
    }
}
