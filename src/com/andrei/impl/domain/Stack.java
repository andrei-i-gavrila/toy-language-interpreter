package com.andrei.impl.domain;

import com.andrei.interfaces.domain.IStack;

public class Stack<E> implements IStack<E> {

    private final java.util.Stack<E> stack = new java.util.Stack<>();

    @Override
    public IStack<E> push(E e) {
        stack.push(e);
        return this;
    }

    @Override
    public E pop() {
        return stack.pop();
    }

    @Override
    public E top() {
        return stack.peek();
    }

    @Override
    public boolean empty() {
        return stack.empty();
    }

    @Override
    public String toString() {
        return stack.stream()
                .map(Object::toString)
                .reduce((result, current) -> current + "\n" + result)
                .orElse("Empty");
    }
}
