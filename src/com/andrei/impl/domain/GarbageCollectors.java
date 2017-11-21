package com.andrei.impl.domain;

import java.util.Map;
import java.util.stream.Collectors;

public class GarbageCollectors {

    public static ProgramState ConservativeGarbageCollector(ProgramState state) {
        return new ProgramState(
                state.getExecutionStack(),
                state.getSymbolTable(),
                state.getOutput(),
                state.getFileTable(),
                new Heap(state.getHeap().entrySet()
                        .stream()
                        .filter(entry -> state.getSymbolTable().contains(entry.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                )
        );
    }
}
