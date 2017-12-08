package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.domain.expression.Expression;
import com.andrei.interfaces.domain.IStatement;

public class While implements IStatement {
    private Expression condition;
    private IStatement statement;

    public While(Expression condition, IStatement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public void execute(ProgramState state) throws ToyException {
        if (condition.evaluate(state) != 0) {
            state.getExecutionStack().push(this);
            state.getExecutionStack().push(statement);
        }
    }

    @Override
    public String toString() {
        return "while (" + condition.toString() + ") { " + statement.toString() + " }";
    }
}
