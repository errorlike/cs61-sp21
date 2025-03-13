package flik;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FlikTest {
    @Test
    public void testTwoIntegerObject() {
        Integer a = 1;
        Integer b = 1;
        assertTrue(Flik.isSameNumber(a, b));
    }

    @Test
    public void testTwoInterNumber() {
        int a = 1;
        int b = 1;
        assertTrue(Flik.isSameNumber(a, b));
    }

    @Test
    public void testTwoDiffObject() {
        Integer a = 1;
        Integer b = 2;
        assertFalse(Flik.isSameNumber(a, b));
    }

    @Test
    public void testTwoDiffPrimal() {
        int a = 1;
        int b = 2;
        assertFalse(Flik.isSameNumber(a,b));
    }
}