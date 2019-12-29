
import com.daasworld.Memory;

import static org.junit.Assert.*;
import org.junit.*;

public class MemoryTest {
    private Memory m;

    @Before
    public void setup() {
        m = new Memory();
    }

    @Test
    public void initialValueTest() {
        assertEquals("value should have been zero,", 0, m.get());
    }

    @Test
    public void simpleSetTest() {
        int value = 100;
        m.set(value);
        assertEquals("value should have been " + value, value, m.get());
    }

    @Test
    public void compareAndSetShouldPass() {
        int oldValue = 100;
        int newValue = 200;
        m.set(oldValue);
        assertTrue("should have returned true", m.compareAndSet(oldValue, newValue));
        assertEquals("value should have been " + newValue, newValue, m.get());
    }

    @Test
    public void compareAndSetShouldFail() {
        int oldValue = 100;
        int newValue = 200;
        m.set(oldValue);
        assertFalse("should have returned false", m.compareAndSet(0, newValue));
        assertEquals("value should have been " + oldValue, oldValue, m.get());
    }
}
