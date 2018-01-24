package com.andrei.impl.domain;

import java.util.Stack;

public class ToyStack<E> extends Stack<E> {

    public ToyStack() {
        super();
    }

    public ToyStack(E e) {
        super();
        this.push(e);
    }


    public String toString() {
        return this.stream()
                .map(Object::toString)
                .reduce("", (result, current) -> current + "\n" + result);
    }
}
