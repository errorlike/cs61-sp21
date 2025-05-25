package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testThreeAddThreeRemove() {
        ArrayList<Integer> expected = new ArrayList<>();
        ArrayDeque<Integer> actual = new ArrayDeque<>();

        expected.add(1);
        expected.add(2);
        expected.add(3);

        actual.addLast(1);
        actual.addLast(2);
        actual.addLast(3);

        assertEquals(expected.remove(expected.size() - 1), actual.removeLast());
        assertEquals(expected.remove(expected.size() - 1), actual.removeLast());
        assertEquals(expected.remove(expected.size() - 1), actual.removeLast());
    }
    @Test
    public void addOnlyOne() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addFirst(5);
        list.addLast(1);
        assertEquals(5, (int) list.get(0));
    }
    @Test
    public void testTreeAddFirstThreeRemoveFirst() {
        ArrayList<Integer> expected = new ArrayList<>();
        ArrayDeque<Integer> actual = new ArrayDeque<>();

        expected.add(0, 1);
        expected.add(0, 2);
        expected.add(0, 3);

        actual.addFirst(1);
        actual.addFirst(2);
        actual.addFirst(3);

        assertEquals(expected.remove(0), actual.removeFirst());
        assertEquals(expected.remove(0), actual.removeFirst());
        assertEquals(expected.remove(0), actual.removeFirst());
    }

    @Test
    public void randomizedTest() {
        ArrayList<Integer> L = new ArrayList<>();
        ArrayDeque<Integer> B = new ArrayDeque<>();
        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.add(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int bSize = B.size();
                assertEquals(size, bSize);
            } else if (operationNumber == 2) {
                if (L.isEmpty()) {
                    continue;
                }
                assertEquals(L.get(L.size() - 1), B.get(B.size() - 1));

            } else if (operationNumber == 3) {
                if (L.isEmpty()) {
                    continue;
                }
                assertEquals(L.remove(L.size() - 1), B.removeLast());
            }

        }
    }

    @Test
    public void addResize() {
        ArrayDeque<Integer> actual = new ArrayDeque<>();
        for (int i = 0; i < 18; i++) {
            actual.addFirst(i);
        }
        for (int i = 18; i < 40; i++) {
            actual.addLast(i);
        }
        actual.printDeque();
    }

    @Test
    public void removeToNeg() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.removeLast();
        list.removeFirst();
    }
    @Test
    public  void testArrayIsEmptyAfterAddingAndDeletingItem(){
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addFirst(1);
        list.removeFirst();
        list.addLast(1);
        assertNotNull(list.get(0));
    }
    @Test
    public  void testArrayIsEmptyAfterAddingAndDeletingItemReverse(){
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addLast(1);
        list.removeLast();
        list.addFirst(1);
        assertNotNull(list.get(0));
    }
}
