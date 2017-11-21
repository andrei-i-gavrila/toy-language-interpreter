package com.andrei;

import com.andrei.impl.domain.expression.Constant;
import com.andrei.impl.domain.expression.ReadHeap;
import com.andrei.impl.domain.expression.Variable;
import com.andrei.impl.domain.expression.artihmetic.Addition;
import com.andrei.impl.domain.expression.artihmetic.ArithmeticExpression;
import com.andrei.impl.domain.statement.*;
import com.andrei.impl.view.RunExampleCommand;
import com.andrei.impl.view.TextMenu;
import com.andrei.interfaces.domain.IStatement;

public class Main {

    public static void main(String[] args) {
        IStatement program1 = new Compound(
                new OpenFile("file", "test.in"),
                new Compound(
                        new ReadFile(new Variable("file"), "a"),
                        new Compound(
                                new ReadFile(new Variable("file"), "b"),
                                new Compound(
                                        new Print(new Addition(new Variable("a"), new Variable("b"))),
                                        new CloseFile(new Variable("file"))
                                )
                        )
                )
        );

        IStatement program2 = new Compound(
                new OpenFile("f", "test.in"),
                new Compound(
                        new ReadFile(new Variable("f"), "c"),
                        new Compound(
                                new Print(new Variable("c")),
                                new Compound(
                                        new If(
                                                new Variable("c"),
                                                new Compound(
                                                        new ReadFile(new Variable("f"), "c"),
                                                        new Print(new Variable("c"))),
                                                new Print(new Constant(0))),
                                        new CloseFile(new Variable("f"))
                                )
                        )
                )
        );


        IStatement program3 = new Compound(
                new OpenFile("f", "test.in"),
                new Compound(
                        new ReadFile(ArithmeticExpression.plus(new Variable("f"), new Constant(2)), "c"),
                        new Compound(
                                new Print(new Variable("c")),
                                new Compound(
                                        new If(
                                                new Variable("c"),
                                                new Compound(
                                                        new ReadFile(new Variable("f"), "c"),
                                                        new Print(new Variable("c"))),
                                                new Print(new Constant(0))),
                                        new CloseFile(new Variable("f"))
                                )
                        )
                )
        );

        IStatement program4 = new Compound(
                new Assignment("v", new Constant(10)),
                new Compound(
                        new Allocate("v", new Constant(22)),
                        new Compound(
                                new Allocate("a", new Constant(22)),
                                new Compound(
                                        new WriteHeap("a", new Constant(30)),
                                        new Compound(
                                                new Print(new Variable("a")),
                                                new Compound(
                                                        new Print(new ReadHeap(new Variable("a"))),
                                                        new Assignment("a", new Constant(0))
                                                )
                                        )
                                )
                        )
                )
        );

        new TextMenu("default", "Starting point",
                new RunExampleCommand("e1", program1),
                new RunExampleCommand("e2", program2),
                new RunExampleCommand("e3", program3),
                new RunExampleCommand("e4", program4)
        ).run();
    }
}
