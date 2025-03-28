package deque;

import java.util.Comparator;

public class Exam implements Comparable<Exam> {
    private String studentName;
    private double grade;
    private long identityNumber;

    public Exam(String studentName, double grade, long identityNumber) {
        this.studentName = studentName;
        this.grade = grade;
        this.identityNumber = identityNumber;
    }

    @Override
    public int compareTo(Exam o) {
        return this.studentName.compareTo(o.studentName);
    }

    private static class IdentityNumberComparator implements Comparator<Exam> {

        @Override
        public int compare(Exam o1, Exam o2) {
            return Long.compare(o1.identityNumber, o2.identityNumber);
        }
    }

    private static class GradeComparater implements Comparator<Exam> {

        @Override
        public int compare(Exam o1, Exam o2) {
            return Double.compare(o1.grade, o2.grade);
        }
    }

    public static Comparator<Exam> getIdentityComparator() {
        return new IdentityNumberComparator();
    }

    public static Comparator<Exam> getGradeComparator() {
        return new GradeComparater();
    }
}
