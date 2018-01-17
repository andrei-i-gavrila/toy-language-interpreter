package com.andrei.impl.domain;

import com.andrei.interfaces.domain.IDictionary;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dictionary<K, V> implements IDictionary<K, V> {

    private final Map<K, V> map;

    public Dictionary() {
        this.map = new HashMap<>();
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public boolean has(K key) {
        return map.containsKey(key);
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public void remove(K key) {
        map.remove(key);
    }

    @Override
    public boolean contains(V value) {
        return map.containsValue(value);
    }

    @Override
    public String toString() {
        return map.entrySet().stream()
                .map(entry -> entry.getKey() + " : " + entry.getValue())
                .reduce((result, current) -> result + "\n" + current)
                .orElse("Empty");
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return map.entrySet();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> valueSet() {
        return map.values();
    }

    @Override
    public IDictionary<K, V> clone() {
        IDictionary<K, V> cloned = new Dictionary<>();
        map.forEach(cloned::put);
        return cloned;
    }
}
