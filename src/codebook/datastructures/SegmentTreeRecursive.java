/*
 * A segment tree is a tree data structure for storing intervals. Lazy propagation is used to ensure a O(log N) query and update time.
 * This implementation can update and query the sum of elements.
 *
 * Time complexity:
 *  - Build: O(N)
 *  - Update: O(log N)
 *  - Query: O(log N)
 */

package codebook.datastructures;

public class SegmentTreeRecursive {

	// size of the input
	private int size;
	// represents the nodes of the tree
	private int[] seg;
	// represents the lazy propagation lazy
	private int[] lazy;
	// reprsents the initial input
	private int[] val;

	SegmentTreeRecursive (int size) {
		this(size, new int[size + 1]);
	}

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
		update(1, 1, size, lo, hi, newVal);
	}

	private void update (int n, int lo, int hi, int qlo, int qhi, int newVal) {
		if (lo == qlo && hi == qhi) {
			seg[n] += newVal * (hi - lo + 1);
			lazy[n] += newVal;
			return;
		}
		int mid = (lo + hi) >> 1;
		if (lazy[n] > 0) {
			lazy[n << 1] += lazy[n];
			lazy[n << 1 | 1] += lazy[n];
			seg[n << 1] += lazy[n] * (mid - lo + 1);
			seg[n << 1 | 1] += lazy[n] * (hi - (mid + 1) + 1);
			lazy[n] = 0;
		}
		if (qhi <= mid)
			update(n << 1, lo, mid, qlo, qhi, newVal);
		else if (qlo > mid)
			update(n << 1 | 1, mid + 1, hi, qlo, qhi, newVal);
		else {
			update(n << 1, lo, mid, qlo, mid, newVal);
			update(n << 1 | 1, mid + 1, hi, mid + 1, qhi, newVal);
		}
		seg[n] = seg[n << 1] + seg[n << 1 | 1];
	}

	// range query
	public int query (int lo, int hi) {
		return query(1, 1, size, lo, hi);
	}

	private int query (int n, int lo, int hi, int qlo, int qhi) {
		if (lo == qlo && hi == qhi)
			return seg[n];
		int mid = (lo + hi) >> 1;
		if (lazy[n] > 0) {
			lazy[n << 1] += lazy[n];
			lazy[n << 1 | 1] += lazy[n];
			seg[n << 1] += lazy[n] * (mid - lo + 1);
			seg[n << 1 | 1] += lazy[n] * (hi - (mid + 1) + 1);
			lazy[n] = 0;
		}
		if (qhi <= mid)
			return query(n << 1, lo, mid, qlo, qhi);
		else if (qlo > mid)
			return query(n << 1 | 1, mid + 1, hi, qlo, qhi);
		else
			return query(n << 1, lo, mid, qlo, mid) + query(n << 1 | 1, mid + 1, hi, mid + 1, qhi);
	}

	public static void main (String[] args) {
		SegmentTreeRecursive t = new SegmentTreeRecursive(10);
		t.update(1, 5, 5);
		System.out.println(t.query(2, 5));
		System.out.println(t.query(3, 5));
		System.out.println(t.query(4, 5));
		System.out.println(t.query(5, 5));
		t.update(2, 3, 3);
		System.out.println(t.query(1, 5));
		System.out.println(t.query(1, 2));
	}
}
