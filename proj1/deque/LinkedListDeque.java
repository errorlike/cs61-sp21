package deque;

/*
 * sentinel.next指向首个节点
 * sentinel.prev指向末尾节点。
 * */
public class LinkedListDeque<Item> implements Deque<Item> {
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

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item removed = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size--;
        return removed;
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item removed = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return removed;
    }

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
}
