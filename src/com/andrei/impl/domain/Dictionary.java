package com.andrei.impl.domain;

import java.util.LinkedHashMap;

public class Dictionary<K, V> extends LinkedHashMap<K, V> {
    @Override
    public String toString() {
        return this.entrySet().stream()
                .map(entry -> entry.getKey() + " : " + entry.getValue())
                .reduce((result, current) -> result + "\n" + current)
                .orElse("Empty");
    }
}
