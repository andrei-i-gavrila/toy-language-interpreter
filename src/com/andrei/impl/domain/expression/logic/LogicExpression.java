package com.andrei.impl.domain.expression.logic;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.domain.expression.Expression;

public abstract class LogicExpression extends Expression {
    @Override
    public Integer evaluate(ProgramState programState) throws ToyException {
        return evaluateBool(programState) ? 1 : 0;
    }

    protected abstract Boolean evaluateBool(ProgramState programState) throws ToyException;
}
