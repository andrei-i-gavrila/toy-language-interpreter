package com.andrei.impl.repository;

import com.andrei.impl.domain.ProgramState;
import com.andrei.interfaces.repository.IRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IRepository {

    private ProgramState currentProgram;
    private final String logFilePath;

    public Repository(ProgramState currentProgram, String logFilePath) {
        this.currentProgram = currentProgram;
        this.logFilePath = logFilePath;
    }

    public ProgramState getCurrentProgramState() {
        return currentProgram;
    }

    @Override
    public void setProgramState(ProgramState state) {
        currentProgram = state;
    }

    @Override
    public void logCurrentProgramState() throws IOException {
        PrintWriter logger = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

        logger.append(getCurrentProgramState().toString()).append("\n\n\n");
        logger.close();
    }

    @Override
    public String toString() {
        return "Repository.programState = " + getCurrentProgramState().toString();
    }
}
