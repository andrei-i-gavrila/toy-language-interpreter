package com.andrei.impl.domain;

import com.andrei.impl.domain.exceptions.HeapAddressNotFoundException;
import com.andrei.impl.utils.RandomSequenceProvider;
import com.andrei.interfaces.domain.IHeap;
import com.andrei.interfaces.utils.NumberSequenceProvider;

import java.util.Map;
import java.util.Set;

public class Heap implements IHeap {

    public static final Integer NULL = 0;
    private final Map<Integer, Integer> heap;
    private final NumberSequenceProvider addressProvider;

    public Heap() {
        this.heap = new ToyDictionary<>();
        this.addressProvider = new RandomSequenceProvider();
    }

    public Integer allocate() {
        Integer usedAddress = addressProvider.next();
        heap.put(usedAddress, NULL);

        return usedAddress;
    }

    public void write(Integer address, Integer value) throws HeapAddressNotFoundException {
        if (!heap.containsKey(address)) {
            throw new HeapAddressNotFoundException(address);
        }
        heap.put(address, value);
    }

    public Integer read(Integer address) throws HeapAddressNotFoundException {
        if (!heap.containsKey(address)) {
            throw new HeapAddressNotFoundException(address);
        }
        return heap.get(address);
    }

    public Set<Map.Entry<Integer, Integer>> entrySet() {
        return heap.entrySet();
    }

    public String toString() {
        return heap.toString();
    }
}
