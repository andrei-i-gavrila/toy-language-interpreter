package com.andrei.impl.domain;

public class Stack<E> extends java.util.Stack<E> {
    @Override
    public String toString() {
        return this.stream()
                .map(Object::toString)
                .reduce((result, current) -> current + "\n" + result)
                .orElse("Empty");
    }
}
