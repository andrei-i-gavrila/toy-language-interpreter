package com.andrei.interfaces.domain;

import com.andrei.impl.domain.ToyBarrier;

public interface BarrierTable {

    Integer createBarrier(int limit);

    Dictionary<Integer, ToyBarrier> getBarriers();
}
