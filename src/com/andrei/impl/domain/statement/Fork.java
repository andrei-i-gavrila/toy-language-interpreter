package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.interfaces.domain.IStatement;

import javax.swing.text.html.Option;
import java.util.Optional;

public class Fork implements IStatement {

    private IStatement forkedStatement;

    public Fork(IStatement forkedStatement) {
        this.forkedStatement = forkedStatement;
    }

    @Override
    public Optional<ProgramState> execute(ProgramState state) throws ToyException {
        return Optional.of(new ProgramState(forkedStatement, state));
    }

    @Override
    public String toString() {
        return "fork(" + forkedStatement.toString() + ")";
    }
}
