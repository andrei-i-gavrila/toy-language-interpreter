package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.domain.expression.Expression;
import com.andrei.impl.domain.ProgramState;
import com.andrei.interfaces.domain.IStatement;

public class Assignment implements IStatement{
    final String identifier;
    final Expression expression;

    public Assignment(String identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        state.getSymbolTable().put(identifier, expression.evaluate(state));

        return state;
    }

    @Override
    public String toString() {
        return identifier + " = " + expression.toString();
    }
}
