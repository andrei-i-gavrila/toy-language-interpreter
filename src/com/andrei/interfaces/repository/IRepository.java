package com.andrei.interfaces.repository;

import com.andrei.impl.domain.ProgramState;
import javafx.collections.ObservableList;

public interface IRepository {

    ObservableList<ProgramState> getProgramStates();

    void setProgramStates(ObservableList<ProgramState> states);

    void logProgramState(ProgramState state);

    void removeCompletedProgramStates();
}