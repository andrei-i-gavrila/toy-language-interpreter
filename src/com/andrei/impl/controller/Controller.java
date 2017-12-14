package com.andrei.impl.controller;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.interfaces.repository.IRepository;

import java.io.IOException;

public class Controller {
    private final IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }


    public void allSteps() throws ToyException, IOException {
        Va
        while (!repository.getCurrentProgramState().getExecutionStack().empty()) {
            repository.logCurrentProgramState();
            oneStep();
            repository.setProgramState(GarbageCollectors.ConservativeGarbageCollector(repository.getCurrentProgramState()));
        }
        repository.getCurrentProgramState().getFileTable().closeAllFiles();
        repository.logCurrentProgramState();
    }

    public String getOutput() {
        return repository.getCurrentProgramState().getOutput().toString();
    }
}
