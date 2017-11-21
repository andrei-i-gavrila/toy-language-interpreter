package com.andrei.impl.domain.expression.artihmetic;

import com.andrei.impl.domain.expression.Expression;

public abstract class ArithmeticExpression extends Expression {
    protected final Expression lhs;
    protected final Expression rhs;

    protected ArithmeticExpression(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public static Expression plus(Expression lhs, Expression rhs) {
        return new Addition(lhs, rhs);
    }
    public static Expression minus(Expression lhs, Expression rhs) {
        return new Subtraction(lhs, rhs);
    }
    public static Expression divide(Expression lhs, Expression rhs) {
        return new Division(lhs, rhs);
    }
    public static Expression multiply(Expression lhs, Expression rhs) {
        return new Multiplication(lhs, rhs);
    }

}
