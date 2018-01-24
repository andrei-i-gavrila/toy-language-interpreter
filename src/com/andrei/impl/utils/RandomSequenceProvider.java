package com.andrei.impl.utils;

import com.andrei.interfaces.utils.NumberSequenceProvider;

import java.util.Random;

public class RandomSequenceProvider implements NumberSequenceProvider {

    private final Random provider = new Random();

    public Integer next() {
        return provider.nextInt();
    }
}
