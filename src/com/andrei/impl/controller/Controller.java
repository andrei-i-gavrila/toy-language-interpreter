package com.andrei.impl.controller;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.interfaces.repository.IRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private final IRepository repository;
    private ExecutorService executorService;

    public Controller(IRepository repository) {
        this.repository = repository;
        executorService = Executors.newFixedThreadPool(2);

    }


    public boolean oneStep() throws InterruptedException {
        List<ProgramState> programStates = repository.getProgramStates();
        programStates.forEach(repository::logProgramState);

        repository.getProgramStates().addAll(executeThenGetNewProgramStates(getExecutionCallables(programStates)));
        programStates.forEach(repository::logProgramState);

        repository.removeCompletedProgramStates();
        return repository.getProgramStates().isEmpty();
    }

    private List<ProgramState> executeThenGetNewProgramStates(List<Callable<Optional<ProgramState>>> callables) throws InterruptedException {
        return executorService.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return Optional.<ProgramState>empty();
                    }
                }).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
    }

    private List<Callable<Optional<ProgramState>>> getExecutionCallables(List<ProgramState> programStates) {
        return programStates
                .stream()
                .map(programState -> (Callable<Optional<ProgramState>>) programState::oneStep)
                .collect(Collectors.toList());
    }

    public void allSteps() throws ToyException, IOException, InterruptedException {
        while(!oneStep());
    }

    public String getOutput() {
        return " ";
    }
}
