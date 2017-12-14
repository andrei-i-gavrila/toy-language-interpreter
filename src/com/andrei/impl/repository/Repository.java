package com.andrei.impl.repository;

import com.andrei.impl.domain.ArrayList;
import com.andrei.impl.domain.ProgramState;
import com.andrei.interfaces.repository.IRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Repository implements IRepository {

    private final String logFilePath;
    private List<ProgramState> programStates;

    public Repository(ProgramState currentProgram, String logFilePath) {
        this.programStates = new ArrayList<>(currentProgram);
        this.logFilePath = logFilePath;
    }

    @Override
    public void logCurrentProgramState(ProgramState state) throws IOException {
        new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))
                .append(Thread.currentThread().getName())
                .append("\n")
                .append(state.toString())
                .append("\n\n\n")
                .close();
    }

    public List<ProgramState> getProgramStates() {
        return programStates;
    }

    public void setProgramStates(List<ProgramState> programStates) {
        this.programStates = programStates;
    }
}
