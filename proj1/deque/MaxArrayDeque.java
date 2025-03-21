package deque;

import java.util.Comparator;

public class MaxArrayDeque<Item> extends ArrayDeque<Item> {
    private final Comparator<Item> comparator;

    public MaxArrayDeque(Comparator<Item> comparator) {
        this.comparator = comparator;
    }

    public Item max() {
        Item max = get(0);
        if (isEmpty() || size() == 1) {
            return max;
        }

        for (int i = 0; i < size(); i++) {
            int diff = comparator.compare(get(i), max);
            if (diff > 0) {
                max = get(0);
            }
        }
        return max;
    }

    public Item max(Comparator<Item> comparator) {
        Item max = get(0);
        if (isEmpty() || size() == 1) {
            return max;
        }
        for (int i = 0; i < size(); i++) {
            Item current = get(i);
            int diff = comparator.compare(current, max);
            if (diff > 0) {
                max = current;
            }
        }
        return max;
    }

}
