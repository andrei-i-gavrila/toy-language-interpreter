package com.andrei.interfaces.domain;

import java.util.Map;

public interface Dictionary<K, V> extends Map<K, V>, Cloneable {
    Dictionary<K, V> clone();
}