package com.spo.model;

import com.spo.validations.annotations.RoomLoadLessThan;
import com.spo.validations.annotations.SeniorCapacityGreaterThanJunior;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@SeniorCapacityGreaterThanJunior
public class Order {

    @NotEmpty(message = "Rooms array should be not empty")
    @RoomLoadLessThan
    private List<Integer> rooms;

    @Min(value = 1, message = "Senior capacity should be greater than 0")
    private int senior;

    @Min(value = 1, message = "Junior capacity should be greater than 0")
    private int junior;
}
