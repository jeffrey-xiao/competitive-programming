package data_structures;

public class BinaryIndexedTree {
	private int[] tree1;
	private int[] tree2;
	private int size;
	
	BinaryIndexedTree (int size) {
		tree1 = new int[size];
		tree2 = new int[size];
		this.size = size;
	}
	
	// Point updates and range queries
	
//	public void update (int idx, int val) {
//		for (int x = idx; x < size; x += (x & -x))
//			tree1[x] += val;
//	}
//	public int query (int idx) {
//		int res = 0;
//		for (int x = idx; x > 0; x -= (x & -x))
//			res += tree1[x];
//		return res;
//	}
//	public int query (int l, int r) {
//		return query(r) - query(l - 1);
//	}
	
	// Range updates and point queries
	
//	public void update (int idx, int val) {
//		for (int x = idx; x < size; x += (x & -x))
//			tree1[x] += val;
//	}
//	public void update (int l, int r, int val) {
//		update(l, val);
//		update(r+1, -val);
//	}
//	public void query (int idx) {
//		int sum = 0;
//		for (int x = idx; x > 0; x -= (x & -x))
//			sum += tree1[x];
//		return sum;
//	}
	
	// Range updates and range queries
	
//	public void update (int[] tree, int idx, int val) {
//		for (int x = idx; x < size; x += (x & -x))
//			tree[x] += val;
//	}
//	public void update (int l, int r, int val) {
//		update(tree1, l, val);
//		update(tree1, r+1, -val);
//		update(tree2, l, val * (l-1));
//		update(tree2, r+1, -val * r);
//	}
//	public int query (int[] tree, int idx) {
//		int sum = 0;
//		for (int x = idx; x > 0; x -= (x & -x))
//			sum += tree[x];
//		return sum;
//	}
//	public int query (int idx) {
//		return query(tree1, idx) * idx - query(tree2, idx);
//	}
//	public int query (int l, int r) {
//		return query(r) - query(l-1);
//	}
	
}

