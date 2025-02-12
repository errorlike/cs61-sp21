package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> expected = new AListNoResizing<>();
        BuggyAList<Integer> actual = new BuggyAList<>();

        expected.addLast(1);
        expected.addLast(2);
        expected.addLast(3);

        actual.addLast(1);
        actual.addLast(2);
        actual.addLast(3);

        assertEquals(expected.removeLast(), actual.removeLast());
        assertEquals(expected.removeLast(), actual.removeLast());
        assertEquals(expected.removeLast(), actual.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();
        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int bSize = B.size();
                assertEquals(size, bSize);
            } else if (operationNumber == 2) {
                if (L.size() == 0) {
                    continue;
                }
                assertEquals(L.getLast(), B.getLast());

            } else if (operationNumber == 3) {
                if (L.size() == 0) {
                    continue;
                }
                assertEquals(L.removeLast(), B.removeLast());
            }

        }
    }
}
