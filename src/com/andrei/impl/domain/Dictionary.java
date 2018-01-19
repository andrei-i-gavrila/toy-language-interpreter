package com.andrei.impl.domain;

import com.andrei.interfaces.domain.IDictionary;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dictionary<K, V> extends HashMap<K, V>  implements IDictionary<K, V> {

    public Dictionary() {
    }


    @Override
    public String toString() {
        return this.entrySet().stream()
                .map(entry -> entry.getKey() + " : " + entry.getValue())
                .reduce((result, current) -> result + "\n" + current)
                .orElse("Empty");
    }

    @Override
    public IDictionary<K, V> clone() {
        IDictionary<K, V> cloned = new Dictionary<>();
        this.forEach(cloned::put);
        return cloned;
    }
}
