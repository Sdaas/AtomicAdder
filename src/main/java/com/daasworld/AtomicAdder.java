package com.daasworld;

public class AtomicAdder {
    private Memory memory;

    public AtomicAdder(Memory memory) {
        this.memory = memory;
    }

    // This is NOT a thread-safe way to implement an adder
    public void buggyAdd(int value) {
        int tmp = memory.get();
        memory.set(tmp + value);
    }

    // A thread-safe way to implement an adder
    // Uses a spin-lock in conjunction with compare-and-swap
    public void add(int x ) {
        boolean done = false;
        while ( !done ) {
            int oldValue = memory.get();
            int newValue = oldValue + x;
            done = memory.compareAndSet(oldValue, newValue);
        }
    }
}