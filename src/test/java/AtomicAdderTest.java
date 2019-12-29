
import com.daasworld.AtomicAdder;
import com.daasworld.Memory;

import static org.junit.Assert.*;
import org.junit.*;
import static org.mockito.Mockito.*;


public class AtomicAdderTest {
    private Memory memory;
    private AtomicAdder adder;

    @Before
    public void setup() {
        memory = mock(Memory.class);
        adder = new AtomicAdder(memory);
    }

    @Test
    public void buggyAddTest() {
        // set the memory to 100 and then add 50 to it.
        // Finally verify that it memory.set was called with 150
       when(memory.get()).thenReturn(100);
       adder.buggyAdd(50);
       verify(memory).set(150); // verify that memory.set(150) was called/
    }

    @Test
    public void addTest() {
        // set the memory to 100 and then add 50 to it.
        // Finally verify that it memory.set was called with 150
        when(memory.get()).thenReturn(100);
        when(memory.compareAndSet(100,150)).thenReturn(true);

        adder.add(50);
        verify(memory).compareAndSet(100, 150);
    }

    @Test
    public void addTestWithSpinLock() {
        // trying to exercise the spin lock ...
        // set the memory to 100 and then add 50 to it.
        // on the first two invocations the compareAndSet() will fail ...
        // Finally verify that it memory.set was called with 150
        when(memory.get()).thenReturn(100);
        when(memory.compareAndSet(100,150)).thenReturn(false, false, true);

        adder.add(50);

        verify(memory, times(3)).compareAndSet(100, 150);
    }
}
