package com.andrei.interfaces.domain;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;

import java.util.Optional;

public interface IStatement {

    Optional<ProgramState> execute(ProgramState state) throws ToyException;

}
