package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.domain.expression.Expression;
import com.andrei.interfaces.domain.IStatement;

public class If implements IStatement {

    final Expression condition;
    final IStatement thenStatement;
    final IStatement elseStatement;

    public If(Expression condition, IStatement thenStatement, IStatement elseStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public If(Expression condition, IStatement statement) {
        this.condition = condition;
        this.thenStatement = statement;
        this.elseStatement = null;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        if (condition.evaluate(state) != 0) {
            state.getExecutionStack().push(thenStatement);
        } else if (elseStatement != null) {
            state.getExecutionStack().push(elseStatement);
        }

        return state;
    }

    @Override
    public String toString() {
        return "if (" + condition.toString() + ") " +
                "THEN (" + thenStatement.toString() + ") " +
                (elseStatement != null ? "ELSE (" + elseStatement.toString() + ")" : "");
    }
}
