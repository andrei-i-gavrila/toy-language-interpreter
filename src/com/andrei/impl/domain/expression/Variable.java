package com.andrei.impl.domain.expression;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.domain.exceptions.VariableException;

public class Variable extends Expression {

    final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public Integer evaluate(ProgramState programState) throws ToyException {
        if (!programState.getSymbolTable().containsKey(variable)) {
            throw new VariableException("Variable " + variable + " does not exist!");
        }
        return programState.getSymbolTable().get(variable);
    }

    @Override
    public String toString() {
        return variable;
    }
}
