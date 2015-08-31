package codebook.datastructures;

public class BinaryIndexedTree {
	private int[] tree1;
	private int[] tree2;
	private int size;

	BinaryIndexedTree (int size) {
		this.size = size;
		this.tree1 = new int[size];
		this.tree2 = new int[size];

	}

	// Point updates and range queries
	
	public void update (int idx, int val) { 
		for (int x = idx; x < size; x += (x & -x)) tree1[x] += val; 
	} 
	public int query (int idx) { 
		int res = 0;
		for (int x = idx; x > 0; x -= (x & -x)) res += tree1[x]; 
		return res; 
	}
	public int query (int x1, int x2) { 
		return query(x2) - query(x1 - 1); 
	}
	
	// Range updates and point queries
	
	public void update (int idx, int val) { 
		for (int x = idx; x < size; x += (x & -x)) tree1[x] += val; 
	} 
	public void update (int x1, int x2, int val) { 
		update(x1, val); update(x2+1, -val); 
	} 
	public int query (int idx) {
		int sum = 0; 
		for (int x = idx; x > 0; x -= (x & -x)) 
		sum += tree1[x]; 
		return sum; 
	}
	 

	// Range updates and range queries
	
	public void update (int[] tree, int idx, int val) { 
		for (int x = idx; x < size; x += (x & -x)) 
		tree[x] += val; 
	} 
	public void update (int x1, int x2, int val) { 
		update(tree1, x1, val); 
		update(tree1, x2+1, -val);
		update(tree2, x1, val * (x1-1)); 
		update(tree2, x2+1, -val * x2); 
	} 
	public int query (int[] tree, int idx) { 
		int sum = 0; 
		for (int x = idx; x > 0; x -= (x & -x)) sum += tree[x]; 
		return sum; 
	} 
	public int query (int x) {
		return query(tree1, x) * x - query(tree2, x); 
	} 
	public int query (int x1, int x2) { 
		return query(x2) - query(x1-1); 
	}
}
