package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.interfaces.domain.FileTable;
import com.andrei.interfaces.domain.Statement;

import java.util.Optional;

public class OpenFile implements Statement {

    private final String var;
    private final String filename;

    public OpenFile(String var, String filename) {
        this.var = var;
        this.filename = filename;
    }

    public Optional<ProgramState> execute(ProgramState state) throws ToyException {
        FileTable fileTable = state.getFileTable();
        Integer fileDescriptor = fileTable.openFile(filename);
        state.getSymbolTable().put(var, fileDescriptor);

        return Optional.empty();
    }

    public String toString() {
        return "openFile(" + var + ", " + filename + ")";
    }
}
