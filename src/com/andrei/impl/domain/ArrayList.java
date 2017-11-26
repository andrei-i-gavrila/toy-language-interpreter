package com.andrei.impl.domain;


public class ArrayList<E> extends java.util.ArrayList<E> {
    public ArrayList() {
        super();
    }
    public ArrayList(E e) {
        super();
        this.add(e);
    }
    @Override
    public String toString() {
        return this.stream().map(Object::toString).reduce(String::concat).orElse("Empty");
    }
}
