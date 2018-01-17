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
    }


    public void oneStep(List<ProgramState> programStates) throws InterruptedException {
        programStates.forEach(repository::logProgramState);

        repository.setProgramStates(
                Stream.concat(
                        programStates.stream(),
                        executeThenGetNewProgramStates(getExecutionCallables(programStates)).stream()
                ).collect(Collectors.toList())
        );

        programStates.forEach(repository::logProgramState);
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
        executorService = Executors.newFixedThreadPool(2);
        String finalOutput;
        do {
            oneStep(repository.getProgramStates());
            finalOutput = repository.getProgramStates().get(0).getOutput().toString();
            repository.setProgramStates(getNotCompletedProgramStates());
        } while (!repository.getProgramStates().isEmpty());
        System.out.println(finalOutput);
    }

    private List<ProgramState> getNotCompletedProgramStates() {
        return repository.getProgramStates()
                .stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public String getOutput() {
        return " ";
    }
}
