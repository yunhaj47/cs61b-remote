public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int INIT_CAPACITY = 8;
    private static final int RFACTOR = 2;
    private static final double MIN_USAGE_RATIO = 0.25;

    /** Contructor
     * Creates an empty array deque.
     * The starting size of array is 8. */
    public ArrayDeque() {
        items = (T[]) new Object[INIT_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }


    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];

        int curr = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            a[i] = items[curr];
            curr = plusOne(curr);
        }
        items = a;
        nextFirst = capacity - 1;
        nextLast = size;

    }

    private int minusOne(int index) {
        return (index + items.length - 1) % items.length;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    /** Adds an item of type T to the front of the deque */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (size  == items.length) {
            resize(size * RFACTOR);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = plusOne(nextLast);

    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque.
     * Must take constant time. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last,
     * separated by a space.
     * Once all the items have been printed,
     * print out a new line. */
    public void printDeque() {
        int curr = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[curr] + " ");
            curr = plusOne(curr);
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.*/
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int first = plusOne(nextFirst);
        T firstItem = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;

        if (items.length >= 16 && size < items.length * MIN_USAGE_RATIO) {
            resize(items.length / 2);
        }

        return firstItem;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int last = minusOne(nextLast);
        T lastItem = items[last];
        items[last] = null;
        nextLast = last; // 这句是否多余
        size -= 1;

        if (items.length >= 16 && size < items.length * MIN_USAGE_RATIO) {
            resize(items.length / 2);
        }

        return lastItem;
    }

    /** Gets the item at the given index,
     * must take constant time */
    public T get(int index) {
        if (index > size) {
            return null;
        }
        int first = plusOne(nextFirst);
        return items[(first + index) % items.length];
    }

    /** Creating a deep copy means that
     * you create an entirely new ArrayDeque,
     * with the exact same items as other.*/
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[INIT_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

}
