public class LinkedListDeque<T> {

    private int size;
    /** Circular Sentinel */
    private TNode sentinel;


    private class TNode {
        private T item;
        private TNode previous;
        private TNode next;

        public TNode(TNode p, T t, TNode n) {
            previous = p;
            item = t;
            next = n;
        }
    }

    /** Creates an empty linked list deque */
//    public LinkedListDeque() {
//        size = 0;
//        sentinel  = new TNode(null, null, null);
//        sentinel.previous = sentinel;
//        sentinel.next = sentinel;
//    }

    /** Constructor */
    public LinkedListDeque(T t) {
        sentinel  = new TNode(null, null, null);
        sentinel.next = new TNode(sentinel, t, sentinel);
        sentinel.previous = sentinel.next;
        size = 1;
    }

    /** Create a deep copy of others */
//    public LinkedListDeque(LinkedListDeque other) {
//        size = other.size();
//        sentinel  = new TNode(null, null, null);
//        sentinel.previous = sentinel;
//        sentinel.next = sentinel;
//        TNode p = other.sentinel.next;
//        TNode q = sentinel;
//        for (int i = 0; i <= size - 1; i++) {
//            q.next = new TNode(q, p.item, sentinel);
//            sentinel.previous  = q.next;
//            p = p.next;
//            q = q.next;
//        }
//
//    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T t) {
        size += 1;
        sentinel.next = new TNode(sentinel, t, sentinel.next);
        sentinel.next.next.previous = sentinel.next;

    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T t) {
        size += 1;
        sentinel.previous.next = new TNode(sentinel.previous, t, sentinel);
        sentinel.previous = sentinel.previous.next;

    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /**  Prints the items in the deque from first to last,
     * separated by a space.
     * Once all the items have been printed, print out a new line.*/
    public void printDeque() {
        TNode p = sentinel;
        for (int i = 0; i <= size - 1; i++) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println(); // print out a new line

    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        TNode p = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;
        size -= 1;
        return p.item;

    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        TNode p = sentinel.previous;
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;
        size -= 1;
        return p.item;

    }

    /** Gets the item at the given index,
     * where 0 is the front,
     * 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        TNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    /** Same as get, but uses recursion */
    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, TNode p) {
        if (index == 0) {
            return p.item;
        }
        p = p.next;
        return getRecursiveHelper(index - 1, p);
    }
    
}
