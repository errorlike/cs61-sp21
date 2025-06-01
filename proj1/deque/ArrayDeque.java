package deque;

import java.util.Iterator;

//1.数组始终从0开始
//2. 数组满的时候，nextFirst指向end,nextLast指向start
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private static final int RESIZE_FACTOR = 16;
    private int size;
    private T[] ts;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        ts = (T[]) new Object[8];
    }

    @Override
    public void addFirst(T t) {
        if (size == ts.length) {
            resize(size * 2);
        }
        if (isEmpty()) {
            nextLast++;
        }
        ts[nextFirst] = t;
        nextFirst = nextFirst - 1 >= 0 ? nextFirst - 1 : ts.length - 1;
        size++;
    }

    @Override
    public void addLast(T t) {
        if (size == ts.length) {
            resize(size * 2);
        }
        if (isEmpty()) {
            nextFirst = ts.length - 1;
        }
        ts[nextLast] = t;
        nextLast = nextLast + 1 != ts.length ? nextLast + 1 : 0;
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
                System.out.print(ts[cursor]);
            } else {
                System.out.print(ts[cursor] + " ");
            }
            cursor = cursor + 1 != ts.length ? cursor + 1 : 0;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size >= RESIZE_FACTOR && size < ts.length / 4) {
            resize(ts.length / 4);
        }
        int first = getFirstIndex();
        T removed = ts[first];
        ts[first] = null;
        size--;
        if (size == 0) {
            nextLast = 0;
        }
        nextFirst = first;
        return removed;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size >= RESIZE_FACTOR && size < ts.length / 4) {
            resize(ts.length / 4);
        }
        int last = getLastIndex();
        T removed = ts[last];
        ts[last] = null;
        size--;
        if (size == 0) {
            nextFirst = 0;
        }
        nextLast = last;
        return removed;
    }

    private void resize(int capacity) {
        ts = copyArray(capacity);
        nextFirst = ts.length - 1;
        nextLast = size;
    }

    private T[] copyArray(int capacity) {
        T[] newArray = (T[]) new Object[capacity];

        int cursor = getFirstIndex();
        for (int i = 0; i < size; i++) {
            if (cursor == size) {
                cursor = 0;
            }
            newArray[i] = ts[cursor];
            cursor++;
        }
        return newArray;
    }

    @Override
    public T get(int index) {
        int calculated = (getFirstIndex() + index) % ts.length;
        return ts[calculated];
    }

    private int getFirstIndex() {
        return nextFirst + 1 != ts.length ? nextFirst + 1 : 0;
    }

    private int getLastIndex() {
        return nextLast - 1 >= 0 ? nextLast - 1 : size - 1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            T t = get(cursor);
            cursor++;
            return t;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<?> that = (ArrayDeque) o;
        if (this.size != that.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(that.get(i))) {
                return false;
            }
        }
        return true;
    }

}
