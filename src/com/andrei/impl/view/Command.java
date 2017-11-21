package com.andrei.impl.view;

import java.util.Scanner;

public abstract class Command implements Runnable {
    protected static final Scanner scanner = new Scanner(System.in);

    protected final String key;
    protected final String description;

    public Command(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getKey() + ": " + getDescription();
    }

    public abstract void run();
}
