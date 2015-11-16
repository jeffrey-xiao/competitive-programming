package codebook.datastructures;

import java.util.Arrays;

public class MergeTree {

	private int[][] tree;
	private int[] a;
	private int n;

	public MergeTree (int n, int[] a) {
		this.n = n;
		this.a = Arrays.copyOf(a, n);
		this.tree = new int[4 * n][];
		build(1, 1, n);
	}

	private int[] merge (int[] a, int[] b) {
		int[] res = new int[a.length + b.length];
		int j = 0;
		int k = 0;
		for (int i = 0; i < res.length; i++) {
			if (k == b.length || (j != a.length && a[j] < b[k]))
				res[i] = a[j++];
			else
				res[i] = b[k++];
		}
		return res;
	}

	private void build (int n, int l, int r) {
		tree[n] = new int[(r - l) + 1];
		if (l == r) {
			tree[n][0] = a[l - 1];
			return;
		}
		int mid = (l + r) >> 1;
		build(n << 1, l, mid);
		build(n << 1 | 1, mid + 1, r);
		tree[n] = merge(tree[n << 1], tree[n << 1 | 1]);
	}

	public int query (int l, int r, int k) {
		int lo = -1000000000, hi = 1000000000;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (query(1, 1, n, l, r, mid) < k)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return lo;
	}

	private int query (int n, int l, int r, int ql, int qr, int val) {
		if (ql == l && qr == r) {
			int lo = 0, hi = r - l;
			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				if (tree[n][mid] <= val)
					lo = mid + 1;
				else
					hi = mid - 1;
			}
			return lo;
		}
		int mid = (l + r) >> 1;
		if (qr <= mid)
			return query(n << 1, l, mid, ql, qr, val);
		else if (ql > mid)
			return query(n << 1 | 1, mid + 1, r, ql, qr, val);
		else
			return query(n << 1, l, mid, ql, mid, val) + query(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
	}

	public void update (int x, int val) {
		update(1, 1, n, x, val);
	}

	private void update (int n, int l, int r, int x, int val) {
		if (x == l && x == r) {
			tree[n][0] = val;
			return;
		}
		int mid = (l + r) >> 1;
		if (x <= mid)
			update(n << 1, l, mid, x, val);
		else
			update(n << 1 | 1, mid + 1, r, x, val);
		tree[n] = merge(tree[n << 1], tree[n << 1 | 1]);
	}
}
