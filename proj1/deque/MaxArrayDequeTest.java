package deque;

import org.junit.Test;

import static org.junit.Assert.*;


public class MaxArrayDequeTest {
    @Test
    public void testEmptyDequeWithMax() {
        MaxArrayDeque<Integer> maxArrayDeque = new MaxArrayDeque<>(Integer::compareTo);
        assertNull(maxArrayDeque.max());
    }

    @Test
    public void testDifferentComparator() {
        Cat carA = new Cat("a", 2);
        Cat carB = new Cat("b", 10);

        MaxArrayDeque<Cat> maxArrayDeque = new MaxArrayDeque<>(Cat::compareTo);

        maxArrayDeque.addFirst(carA);
        maxArrayDeque.addFirst(carB);

        assertEquals(carB, maxArrayDeque.max());
        assertEquals(carB,maxArrayDeque.max(Cat.getNameComparator()));
    }
}