package com.andrei.impl.domain;

import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.interfaces.domain.IDictionary;
import com.andrei.interfaces.domain.IFileTable;
import com.andrei.interfaces.domain.IHeap;
import com.andrei.interfaces.domain.IStatement;

import java.util.List;
import java.util.Optional;

public class ProgramState {

    private int threadId;
    private final Stack<IStatement> executionStack;
    private final IDictionary<String, Integer> symbolTable;
    private final List<String> output;
    private final IFileTable fileTable;
    private final IHeap heap;

    public ProgramState(IStatement startStatement) {
        threadId = 1;
        executionStack = new Stack<>();
        executionStack.push(startStatement);

        symbolTable = new Dictionary<>();
        output = new ArrayList<>();
        fileTable = new FileTable();
        heap = new Heap();
    }

    public ProgramState(IStatement forkedStatement, ProgramState clonedProgramState) {
        threadId = clonedProgramState.threadId * 10;
        executionStack = new Stack<>();
        executionStack.push(forkedStatement);

        symbolTable = clonedProgramState.symbolTable.clone();
        output = clonedProgramState.output;
        fileTable = clonedProgramState.fileTable;
        heap = clonedProgramState.heap;
    }

    public Optional<ProgramState> oneStep() throws ToyException {
        return executionStack.pop().execute(this);
    }

    public IFileTable getFileTable() {
        return fileTable;
    }

    public Stack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public IDictionary<String, Integer> getSymbolTable() {
        return symbolTable;
    }

    public List<String> getOutput() {
        return output;
    }

    public IHeap getHeap() {
        return heap;
    }

    public boolean isCompleted() {
        return executionStack.empty();
    }

    @Override
    public String toString() {
        return String.format(
                "ProgramState %d:\nExecution Stack:\n%s\nSymbol Table:\n%s\nOutput:\n%s\nFileTable:\n%s\nHeap:\n%s",
                threadId,
                executionStack.toString(),
                symbolTable.toString(),
                output.toString(),
                fileTable.toString(),
                heap.toString()
        );

    }

    public int getThreadId() {
        return threadId;
    }
}
