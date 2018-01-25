package com.andrei.impl.domain;

import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.impl.utils.ContinuousSequenceProvider;
import com.andrei.interfaces.domain.*;
import com.andrei.interfaces.utils.NumberSequenceProvider;

import java.util.List;
import java.util.Optional;

public class ProgramState {

    private static final NumberSequenceProvider threadIdProvider = new ContinuousSequenceProvider();

    private final int threadId;
    private final ToyStack<Statement> executionStack;
    private final Dictionary<String, Integer> symbolTable;
    private final List<String> output;
    private final FileTable fileTable;
    private final Heap heap;
    private final BarrierTable barrierTable;

    public ProgramState(Statement startStatement) {
        threadId = threadIdProvider.next();
        executionStack = new ToyStack<>(startStatement);

        symbolTable = new ToyDictionary<>();
        output = new ToyList<>();
        fileTable = new ToyFileTable();
        heap = new ToyHeap();
        barrierTable = new ToyBarrierTable();
    }

    public ProgramState(Statement forkedStatement, ProgramState clonedProgramState) {
        threadId = threadIdProvider.next();
        executionStack = new ToyStack<>(forkedStatement);

        symbolTable = clonedProgramState.symbolTable.clone();
        output = clonedProgramState.output;
        fileTable = clonedProgramState.fileTable;
        heap = clonedProgramState.heap;
        barrierTable = clonedProgramState.barrierTable;
    }

    public BarrierTable getBarrierTable() {
        return barrierTable;
    }

    public Optional<ProgramState> oneStep() throws ToyException {
        return executionStack.pop().execute(this);
    }

    public FileTable getFileTable() {
        return fileTable;
    }

    public ToyStack<Statement> getExecutionStack() {
        return executionStack;
    }

    public Dictionary<String, Integer> getSymbolTable() {
        return symbolTable;
    }

    public List<String> getOutput() {
        return output;
    }

    public Heap getHeap() {
        return heap;
    }

    public boolean isCompleted() {
        return executionStack.empty();
    }

    public String toString() {
        return String.format(
                "ProgramState %d:\nExecution ToyStack:\n%s\nSymbol Table:\n%s\nOutput:\n%s\nToyFileTable:\n%s\nToyHeap:\n%s",
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
