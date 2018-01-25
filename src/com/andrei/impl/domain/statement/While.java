package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.domain.expression.Expression;
import com.andrei.interfaces.domain.Statement;

import java.util.Optional;

public class While implements Statement {

    private final Expression condition;
    private final Statement statement;

    public While(Expression condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    public Optional<ProgramState> execute(ProgramState state) throws ToyException {
        if (condition.evaluate(state) != 0) {
            state.getExecutionStack().push(this);
            state.getExecutionStack().push(statement);
        }
        return Optional.empty();
    }

    public String toString() {
        return "while (" + condition.toString() + ") { " + statement.toString() + " }";
    }
}
