package deque;

//1.数组始终从0开始
//2. 数组满的时候，nextFirst指向end,nextLast指向start
public class ArrayDeque<Item> {
    int size;
    private Item[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
    }

    public void addFirst(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = nextFirst - 1 >= 0 ? nextFirst - 1 : size - 1;
        size++;
    }

    public void addLast(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = nextLast + 1 != size ? nextLast + 1 : 0;
        size++;
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

    public Item removeFirst() {
        if (size >= 16 && size < items.length / 4) {
            resize(items.length / 4);
        }
        int first = nextFirst + 1 != size ? nextFirst + 1 : 0;
        Item removed = items[first];
        items[first] = null;
        size--;
        return removed;
    }

    public Item removeLast() {
        if (size >= 16 && size < items.length / 4) {
            resize(items.length / 4);
        }
        int last = nextLast - 1 >= 0 ? nextLast - 1 : size - 1;
        Item removed = items[last];
        items[last] = null;
        size--;
        return removed;
    }

    private void resize(int capacity) {
        items = copyArray(capacity);
        nextLast = size;
        nextFirst = nextLast;
    }

    private Item[] copyArray(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        int cursor = nextLast;
        for (int i = 0; i < size; i++) {
            if (cursor == size) {
                cursor = 0;
            }
            newArray[i] = items[cursor];
            cursor++;
        }
        return newArray;
    }

    public Item get(int index) {
        return items[index];
    }
}
