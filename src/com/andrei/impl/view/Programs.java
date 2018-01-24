package com.andrei.impl.view;

import com.andrei.impl.domain.expression.Constant;
import com.andrei.impl.domain.expression.ReadHeap;
import com.andrei.impl.domain.expression.Variable;
import com.andrei.impl.domain.expression.artihmetic.Addition;
import com.andrei.impl.domain.expression.artihmetic.Subtraction;
import com.andrei.impl.domain.expression.logic.NotEqual;
import com.andrei.impl.domain.statement.*;
import com.andrei.interfaces.domain.IStatement;

import java.util.Arrays;
import java.util.List;

public final class Programs {

    private static final IStatement program1 = new Compound(
            new OpenFile("file", "test.in"),
            new ReadFile(new Variable("file"), "a"),
            new ReadFile(new Variable("file"), "b"),
            new Print(new Addition(new Variable("a"), new Variable("b"))),
            new CloseFile(new Variable("file"))
    );

    private static final IStatement program2 = new Compound(
            new OpenFile("f", "test.in"),
            new ReadFile(new Variable("f"), "c"),
            new Print(new Variable("c")),
            new If(
                    new Variable("c"),
                    new Compound(
                            new ReadFile(new Variable("f"), "c"),
                            new Print(new Variable("c"))),
                    new Print(new Constant(0)))
    );

    private static final IStatement program3 = new Compound(
            new OpenFile("f", "test.in"),
            new ReadFile(new Addition(new Variable("f"), new Constant(2)), "c"),
            new Print(new Variable("c")),
            new If(
                    new Variable("c"),
                    new Compound(
                            new ReadFile(new Variable("f"), "c"),
                            new Print(new Variable("c"))),
                    new Print(new Constant(0))),
            new CloseFile(new Variable("f"))
    );

    private static final IStatement program4 = new Compound(
            new Assignment("v", new Constant(10)),
            new Allocate("v", new Constant(22)),
            new Allocate("a", new Constant(22)),
            new WriteHeap("a", new Constant(30)),
            new Print(new Variable("a")),
            new Print(new ReadHeap(new Variable("a"))),
            new Assignment("a", new Constant(0))
    );

    private static final IStatement program5 = new Compound(
            new Assignment("v", new Constant(6)),
            new While(
                    new NotEqual(new Variable("v"), new Constant(4)),
                    new Compound(
                            new Print(new Variable("v")),
                            new Assignment("v", new Subtraction(new Variable("v"), new Constant(1))))),
            new Print(new Variable("v"))
    );

    private static final IStatement program6 = new Compound(
            new Assignment("v", new Constant(10)),
            new Allocate("a", new Constant(22)),
            new Fork(new Compound(
                    new WriteHeap("a", new Constant(30)),
                    new Assignment("v", new Constant(32)),
                    new Print(new Variable("v")),
                    new Print(new ReadHeap(new Variable("a")))
            )),
            new Print(new Variable("v")),
            new Print(new ReadHeap(new Variable("a")))
    );

    public static final List<IStatement> PROGRAMS = Arrays.asList(program1, program2, program3, program4, program5, program6);
}
