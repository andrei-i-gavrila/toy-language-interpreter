package com.andrei.impl.domain;

import com.andrei.impl.domain.exceptions.HeapAddressNotFoundException;
import com.andrei.interfaces.domain.IHeap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class Heap implements IHeap {
    public static final Integer NULL = 0;
    Map<Integer, Integer> heap;
    private final AtomicReference<Integer> LAST_ADDRESS = new AtomicReference<>(1);

    public Heap() {
        this.heap = new HashMap<>();
    }

    public Heap(Map<Integer, Integer> heap) {
        this.heap = heap;
        LAST_ADDRESS.set(heap.keySet().stream().max((o1, o2) -> o1 > o2 ? o1 : o2).orElse(0) + 1);
    }

    @Override
    public Integer allocate() {
        Integer usedAddress = LAST_ADDRESS.getAndSet(LAST_ADDRESS.get() + 1);
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
