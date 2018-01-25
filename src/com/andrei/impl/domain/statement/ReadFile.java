package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.domain.expression.Expression;
import com.andrei.interfaces.domain.FileTable;
import com.andrei.interfaces.domain.Statement;

import java.io.IOException;
import java.util.Optional;

public class ReadFile implements Statement {

    private final Expression fileDescriptorExpression;
    private final String var;

    public ReadFile(Expression fileDescriptorExpression, String var) {
        this.fileDescriptorExpression = fileDescriptorExpression;
        this.var = var;
    }

    public Optional<ProgramState> execute(ProgramState state) throws ToyException {
        FileTable fileTable = state.getFileTable();
        int fileDescriptor = fileDescriptorExpression.evaluate(state);

        String line;
        try {
            line = fileTable.getFileReader(fileDescriptor).readLine();
            if (line == null) {
                throw new ToyException("File with fd " + fileDescriptor + " has no input left");
            }
        } catch (IOException e) {
            throw new ToyException("Couldn't read from file with fd " + fileDescriptor);
        }

        Integer readValue = line.isEmpty() ? 0 : Integer.parseInt(line);
        state.getSymbolTable().put(var, readValue);

        return Optional.empty();
    }

    public String toString() {
        return "readFromFile(" + var + ", " + fileDescriptorExpression.toString() + ")";
    }
}
