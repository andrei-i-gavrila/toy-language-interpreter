package com.andrei.interfaces.domain;

import java.util.stream.Stream;

public interface IList<E> {

    int size();

    void add(E e);

    void addAll(E... es);

    Stream<E> stream();
}
