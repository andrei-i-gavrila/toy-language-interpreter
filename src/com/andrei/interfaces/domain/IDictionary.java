package com.andrei.interfaces.domain;

import java.util.Map;
import java.util.Set;

public interface IDictionary<K, V> {

    V get(K key);

    boolean has(K key);

    void put(K key, V value);

    void remove(K key);

    boolean contains(V value);

    Set<Map.Entry<K, V>> entrySet();
}