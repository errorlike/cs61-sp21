package deque;

/*
 * sentinel.next指向首个节点
 * sentinel.next.prev指向末尾节点。
 * */
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

    public Item removeLast() {
        if (sentinel.next.prev == null) {
            return null;
        }
        Item removed = sentinel.next.prev.item;
        sentinel.next.prev = sentinel.next.prev.prev;
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
    public void printDeque(){
        ListNode node = sentinel.next;
        while (node!=null){
            System.out.print(node.item+" ");
            if(node.next==null){
                System.out.print(node.item);
            }
        }
        System.out.println();
    }
}
