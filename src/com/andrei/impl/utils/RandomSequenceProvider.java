package com.andrei.impl.utils;

import com.andrei.interfaces.utils.NumberSequenceProvider;

import java.util.concurrent.ThreadLocalRandom;

public class RandomSequenceProvider implements NumberSequenceProvider {

    public Integer next() {
        return ThreadLocalRandom.current().nextInt();
    }
}
