package com.andrei.interfaces.repository;

import com.andrei.impl.domain.ProgramState;

import java.io.IOException;

public interface IRepository {

    ProgramState getCurrentProgramState();

    void setProgramState(ProgramState state);

    void logCurrentProgramState() throws IOException;
}