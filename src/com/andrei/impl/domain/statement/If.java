package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.domain.expression.Expression;
import com.andrei.interfaces.domain.Statement;

import java.util.Optional;

public class If implements Statement {

    final Expression condition;
    final Statement thenStatement;
    final Statement elseStatement;

    public If(Expression condition, Statement thenStatement, Statement elseStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public If(Expression condition, Statement statement) {
        this.condition = condition;
        this.thenStatement = statement;
        this.elseStatement = null;
    }

    public Optional<ProgramState> execute(ProgramState state) throws ToyException {
        if (condition.evaluate(state) != 0) {
            state.getExecutionStack().push(thenStatement);
        } else if (elseStatement != null) {
            state.getExecutionStack().push(elseStatement);
        }

        return Optional.empty();
    }

    public String toString() {
        return "if (" + condition.toString() + ") " +
                "THEN (" + thenStatement.toString() + ") " +
                (elseStatement != null ? "ELSE (" + elseStatement.toString() + ")" : "");
    }
}
