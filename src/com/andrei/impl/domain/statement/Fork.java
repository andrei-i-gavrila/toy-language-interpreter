package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.interfaces.domain.Statement;

import java.util.Optional;

public class Fork implements Statement {

    private final Statement forkedStatement;

    public Fork(Statement forkedStatement) {
        this.forkedStatement = forkedStatement;
    }

    public Optional<ProgramState> execute(ProgramState state) throws ToyException {
        return Optional.of(new ProgramState(forkedStatement, state));
    }

    public String toString() {
        return "fork(" + forkedStatement.toString() + ")";
    }
}
