package com.andrei.impl.domain.expression;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.exceptions.ToyException;

public abstract class Expression {

    public abstract Integer evaluate(ProgramState programState) throws ToyException;

}
