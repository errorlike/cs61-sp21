package deque;

import jh61b.junit.In;

import java.util.Iterator;

/*
 * sentinel.next指向首个节点
 * sentinel.prev指向末尾节点。
 * */
public class LinkedListDeque<Item> implements Deque<Item>, Iterable<Item> {
    private final ListNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    private class ListNode {
        private Item item;
        private ListNode prev;
        private ListNode next;

        public ListNode(Item item, ListNode prev, ListNode next) {
            this.next = next;
            this.prev = prev;
            this.item = item;
        }
    }

    @Override
    public void addFirst(Item item) {
        ListNode first = new ListNode(item, sentinel, sentinel.next);
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
    public void addLast(Item item) {
        ListNode last = new ListNode(item, sentinel.prev, sentinel);
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
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item removed = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size--;
        return removed;
    }

    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item removed = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return removed;
    }

    @Override
    public Item get(int index) {
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
        return node.item;
    }

    public Item getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }

    private Item getRecursive(ListNode cursor, int index) {
        if (checkIndexInRange(index)) {
            if (index == 0) {
                return cursor.item;
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
                System.out.print(node.item);
                break;
            }
            System.out.print(node.item + " ");
            node = node.next;
            count++;
        }
        System.out.println();
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<Item> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public Item next() {
            Item item = get(cursor);
            cursor++;
            return item;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LinkedListDeque )) {
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
            if (!thisPoint.item.equals(thatPoint.item)) {
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
