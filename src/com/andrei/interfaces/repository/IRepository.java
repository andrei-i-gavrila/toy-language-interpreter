package com.andrei.interfaces.repository;

import com.andrei.impl.domain.ProgramState;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public interface IRepository {

    void setProgramStates(ObservableList<ProgramState> states);

    ObservableList<ProgramState> getProgramStates();

    void logProgramState(ProgramState state);

    void removeCompletedProgramStates();
}