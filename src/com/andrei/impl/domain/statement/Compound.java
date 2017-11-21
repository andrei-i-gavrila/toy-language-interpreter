package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.expression.artihmetic.ArithmeticException;
import com.andrei.interfaces.domain.IStatement;

public class Compound implements IStatement {

    final IStatement thisStatement;
    final IStatement nextStatement;

    public Compound(IStatement thisStatement, IStatement nextStatement) {
        this.thisStatement = thisStatement;
        this.nextStatement = nextStatement;
    }

    @Override
    public void execute(ProgramState state) throws ArithmeticException {
        state.getExecutionStack()
                .push(nextStatement)
                .push(thisStatement);

    }

    @Override
    public String toString() {
        return thisStatement.toString() + ";" + nextStatement.toString();
    }
}
