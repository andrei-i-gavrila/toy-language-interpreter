package com.andrei.impl.domain.statement;

import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.domain.ToyBarrier;
import com.andrei.impl.domain.exceptions.ToyException;
import com.andrei.interfaces.domain.Statement;

import java.util.Optional;

public class Await implements Statement {

    final String variable;

    public Await(String variable) {
        this.variable = variable;
    }

    public Optional<ProgramState> execute(ProgramState state) throws ToyException {

        if (!state.getSymbolTable().containsKey(variable)) {
            throw new ToyException("Variable " + variable + " does not exist");
        }

        Integer barrierId = state.getSymbolTable().get(variable);

        if (!state.getBarrierTable().getBarriers().containsKey(barrierId)) {
            throw new ToyException("Barrier " + variable + " does not exist");
        }

        ToyBarrier barrier = state.getBarrierTable().getBarriers().get(barrierId);

        if (barrier.getLimit() > barrier.getProgramStateIdentifiers().size()) {
            if (!barrier.getProgramStateIdentifiers().contains(state.getThreadId())) {
                barrier.getProgramStateIdentifiers().add(state.getThreadId());
            }
            state.getExecutionStack().push(new Await(variable));
        }

        return Optional.empty();
    }

    public String toString() {
        return "await(" + variable + ')';
    }
}
