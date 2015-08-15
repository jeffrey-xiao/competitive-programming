package percolation;

public class WeightedQuickUnion {
	private int[] id;
	private int[] size;
	private int count;

	public WeightedQuickUnion (int n) {
		id = new int[n];
		size = new int[n];
		count = n;
		for (int x = 0; x < n; x++) {
			id[x] = x;
			size[x] = 1;
		}
	}

	public int find (int i) {
		while (i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}

	public boolean connected (int x, int y) {
		return find(x) == find(y);
	}

	public int count () {
		return count;
	}

	public void union (int x, int y) {
		int rootx = find(x);
		int rooty = find(y);
		if (rootx == rooty)
			return;
		count--;
		if (size[rootx] < size[rooty]) {
			id[rootx] = id[rooty];
			size[rooty] += size[rootx];
		} else {
			id[rooty] = id[rootx];
			size[rootx] += size[rooty];
		}
	}

	public void print () {
		for (int i : id)
			System.out.print(i + " ");
		System.out.println();
	}

}
