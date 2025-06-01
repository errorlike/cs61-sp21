package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public T max() {
        T max = max(this.comparator);
        return max;
    }

    public T max(Comparator<T> provideComparator) {

        T max = get(0);
        if (isEmpty() || size() == 1) {
            return max;
        }

        for (int i = 1; i < size(); i++) {
            int diff = provideComparator.compare(get(i), max);
            if (diff > 0) {
                max = get(i);
            }
        }
        return max;
    }

}
