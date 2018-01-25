package com.andrei.impl.domain;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ToyBarrier {

    private int limit;
    private Set<Integer> programStateIdentifiers;

    public ToyBarrier(int limit) {
        this.limit = limit;
        this.programStateIdentifiers = ConcurrentHashMap.newKeySet();
    }

    public int getLimit() {
        return limit;
    }

    public Set<Integer> getProgramStateIdentifiers() {
        return programStateIdentifiers;
    }
}
