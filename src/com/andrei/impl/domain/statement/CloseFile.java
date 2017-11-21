package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.domain.expression.Expression;
import com.andrei.interfaces.domain.IStatement;

public class CloseFile implements IStatement {
    private final Expression fileDescriptorExpression;

    public CloseFile(Expression fileDescriptorExpression) {
        this.fileDescriptorExpression = fileDescriptorExpression;
    }

    @Override
    public void execute(ProgramState state) throws ToyException {
        int fileDescriptor = fileDescriptorExpression.evaluate(state);
        state.getFileTable().closeFile(fileDescriptor);

    }

    @Override
    public String toString() {
        return "closeFile("+fileDescriptorExpression.toString()+")";
    }
}
