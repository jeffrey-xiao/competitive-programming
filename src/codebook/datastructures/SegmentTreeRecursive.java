// Simple recursive segment tree implementation with sum operator
package codebook.datastructures;

public class SegmentTreeRecursive {

	// size of the inut
	private int size;
	// represents the nodes of the tree
	private int[] seg;
	// represents the lazy propagation lazy
	private int[] lazy;
	// reprsents the initial input
	private int[] val;

	SegmentTreeRecursive (int size, int[] val) {
		this.size = size;
		this.seg = new int[size * 4];
		this.lazy = new int[size * 4];
		this.val = val;
		build(1, 1, size);
	}

	// creating the segment tree
	private void build (int n, int lo, int hi) {
		if (lo == hi) {
			seg[n] = val[lo];
			return;
		}
		int mid = (hi + lo) >> 1;
		build(n << 1, lo, mid);
		build(n << 1 | 1, mid + 1, hi);
		seg[n] = seg[n << 1] + seg[n << 1 | 1];
	}

	// range update
	public void update (int lo, int hi, int newVal) {
		update(1, lo, hi, 1, size, newVal);
	}

	private void update (int n, int lo, int hi, int currLo, int currHi, int newVal) {
		if (lo == currLo && hi == currHi) {
			seg[n] = newVal;
			lazy[n] += newVal;
			return;
		}
		if (lazy[n] > 0) {
			lazy[n << 1] += lazy[n];
			lazy[n << 1 | 1] += lazy[n];
			lazy[n] = 0;
		}
		int mid = (currHi + currLo) >> 1;
		if (hi <= mid)
			update(n << 1, lo, hi, currLo, mid, newVal);
		else if (lo > mid)
			update(n << 1 | 1, lo, hi, mid + 1, currHi, newVal);
		else {
			update(n << 1, lo, mid, currLo, mid, newVal);
			update(n << 1 | 1, mid + 1, hi, mid + 1, currHi, newVal);
		}
		seg[n] = seg[n << 1] + seg[n << 1 | 1];
	}

	// range query
	public int query (int lo, int hi) {
		return query(1, lo, hi, 1, size);
	}

	private int query (int n, int lo, int hi, int currLo, int currHi) {
		if (lo == currLo && hi == currHi)
			return seg[n];
		if (lazy[n] > 0) {
			lazy[n << 1] += lazy[n];
			lazy[n << 1 | 1] += lazy[n];
			lazy[n] = 0;
		}
		int mid = (currHi + currLo) >> 1;
		if (hi <= mid)
			return query(n << 1, lo, hi, currLo, mid);
		else if (lo > mid)
			return query(n << 1 | 1, lo, hi, mid + 1, currHi);
		else
			return Math.min(query(n << 1, lo, mid, currLo, mid), query(n << 1 | 1, mid + 1, hi, mid + 1, currHi));
	}
}
