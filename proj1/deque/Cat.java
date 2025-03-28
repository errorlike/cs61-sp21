package deque;

import java.util.Comparator;

public class Cat implements Comparable<Cat> {
    private String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Cat other) {
        return age - other.age;
    }

    private static class NameComparator implements Comparator<Cat> {

        @Override
        public int compare(Cat o1, Cat o2) {
            return o1.name.length() - o2.name.length();
        }
    }

    public static Comparator<Cat> getNameComparator() {
        return new NameComparator();
    }

    private static class NameLengthComparator implements Comparator<Cat> {
        @Override
        public int compare(Cat o1, Cat o2) {
            return o1.compareTo(o2);
        }
    }

    public static Comparator<Cat> getNameLengthComparator() {
        return new NameLengthComparator();
    }
}
