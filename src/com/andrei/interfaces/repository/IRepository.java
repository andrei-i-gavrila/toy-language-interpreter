package com.andrei.interfaces.repository;

import com.andrei.impl.domain.ProgramState;
import javafx.collections.ObservableList;

public interface IRepository {

    void setProgramStates(ObservableList<ProgramState> states);

    ObservableList<ProgramState> getProgramStates();

    void logProgramState(ProgramState state);

    void removeCompletedProgramStates();
}