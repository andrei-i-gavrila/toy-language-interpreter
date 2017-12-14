package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.interfaces.domain.IStatement;

import java.util.Arrays;

public class Compound implements IStatement {

    private IStatement thisStatement;
    private IStatement nextStatement;

    public Compound(IStatement... statements) {
        if (statements.length > 2) {
            this.thisStatement = statements[0];
            this.nextStatement = new Compound(Arrays.copyOfRange(statements, 1, statements.length));
        } else if (statements.length == 2) {
            this.thisStatement = statements[0];
            this.nextStatement = statements[1];
        } else {
            throw new RuntimeException("At least 2 arguments needed");
        }
    }

    @Override
    public void execute(ProgramState state) throws ArithmeticException {
        statements.stream().forEach(state.getExecutionStack()::push);
    }

    @Override
    public String toString() {
        return thisStatement.toString() + ";" + nextStatement.toString();
    }
}
