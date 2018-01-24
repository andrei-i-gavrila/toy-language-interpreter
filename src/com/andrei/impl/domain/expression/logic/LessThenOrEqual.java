package com.andrei.impl.domain.expression.logic;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.domain.expression.Expression;

public class LessThenOrEqual extends LogicExpression {
    private final Expression lhs;
    private final Expression rhs;

    public LessThenOrEqual(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }


    protected Boolean evaluateBool(ProgramState programState) throws ToyException {
        return lhs.evaluate(programState) <= rhs.evaluate(programState);
    }


    public String toString() {
        return lhs.toString() + " <= " + rhs.toString();
    }
}
