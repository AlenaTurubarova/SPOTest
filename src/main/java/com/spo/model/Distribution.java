package com.spo.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Distribution {

    private int senior;
    private int junior;

    public Distribution(int senior, int junior) {
        this.senior = senior;
        this.junior = junior;
    }
}
