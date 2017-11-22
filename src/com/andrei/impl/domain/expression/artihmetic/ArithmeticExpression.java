package com.andrei.impl.domain.expression.artihmetic;

import com.andrei.impl.domain.expression.Expression;

public abstract class ArithmeticExpression extends Expression {
    protected final Expression lhs;
    protected final Expression rhs;

    protected ArithmeticExpression(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
