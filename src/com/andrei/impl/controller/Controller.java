package com.andrei.impl.controller;

import com.andrei.impl.domain.GarbageCollectors;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.interfaces.repository.IRepository;

import java.io.IOException;

public class Controller {
    private final IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public void oneStep() throws ToyException {
        if (repository.getCurrentProgramState().getExecutionStack().empty()) {
            throw new ToyException("Program done!");
        }
        repository.getCurrentProgramState().getExecutionStack().pop().execute(repository.getCurrentProgramState());
    }

    public void allSteps() throws ToyException, IOException {
        while (!repository.getCurrentProgramState().getExecutionStack().empty()) {
            oneStep();
            repository.setProgramState(GarbageCollectors.ConservativeGarbageCollector(repository.getCurrentProgramState()));
            repository.logCurrentProgramState();
        }
    }

    public String getOutput() {
        return repository.getCurrentProgramState().getOutput().toString();
    }
}
