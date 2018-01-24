package com.andrei.impl.domain.exceptions;

public class HeapAddressNotFoundException extends ToyException {

    public HeapAddressNotFoundException(Integer address) {
        super("Address " + address.toString() + " is not allocated");
    }
}
