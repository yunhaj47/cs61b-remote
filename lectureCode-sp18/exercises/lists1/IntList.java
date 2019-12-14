public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
	 	/** 重写递归size()函数*/
		if (rest == null) return 1;
		return 1 + this.rest.size();
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
		/** 重写迭代size()函数*/
		IntList p = this;
		int totalSize = 0;
		while (p != null) {
			totalSize += 1;
			p = p.rest;
		}
		return totalSize;
	}

	/** Returns the ith value in this list.*/
	public int get(int i) {
		/** 重写递归get函数*/
		/** Base case */
		if (i == 0) return first;
		return rest.get(i - 1);
	}

	/** Returns an IntList identical to L, but with
	 * each element incremented by x. L is not allowed
	 * to change. */
	public static IntList incrList(IntList L, int x) {
		/* Your code here. */
		IntList p = new IntList(L.get(0) + 3,null);
		IntList q = p;
		for (int i = 1; i <= L.size() - 1; i++){
			q.rest = new IntList(L.get(i) + x, null);
			q = q.rest;
		}
		return p;
	}

	/** Returns an IntList identical to L, but with
	 * each element incremented by x. Not allowed to use
	 * the 'new' keyword. */
	public static IntList dincrList(IntList L, int x) {
		/* Your code here. */
		IntList p = L;
		while (L != null){
			L.first += 3;
			L = L.rest;
		}
		return p;
	}

	public static IntList square(IntList L) {
		if (L == null) {
			return L;
		} else {
			IntList rest = square(L.rest);
			IntList M = new IntList(L.first * L.first, rest);
			return M;
		}
	}

	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);

		System.out.println(L.iterativeSize());
		System.out.println(L.size());
		System.out.println(L.get(0));
		System.out.println(L.get(2));
		IntList M = square(L);
		System.out.println(M);
		IntList q = incrList(L,3);
		System.out.println(q);
		System.out.println(dincrList(L, 3));
	}
} 