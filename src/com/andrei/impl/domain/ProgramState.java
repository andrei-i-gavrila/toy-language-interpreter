package com.andrei.impl.domain;

import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.utils.ContinuousSequenceProvider;
import com.andrei.interfaces.domain.Dictionary;
import com.andrei.interfaces.domain.IFileTable;
import com.andrei.interfaces.domain.IHeap;
import com.andrei.interfaces.domain.IStatement;
import com.andrei.interfaces.utils.NumberSequenceProvider;

import java.util.List;
import java.util.Optional;

public class ProgramState {

    private static final NumberSequenceProvider threadIdProvider = new ContinuousSequenceProvider();

    private final int threadId;
    private final ToyStack<IStatement> executionStack;
    private final Dictionary<String, Integer> symbolTable;
    private final List<String> output;
    private final IFileTable fileTable;
    private final IHeap heap;

    public ProgramState(IStatement startStatement) {
        threadId = threadIdProvider.next();
        executionStack = new ToyStack<>(startStatement);

        symbolTable = new ToyDictionary<>();
        output = new ToyList<>();
        fileTable = new FileTable();
        heap = new Heap();
    }

    public ProgramState(IStatement forkedStatement, ProgramState clonedProgramState) {
        threadId = threadIdProvider.next();
        executionStack = new ToyStack<>(forkedStatement);

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

    public ToyStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public Dictionary<String, Integer> getSymbolTable() {
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


    public String toString() {
        return String.format(
                "ProgramState %d:\nExecution ToyStack:\n%s\nSymbol Table:\n%s\nOutput:\n%s\nFileTable:\n%s\nHeap:\n%s",
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
