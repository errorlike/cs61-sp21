package deque;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;

public class TimeArrayDeque {
    private static void printTimingTable(ArrayList<Integer> ns,
                                         ArrayList<Double> times,
                                         ArrayList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < ns.size(); i += 1) {
            int N = ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeArrayDequeConstruction();
    }

    public static void timeArrayDequeConstruction() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        ArrayList<Integer> ns = new ArrayList<>();
        ArrayList<Double> times = new ArrayList<>();
        ArrayList<Integer> opCounts = new ArrayList<>();
        Stopwatch stopwatch = new Stopwatch();
        int j = 0;
        for (int i = 1; i <= 128000; i++) {
            list.addLast(i);
            if (i == Math.pow(2, j) * 1000) {
                double elapsedSeconds = stopwatch.elapsedTime();
                ns.add(i);
                times.add(elapsedSeconds);
                opCounts.add(i);
                j++;
            }

        }
        printTimingTable(ns, times, opCounts);
    }
}
