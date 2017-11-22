package com.andrei.impl.domain;

import com.andrei.interfaces.domain.IList;

import java.util.ArrayList;
import java.util.stream.Stream;

public class List<E> implements IList<E> {
    final java.util.List<E> list = new ArrayList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(E e) {
        list.add(e);
    }

    @SafeVarargs
    public final void addAll(E... es) {
        for (E e : es) {
            add(e);
        }
    }

    @Override
    public Stream<E> stream() {
        return list.stream();
    }


    @Override
    public String toString() {
        return list.stream().map(Object::toString).reduce(String::concat).orElse("Empty");
    }
}
