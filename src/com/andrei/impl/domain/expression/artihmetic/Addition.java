package com.andrei.impl.domain.expression.artihmetic;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.domain.expression.Expression;

public class Addition extends ArithmeticExpression {
    public Addition(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public Integer evaluate(ProgramState programState) throws ToyException {
        return lhs.evaluate(programState) + rhs.evaluate(programState);
    }

    @Override
    public String toString() {
        return lhs.toString() + " + " + rhs.toString();
    }
}
