package deque;

import org.junit.Test;

import static org.junit.Assert.*;

//目前没有实现equals，比较的是引用是否相同
public class MaxArrayDequeTest {
    @Test
    public void testEmptyDequeWithMax() {
        MaxArrayDeque<Integer> maxArrayDeque = new MaxArrayDeque<>(Integer::compareTo);
        assertNull(maxArrayDeque.max());
    }
    @Test
    public void testEmptyDequeWithHasComparatorMax() {
        MaxArrayDeque<Exam> maxArrayDeque = new MaxArrayDeque<>(Exam::compareTo);
        assertNull(maxArrayDeque.max(Exam.getGradeComparator()));
        assertNull(maxArrayDeque.max(Exam.getIdentityComparator()));
    }
    @Test
    public void testDifferentComparator() {
        Cat carA = new Cat("a", 2);
        Cat carB = new Cat("bc", 10);

        MaxArrayDeque<Cat> maxArrayDeque = new MaxArrayDeque<>(Cat.getNameLengthComparator());
        maxArrayDeque.addFirst(carA);
        maxArrayDeque.addFirst(carB);

        assertEquals(carB, maxArrayDeque.max());
        assertEquals(carB,maxArrayDeque.max(Cat.getNameComparator()));


        Exam exam1 = new Exam("zuana", 80.5, 44134124323L);
        Exam exam2 = new Exam("lihua", 82.5, 54134124329L);
        MaxArrayDeque<Exam> examMaxArrayDeque = new MaxArrayDeque<>(Exam.getGradeComparator());

        examMaxArrayDeque.addLast(exam1);
        examMaxArrayDeque.addLast(exam2);

        assertEquals(exam1,examMaxArrayDeque.max());
        assertEquals(exam2,examMaxArrayDeque.max(Exam.getIdentityComparator()));
    }

}