package com.andrei.impl.view.text;

import com.andrei.impl.controller.Controller;
import com.andrei.impl.domain.ProgramState;
import com.andrei.impl.repository.Repository;
import com.andrei.interfaces.domain.IStatement;

public class RunExampleCommand extends Command {
    private final IStatement program;

    public RunExampleCommand(String key, IStatement program) {
        super(key, program.toString());
        this.program = program;
    }

    @Override
    public void run() {
        Controller controller = new Controller(new Repository(new ProgramState(program), key + ".log"));
        try {
            controller.allSteps();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(controller.getOutput());
    }
}
