package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.domain.expression.Expression;
import com.andrei.interfaces.domain.Statement;

import java.util.Optional;

public class NewBarrier implements Statement {

    final String variableName;
    final Expression expression;

    public NewBarrier(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    public Optional<ProgramState> execute(ProgramState state) throws ToyException {
        Integer barrierId = state.getBarrierTable().createBarrier(expression.evaluate(state));
        state.getSymbolTable().put(variableName, barrierId);

        return Optional.empty();
    }

    public String toString() {
        return "newBarrier(" + variableName + ", " + expression.toString() + ")";
    }
}
