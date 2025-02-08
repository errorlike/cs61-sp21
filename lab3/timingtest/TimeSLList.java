package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
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
        // TODO: YOUR CODE HERE
        SLList<Integer> sList = new SLList<>();

        int j = 0;
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int M = 10000;

        for (int i = 1; i <= 128000; i++) {
            sList.addLast(i);
            if (i == (int) Math.pow(2, j) * 1000) {
                Stopwatch stopwatch = new Stopwatch();
                for (int k = 0; k < M; k++) {
                    sList.getLast();
                }
                double executedTimes = stopwatch.elapsedTime();

                Ns.addLast(i);
                times.addLast(executedTimes);
                opCounts.addLast(M);
                j++;
            }
        }
        ;
        printTimingTable(Ns, times, opCounts);
    }

}
