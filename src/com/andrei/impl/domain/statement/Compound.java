package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.interfaces.domain.IStatement;

import java.util.Arrays;
import java.util.Optional;

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

    public Optional<ProgramState> execute(ProgramState state) throws ArithmeticException {
        state.getExecutionStack().push(nextStatement);
        state.getExecutionStack().push(thisStatement);

        return Optional.empty();
    }

    public String toString() {
        return thisStatement.toString() + ";" + nextStatement.toString();
    }
}
