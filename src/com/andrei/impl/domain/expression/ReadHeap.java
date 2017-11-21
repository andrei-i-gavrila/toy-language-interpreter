package com.andrei.impl.domain.expression;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;

public class ReadHeap extends Expression {

    Expression expression;

    public ReadHeap(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Integer evaluate(ProgramState programState) throws ToyException {
        return programState.getHeap().read(expression.evaluate(programState));
    }

    @Override
    public String toString() {
        return "rH(" + expression.toString() + ")";
    }
}
