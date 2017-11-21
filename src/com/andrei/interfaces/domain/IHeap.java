package com.andrei.interfaces.domain;

import com.andrei.impl.domain.exceptions.HeapAddressNotFoundException;

import java.util.Map;
import java.util.Set;

public interface IHeap {

    Integer allocate();

    void write(Integer address, Integer value) throws HeapAddressNotFoundException;

    Integer read(Integer address) throws HeapAddressNotFoundException;

    Set<Map.Entry<Integer, Integer>> entrySet();

}
