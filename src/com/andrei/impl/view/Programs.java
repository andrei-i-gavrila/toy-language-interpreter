package com.andrei.impl.view;

import com.andrei.impl.domain.expression.Constant;
import com.andrei.impl.domain.expression.ReadHeap;
import com.andrei.impl.domain.expression.Variable;
import com.andrei.impl.domain.expression.artihmetic.Addition;
import com.andrei.impl.domain.expression.artihmetic.Multiplication;
import com.andrei.impl.domain.expression.artihmetic.Subtraction;
import com.andrei.impl.domain.expression.logic.NotEqual;
import com.andrei.impl.domain.statement.*;
import com.andrei.interfaces.domain.Statement;

import java.util.Arrays;
import java.util.List;

public final class Programs {

    private static final Statement program1 = new Compound(
            new OpenFile("file", "test.in"),
            new ReadFile(new Variable("file"), "a"),
            new ReadFile(new Variable("file"), "b"),
            new Print(new Addition(new Variable("a"), new Variable("b"))),
            new CloseFile(new Variable("file"))
    );

    private static final Statement program2 = new Compound(
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

    private static final Statement program3 = new Compound(
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

    private static final Statement program4 = new Compound(
            new Assignment("v", new Constant(10)),
            new Allocate("v", new Constant(22)),
            new Allocate("a", new Constant(22)),
            new WriteHeap("a", new Constant(30)),
            new Print(new Variable("a")),
            new Print(new ReadHeap(new Variable("a"))),
            new Assignment("a", new Constant(0))
    );

    private static final Statement program5 = new Compound(
            new Assignment("v", new Constant(6)),
            new While(
                    new NotEqual(new Variable("v"), new Constant(4)),
                    new Compound(
                            new Print(new Variable("v")),
                            new Assignment("v", new Subtraction(new Variable("v"), new Constant(1))))),
            new Print(new Variable("v"))
    );

    private static final Statement program6 = new Compound(
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
    private static final Statement program7 = new Compound(
            new Allocate("v1", new Constant(2)),
            new Allocate("v2", new Constant(3)),
            new Allocate("v3", new Constant(4)),
            new NewBarrier("cnt", new ReadHeap(new Variable("v2"))),
            new Fork(
                    new Compound(
                            new Await("cnt"),
                            new WriteHeap("v1", new Multiplication(new ReadHeap(new Variable("v1")), new Constant(10))),
                            new Print(new ReadHeap(new Variable("v1")))
                    )
            ),
            new Fork(
                    new Compound(
                            new Await("cnt"),
                            new WriteHeap("v2", new Multiplication(new ReadHeap(new Variable("v2")), new Constant(10))),
                            new WriteHeap("v2", new Multiplication(new ReadHeap(new Variable("v2")), new Constant(10))),
                            new Print(new ReadHeap(new Variable("v2")))
                    )
            ),
            new Await("cnt"),
            new Print(new ReadHeap(new Variable("v3")))
    );
    public static final List<Statement> PROGRAMS = Arrays.asList(
            program1, program2, program3, program4, program5, program6, program7
    );
}
