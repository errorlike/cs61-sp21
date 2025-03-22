package deque;

//1.数组始终从0开始
//2. 数组满的时候，nextFirst指向end,nextLast指向start
public class ArrayDeque<Item> implements Deque<Item> {
    private int size;
    private Item[] items;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
    }
    @Override
    public void addFirst(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (isEmpty()) {
            nextLast++;
        }
        items[nextFirst] = item;
        nextFirst = nextFirst - 1 >= 0 ? nextFirst - 1 : items.length - 1;
        size++;
    }
    @Override
    public void addLast(Item item) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (isEmpty()) {
            nextFirst= items.length-1;
        }
        items[nextLast] = item;
        nextLast = nextLast + 1 != items.length ? nextLast + 1 : 0;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int cursor = getFirstIndex();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                System.out.print(items[cursor]);
            } else {
                System.out.print(items[cursor] + " ");
            }
            cursor = cursor + 1 != items.length ? cursor + 1 : 0;
        }
            System.out.println();
    }

    @Override
    public Item removeFirst() {
        if(isEmpty()){
            return null;
        }
        if (size >= 16 && size < items.length / 4) {
            resize(items.length / 4);
        }
        int first =getFirstIndex();
        Item removed = items[first];
        items[first] = null;
        size--;
        nextFirst = first;
        return removed;
    }

    @Override
    public Item removeLast() {
        if(isEmpty()){
            return null;
        }
        if (size >= 16 && size < items.length / 4) {
            resize(items.length / 4);
        }
        int last = getLastIndex();
        Item removed = items[last];
        items[last] = null;
        size--;
        nextLast = last;
        return removed;
    }

    private void resize(int capacity) {
        items = copyArray(capacity);
        nextFirst = items.length - 1;
        nextLast = size;
    }

    private Item[] copyArray(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];

        int cursor = getFirstIndex();
        for (int i = 0; i < size; i++) {
            if (cursor == size) {
                cursor = 0;
            }
            newArray[i] = items[cursor];
            cursor++;
        }
        return newArray;
    }

    @Override
    public Item get(int index) {
        int calculated = (getFirstIndex()+index)% items.length;
        return items[calculated];
    }

    private int getFirstIndex() {
        return nextFirst + 1 != items.length ? nextFirst + 1 : 0;
    }

    private int getLastIndex() {
        return nextLast - 1 >= 0 ? nextLast - 1 : size - 1;
    }
}
