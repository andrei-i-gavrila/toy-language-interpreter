package com.andrei.impl.domain;

import java.util.ArrayList;

public class ToyList<E> extends ArrayList<E> {

    public ToyList() {
        super();
    }

    public String toString() {
        return this.stream().map(Object::toString).reduce("", String::concat);
    }
}
