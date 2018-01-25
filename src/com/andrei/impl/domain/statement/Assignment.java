package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.domain.expression.Expression;
import com.andrei.interfaces.domain.Statement;

import java.util.Optional;

public class Assignment implements Statement {

    final String identifier;
    final Expression expression;

    public Assignment(String identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public Optional<ProgramState> execute(ProgramState state) throws ToyException {
        state.getSymbolTable().put(identifier, expression.evaluate(state));

        return Optional.empty();
    }

    public String toString() {
        return identifier + " = " + expression.toString();
    }
}
