package deque;

import java.util.Comparator;

public class MaxArrayDeque<Item> extends ArrayDeque<Item> {
    private final Comparator<Item> comparator;

    public MaxArrayDeque(Comparator<Item> comparator) {
        this.comparator = comparator;
    }

    public Item max() {
        Item max = max(this.comparator);
        return max;
    }

    public Item max(Comparator<Item> comparator) {

        Item max = get(0);
        if (isEmpty() || size() == 1) {
            return max;
        }

        for (int i = 1; i < size(); i++) {
            int diff = comparator.compare(get(i), max);
            if (diff > 0) {
                max = get(i);
            }
        }
        return max;
    }

}
