package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.interfaces.domain.IFileTable;
import com.andrei.interfaces.domain.IStatement;

public class OpenFile implements IStatement {
    private final String var;
    private final String filename;

    public OpenFile(String var, String filename) {
        this.var = var;
        this.filename = filename;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        IFileTable fileTable = state.getFileTable();
        Integer fileDescriptor = fileTable.openFile(filename);
        state.getSymbolTable().put(var, fileDescriptor);

        return state;
    }

    @Override
    public String toString() {
        return "openFile(" + var + ", " + filename + ")";
    }
}
