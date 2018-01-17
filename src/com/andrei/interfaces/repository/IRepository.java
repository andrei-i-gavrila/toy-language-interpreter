package com.andrei.interfaces.repository;

import com.andrei.impl.domain.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {

    void setProgramStates(List<ProgramState> states);

    List<ProgramState> getProgramStates();

    void logProgramState(ProgramState state);
}