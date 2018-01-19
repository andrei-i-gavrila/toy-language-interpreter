package com.andrei.interfaces.domain;

import java.util.Map;

public interface IDictionary<K, V> extends Map<K, V>, Cloneable {
    IDictionary<K, V> clone();
}