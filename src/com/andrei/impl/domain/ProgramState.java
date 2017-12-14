package com.andrei.impl.domain;

import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.interfaces.domain.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ProgramState {

    private final Stack<IStatement> executionStack;
    private final Map<String, Integer> symbolTable;
    private final List<String> output;
    private final IFileTable fileTable;
    private final IHeap heap;

    public ProgramState(IStatement startStatement) {
        executionStack = new Stack<>();
        executionStack.push(startStatement);

        symbolTable = new Dictionary<>();
        output = new ArrayList<>();
        fileTable = new FileTable();
        heap = new Heap();
    }

    public ProgramState(Stack<IStatement> executionStack, Map<String, Integer> symbolTable, List<String> output, IFileTable fileTable, IHeap heap) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
    }

    public ProgramState oneStep() throws ToyException {
        return executionStack.pop().execute(this);
    }

    public IFileTable getFileTable() {
        return fileTable;
    }

    public Stack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public Map<String, Integer> getSymbolTable() {
        return symbolTable;
    }

    public List<String> getOutput() {
        return output;
    }

    public IHeap getHeap() {
        return heap;
    }

    @Override
    public String toString() {
        return Stream.of(executionStack, symbolTable, output, fileTable, heap)
                .reduce((acc, o) -> acc.toString() + '\n' + o.getClass().getSimpleName() + ":\n" + o.toString())
                .orElse("No state")
                .toString();
    }
}
