package com.andrei.impl.utils;

import com.andrei.interfaces.utils.NumberSequenceProvider;

import java.util.concurrent.atomic.AtomicInteger;

public class ContinuousSequenceProvider implements NumberSequenceProvider {

    private final AtomicInteger current = new AtomicInteger(1);

    public Integer next() {
        return current.getAndIncrement();
    }
}
