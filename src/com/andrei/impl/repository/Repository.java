package com.andrei.impl.repository;

import com.andrei.impl.domain.ProgramState;
import com.andrei.interfaces.repository.IRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IRepository {

    private final String logFilePath;
    private ObservableList<ProgramState> programStates;

    public Repository(ProgramState currentProgram, String logFilePath) {
        this.programStates = FXCollections.observableArrayList(currentProgram);
        this.logFilePath = logFilePath;
    }

    public void logProgramState(ProgramState state) {
        try {
            new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))
                    .append(state.toString())
                    .append("\n\n")
                    .close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeCompletedProgramStates() {
        programStates.removeIf(ProgramState::isCompleted);
    }

    public ObservableList<ProgramState> getProgramStates() {
        return programStates;
    }

    public void setProgramStates(ObservableList<ProgramState> programStates) {
        this.programStates = programStates;
    }
}
