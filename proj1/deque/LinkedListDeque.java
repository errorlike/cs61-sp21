package deque;

import java.util.Iterator;

/*
 * sentinel.next指向首个节点
 * sentinel.prev指向末尾节点。
 * */
public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private final ListNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    private class ListNode {
        private T t;
        private ListNode prev;
        private ListNode next;

        private ListNode(T t, ListNode prev, ListNode next) {
            this.next = next;
            this.prev = prev;
            this.t = t;
        }
    }

    @Override
    public void addFirst(T t) {
        ListNode first = new ListNode(t, sentinel, sentinel.next);
        if (sentinel.prev == null) {
            sentinel.prev = first;
        }
        if (sentinel.next != null) {
            sentinel.next.prev = first;
        }
        sentinel.next = first;
        size++;
    }

    @Override
    public void addLast(T t) {
        ListNode last = new ListNode(t, sentinel.prev, sentinel);
        if (sentinel.next == null) {
            sentinel.next = last;
        }
        if (sentinel.prev != null) {
            sentinel.prev.next = last;
        }
        sentinel.prev = last;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removed = sentinel.next.t;
        sentinel.next = sentinel.next.next;
        size--;
        return removed;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removed = sentinel.prev.t;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return removed;
    }

    @Override
    public T get(int index) {
        if (!checkIndexInRange(index)) {
            return null;
        }
        ListNode node = sentinel.next;
        int i = 0;
        while (node != null) {
            if (index == i) {
                break;
            }
            node = node.next;
            i++;
        }
        return node.t;
    }

    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(ListNode cursor, int index) {
        if (checkIndexInRange(index)) {
            if (index == 0) {
                return cursor.t;
            }
            getRecursive(cursor.next, index - 1);
        }
        return null;
    }

    private boolean checkIndexInRange(int index) {
        return index >= 0 && index < size;
    }

    @Override
    public void printDeque() {
        ListNode node = sentinel.next;
        int count = 0;
        while (true) {
            if (count == size - 1) {
                System.out.print(node.t);
                break;
            }
            System.out.print(node.t + " ");
            node = node.next;
            count++;
        }
        System.out.println();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
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
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        var that = (LinkedListDeque) o;
        if (this.size != that.size) {
            return false;
        }
        ListNode thisPoint = this.sentinel.next;
        ListNode thatPoint = that.sentinel.next;
        int count = 0;
        while (thisPoint != null && count < size) {
            if (!thisPoint.t.equals(thatPoint.t)) {
                return false;
            }
            thisPoint = thisPoint.next;
            thatPoint = thatPoint.next;
            count++;
        }
        return true;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.forEach(System.out::println);
    }
}
