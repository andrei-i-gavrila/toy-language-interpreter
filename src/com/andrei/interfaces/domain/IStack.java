package com.andrei.interfaces.domain;

public interface IStack<E> {

    IStack<E> push(E e);
    E pop();
    E top();
    boolean empty();
}
