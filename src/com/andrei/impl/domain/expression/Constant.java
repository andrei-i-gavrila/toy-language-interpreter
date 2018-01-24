package com.andrei.impl.domain.expression;

import com.andrei.impl.domain.ProgramState;

public class Constant extends Expression {
    private final Integer number;

    public Constant(Integer number) {
        this.number = number;
    }

    public Integer evaluate(ProgramState programState) {
        return number;
    }

    public String toString() {
        return number.toString();
    }
}
