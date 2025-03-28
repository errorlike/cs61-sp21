package deque;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;

public class TimeLinkedListDeque {

    private static void printTimingTable(ArrayList<Integer> Ns, ArrayList<Double> times, ArrayList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();

        int j = 0;
        ArrayList<Integer> Ns = new ArrayList<>();
        ArrayList<Double> times = new ArrayList<>();
        ArrayList<Integer> opCounts = new ArrayList<>();
        int M = 10000;

        for (int i = 1; i <= 128000; i++) {
            deque.addLast(i);
            if (i == (int) Math.pow(2, j) * 1000) {
                Stopwatch stopwatch = new Stopwatch();
                for (int k = 0; k < M; k++) {
                    deque.get(deque.size() - 1);
                }
                double executedTimes = stopwatch.elapsedTime();

                Ns.add(i);
                times.add(executedTimes);
                opCounts.add(M);
                j++;
            }
        }
        printTimingTable(Ns, times, opCounts);
    }
}
