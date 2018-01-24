package com.andrei.impl.domain;

import com.andrei.interfaces.domain.Dictionary;

import java.util.concurrent.ConcurrentHashMap;

public class ToyDictionary<K, V> extends ConcurrentHashMap<K, V> implements Dictionary<K, V> {

    public ToyDictionary() {
    }



    public String toString() {
        return this.entrySet().stream()
                .map(entry -> entry.getKey() + " : " + entry.getValue())
                .reduce("", (result, current) -> result + "\n" + current);
    }


    public Dictionary<K, V> clone() {
        Dictionary<K, V> cloned = new ToyDictionary<>();
        this.forEach(cloned::put);
        return cloned;
    }
}
