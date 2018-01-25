package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.domain.expression.Expression;
import com.andrei.interfaces.domain.Statement;

import java.util.Optional;

public class CloseFile implements Statement {

    private final Expression fileDescriptorExpression;

    public CloseFile(Expression fileDescriptorExpression) {
        this.fileDescriptorExpression = fileDescriptorExpression;
    }

    public Optional<ProgramState> execute(ProgramState state) throws ToyException {
        int fileDescriptor = fileDescriptorExpression.evaluate(state);
        state.getFileTable().closeFile(fileDescriptor);

        return Optional.empty();
    }

    public String toString() {
        return "closeFile(" + fileDescriptorExpression.toString() + ")";
    }
}
