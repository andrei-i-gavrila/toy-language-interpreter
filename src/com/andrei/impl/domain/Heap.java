package com.andrei.impl.domain;

import com.andrei.impl.domain.exceptions.HeapAddressNotFoundException;
import com.andrei.interfaces.domain.IHeap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Heap implements IHeap {
    public static final Integer NULL = 0;
    Map<Integer, Integer> heap;
    private Integer LAST_ADDRESS = 1;

    public Heap() {
        this.heap = new HashMap<>();
    }

    public Heap(Map<Integer, Integer> heap) {
        this.heap = heap;
        LAST_ADDRESS = heap.keySet().stream().max((o1, o2) -> o1 > o2 ? o1 : o2).orElse(0) + 1;
    }

    @Override
    public Integer allocate() {
        Integer usedAddress = LAST_ADDRESS++;
        heap.put(usedAddress, NULL);

        return usedAddress;
    }

    @Override
    public void write(Integer address, Integer value) throws HeapAddressNotFoundException {
        if (!heap.containsKey(address)) {
            throw new HeapAddressNotFoundException(address);
        }
        heap.put(address, value);
    }

    @Override
    public Integer read(Integer address) throws HeapAddressNotFoundException {
        if (!heap.containsKey(address)) {
            throw new HeapAddressNotFoundException(address);
        }
        return heap.get(address);
    }

    @Override
    public Set<Map.Entry<Integer, Integer>> entrySet() {
        return heap.entrySet();
    }

    @Override
    public String toString() {
        return heap.entrySet().stream()
                .map(entry -> entry.getKey() + " : " + entry.getValue())
                .reduce((result, current) -> result + "\n" + current)
                .orElse("Empty");
    }
}
