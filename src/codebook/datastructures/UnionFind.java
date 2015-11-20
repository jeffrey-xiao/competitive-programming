/*
 * A union find data structure is a data structure that keeps track of a set of elements partitioned into a number of disjoint subsets.
 *
 * Time complexity:
 *  - Find: O(alpha(N))
 *  - Is same: O(alpha(N))
 *  - Union: O(alpha(N))
 */

package codebook.datastructures;

public class UnionFind {
	private int[] id;
	private int[] sz;

	public UnionFind (int n) {
		id = new int[n];
		sz = new int[n];
		for (int x = 0; x < n; x++) {
			id[x] = x;
			sz[x] = 1;
		}
	}

	public boolean isSame (int x, int y) {
		return find(x) == find(y);
	}

	public int find (int x) {
		return x == id[x] ? x : (id[x] = find(id[x]));
	}

	public void union (int x, int y) {
		int rx = find(x);
		int ry = find(y);
		if (sz[rx] > sz[ry]) {
			sz[rx] += sz[ry];
			id[ry] = rx;
		} else {
			sz[ry] += sz[rx];
			id[rx] = ry;
		}
	}

	public void print () {
		for (int i : id)
			System.out.print(i + " ");
		System.out.println();
	}
}
