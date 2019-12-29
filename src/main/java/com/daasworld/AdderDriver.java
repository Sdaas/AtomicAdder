package com.daasworld;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class AdderDriver extends Thread {
    private AtomicAdder adder;
    private CountDownLatch latch;

    public AdderDriver(AtomicAdder adder, CountDownLatch latch) {
        this.adder = adder;
        this.latch = latch;
    }

    public void run() {
        // do the work;@
        for(int i=0; i<1000; i++) {
            //adder.buggyAdd(1);
            adder.add(1);
        }
        // signal that the work is done
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {

        int threadCount = 10;
        Memory memory = new Memory();
        AtomicAdder adder = new AtomicAdder(memory);
        CountDownLatch latch = new CountDownLatch(threadCount);

        ArrayList<AdderDriver> drivers = new ArrayList<AdderDriver>();
        for(int i=0; i<threadCount; i++) {
            drivers.add( new AdderDriver(adder, latch));
        }

        // Start each of the threads and wait for all the threads to be done.
        for(AdderDriver d : drivers) {
            d.start();
        }
       latch.await();

        // Print out the value of the adder ..
        System.out.println("The value of the adder is " + memory.get());
    }
}
