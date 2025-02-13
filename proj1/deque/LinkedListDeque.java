package deque;

public class LinkedListDeque<Item> {
    private ListNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
    }

    private class ListNode {
        public Item item;
        public ListNode prev;
        public ListNode next;

        public ListNode(Item item, ListNode prev, ListNode next) {
            this.next = next;
            this.prev = prev;
            this.item = item;
        }
    }

    public void addFirst(Item item) {
        ListNode start = sentinel.next;
        ListNode newStart = new ListNode(item, start, sentinel.next);
        start.prev = sentinel.next;
        sentinel.next = newStart;
        size++;
    }

    public void addLast(Item item) {
        ListNode last = sentinel.next.prev;
        ListNode newLast = new ListNode(item, last, sentinel.next);
        last.next = newLast;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Item removeFirst() {
        if (sentinel.next == null) {
            return null;
        }
        Item removed = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size--;
        return removed;
    }

}
