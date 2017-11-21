package com.andrei.interfaces.domain;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;

public interface IStatement {

    void execute(ProgramState state) throws ToyException;

}
