package deque;

public class ArrayDeque<Item> {
    int size;
    private Item[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
    }

    public void addFirst(Item item) {
        items[nextFirst] = item;
    }

    public void addLast(Item item) {
        items[nextLast] = item;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
            if (i == size - 1) {
                System.out.print(items[i]);
            }
            System.out.println();
        }
    }

    //循环和时钟类似，利用余数来查找位置
    public Item removeFirst() {
        int first = (nextFirst + 1) % 8;
        Item removed = items[first];
        items[first] = null;
        size--;
        return removed;
    }

    public Item removeLast() {
        int last = (nextLast - 1 + items.length) % 8;
        Item removed = items[last];
        items[last] = null;
        size--;
        return removed;
    }
}
