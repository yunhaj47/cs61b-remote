public class SLList {

    /** if you don't use any instance members of the outer class,
     * make the nested class static*/
    public static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }

    }

    private IntNode sentinel;
    private IntNode last;
    private int size;


    public  SLList() {
        sentinel = new IntNode(63, null); //头节点的item是任意的
        last = sentinel;
        size  = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x, null);
        last = sentinel.next;
        size = 1;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        size += 1;
        sentinel.next = new IntNode(x, sentinel.next);
    }

    /** Retrieves the front item from the list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Adds an item to the end of the list. */
//    public void addLast(int x) {
//        size += 1;
//        IntNode p = sentinel;
//        while (p.next != null) { // can cause a null pointer exception if first=null
//            p = p.next;
//        }
//        p.next = new IntNode(x, null);
//    }
    public void addLast(int x) {
        size += 1;
        last.next = new IntNode(x, null);
        last = last.next;
    }
    /** Retrives the back item from the list. */
    public int getLast() {
        return last.item;
    }

//    public static int size(IntNode p) {
//        if (p.next == null) return 1;
//        return 1 + size(p.next);
//    }

//    public int size() {
//        return size (first);
//    }

    public int size() {
        return size;
    }

    public  static void main(String arg[]) {
        SLList L = new SLList();
        L.addLast(3);
        L.addFirst(10);
        L.addFirst(5);
        L.addFirst(6);
        L.addLast(2);
        int x = L.getFirst();
        int y = L.getLast();
        System.out.println(x);
        System.out.println(y);
        System.out.println(L.size());
        SLList L2 = new SLList(10);
        L2.addLast(3);
        L2.addFirst(5);
        L2.addFirst(6);
        L2.addLast(2);
        int q = L2.getFirst();
        int v = L2.getLast();
        System.out.println(q);
        System.out.println(v);
        System.out.println(L2.size());
    }

}
