package deque;

public interface Deque<T> {

    void addFirst(T t);

    void addLast(T t);

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int index);
}
