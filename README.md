## Overview

This simple application shows how "Compare-and-Swap" can be used along with a spin-lock to implement an atomic adder.

When writing to a memory location, the writer also provides the last read value. As part of the compare-and-swap operation (and its cousin compare-and-set), the current content of the memory location is compared against the previous value and the write succeeds only if they are equal. This approach ensures that the value has not been updated by some other writer in the interim, as shown below :
```
memory = 123
Thread 1 : memory.read() // returns 123
Thread 2 : memory.read() // returns 123
Thread 1 : memory.write(old = 123, new = 456) // old value matches. write succeeds
Thread 2 : memory.write(old = 123, new = 789) // old value does NOT match. write fails
```

## Important Files

* `Memory.java` : A data storage that implements compare-and-swap
* `AtomicAdder.java` : Adds value to the data storage
* `AdderDriver.java` : Sets up multiple threads that call the `AtomicAdder`

## Instructions

* `mvn clean`
* `mvn install`
* `export CLASSPATH="target/AtomicAdder-1.0-SNAPSHOT.jar"`
* `java com.daasworld.AdderDriver`

## References

* Wikipedia article on [Compare-And-Swap](https://en.wikipedia.org/wiki/Compare-and-swap)
* [Markdown syntax](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)