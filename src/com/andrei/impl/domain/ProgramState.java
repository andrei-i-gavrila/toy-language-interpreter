package com.andrei.impl.domain;

import com.andrei.interfaces.domain.*;

import java.util.stream.Stream;

public class ProgramState {

    private final IStack<IStatement> executionStack;
    private final IDictionary<String, Integer> symbolTable;
    private final IList<String> output;
    private final IFileTable fileTable;
    private final IHeap heap;

    public ProgramState(IStatement startStatement) {
        executionStack = new Stack<>();
        executionStack.push(startStatement);

        symbolTable = new Dictionary<>();
        output = new List<>();
        fileTable = new FileTable();
        heap = new Heap();
    }

    public ProgramState(IStack<IStatement> executionStack, IDictionary<String, Integer> symbolTable, IList<String> output, IFileTable fileTable, IHeap heap) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
    }

    public IFileTable getFileTable() {
        return fileTable;
    }

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public IDictionary<String, Integer> getSymbolTable() {
        return symbolTable;
    }

    public IList<String> getOutput() {
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
