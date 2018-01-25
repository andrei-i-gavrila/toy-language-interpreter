package com.andrei.impl.domain;

import com.andrei.impl.utils.RandomSequenceProvider;
import com.andrei.interfaces.domain.BarrierTable;
import com.andrei.interfaces.domain.Dictionary;
import com.andrei.interfaces.utils.NumberSequenceProvider;

public class ToyBarrierTable implements BarrierTable {

    final private Dictionary<Integer, ToyBarrier> barriers;
    final private NumberSequenceProvider sequenceProvider = new RandomSequenceProvider();

    public ToyBarrierTable() {
        barriers = new ToyDictionary<>();
    }

    public Integer createBarrier(int limit) {
        Integer usedAddress = sequenceProvider.next();
        barriers.put(usedAddress, new ToyBarrier(limit));

        return usedAddress;
    }

    public Dictionary<Integer, ToyBarrier> getBarriers() {
        return barriers;
    }
}
