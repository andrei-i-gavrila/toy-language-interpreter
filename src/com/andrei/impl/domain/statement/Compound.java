package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.List;
import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.expression.artihmetic.ArithmeticException;
import com.andrei.interfaces.domain.IList;
import com.andrei.interfaces.domain.IStatement;

public class Compound implements IStatement {

    final IList<IStatement> statements = new List<>();

    public Compound(IStatement... statements) {
        this.statements.addAll(statements);
    }

    @Override
    public void execute(ProgramState state) throws ArithmeticException {
        statements.stream().forEach(state.getExecutionStack()::push);
    }

    @Override
    public String toString() {
        return statements.stream().map(Object::toString).map(s -> s + ";").reduce(String::concat).orElse(";");
    }
}
