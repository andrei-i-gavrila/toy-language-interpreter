package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ArrayList;
import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.expression.artihmetic.ArithmeticException;
import com.andrei.interfaces.domain.IStatement;

import java.util.Arrays;
import java.util.List;

public class Compound implements IStatement {

    final List<IStatement> statements = new ArrayList<>();

    public Compound(IStatement... statements) {
        this.statements.addAll(Arrays.asList(statements));
    }

    @Override
    public ProgramState execute(ProgramState state) throws ArithmeticException {
        statements.forEach(state.getExecutionStack()::push);

        return state;
    }

    @Override
    public String toString() {
        return statements.stream().map(Object::toString).map(s -> s + ";").reduce(String::concat).orElse(";");
    }
}
