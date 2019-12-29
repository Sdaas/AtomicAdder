package com.daasworld;

public class AtomicAdder {
    private Memory memory;

    public AtomicAdder(Memory memory) {
        this.memory = memory;
    }

    public void buggyAdd(int value) {
        int tmp = memory.get();
        memory.set(tmp + value);
    }

    // Uses the Compare and Set approach, as described in
    // https://en.wikipedia.org/wiki/Compare-and-swap
    public void add(int x ) {
        boolean done = false;
        while ( !done ) {
            int oldValue = memory.get();
            int newValue = oldValue + x;
            done = memory.compareAndSet(oldValue, newValue);
        }
    }
}