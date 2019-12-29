package com.daasworld;

public class Memory {
    private int value = 0;

    public int get() {
        return value;
    }

    public void set(int value) {
       this.value = value;
    }

    public boolean compareAndSet(int oldValue, int newValue) {
        synchronized (this) {
            if(value == oldValue) {
                value = newValue;
                return true;
            } else {
                return false;
            }
        }
    }
}
