/*
 * A segment tree is a tree data structure for storing intervals. Lazy propagation is used to ensure a O(log N) query and update time.
 * This implementation can update and query the sum of elements. Additionally, it dynamically creates nodes to conserve memory
 */

package codebook.datastructures;

public class ImplicitSegmentTree {
	private Node root;
	private int size;

	ImplicitSegmentTree (int size) {
		this.size = size;
	}

	class Node {
		Node left, right;
		int value;
		int lazy;

		Node (int value) {
			this.value = value;
		}
	}

	class Query {
		int res;
		Node n;

		Query (int res, Node n) {
			this.res = res;
			this.n = n;
		}
	}

	public void update (int qlo, int qhi, int value) {
		root = update(root, 1, size, qlo, qhi, value);
	}

	private Node update (Node n, int lo, int hi, int qlo, int qhi, int value) {
		if (n == null)
			n = new Node(0);
		if (lo == qlo && hi == qhi) {
			n.value += value * (hi - lo + 1);
			n.lazy += value;
			return n;
		}
		int mid = (hi + lo) >> 1;
		if (n.lazy > 0) {
			if (n.left == null)
				n.left = new Node(0);
			if (n.right == null)
				n.right = new Node(0);
			n.left.value += n.lazy * (mid - lo + 1);
			n.right.value += n.lazy * (hi - (mid + 1) + 1);
			n.left.lazy += n.lazy;
			n.right.lazy += n.lazy;
			n.lazy = 0;
		}
		if (qhi <= mid)
			n.left = update(n.left, lo, mid, qlo, qhi, value);
		else if (qlo > mid)
			n.right = update(n.right, mid + 1, hi, qlo, qhi, value);
		else {
			n.left = update(n.left, lo, mid, qlo, mid, value);
			n.right = update(n.right, mid + 1, hi, mid + 1, qhi, value);
			n.value = n.left.value + n.right.value;
		}
		return n;
	}

	public int query (int qlo, int qhi) {
		Query q = query(root, 1, size, qlo, qhi);
		root = q.n;
		return q.res;
	}

	private Query query (Node n, int lo, int hi, int qlo, int qhi) {
		if (n == null)
			n = new Node(0);
		if (lo == qlo && hi == qhi)
			return new Query(n.value, n);
		int mid = (hi + lo) >> 1;
		if (n.lazy > 0) {
			if (n.left == null)
				n.left = new Node(0);
			if (n.right == null)
				n.right = new Node(0);
			n.left.value += n.lazy * (mid - lo + 1);
			n.right.value += n.lazy * (hi - (mid + 1) + 1);
			n.left.lazy += n.lazy;
			n.right.lazy += n.lazy;
			n.lazy = 0;
		}
		Query q;
		if (qhi <= mid) {
			q = query(n.left, lo, mid, qlo, qhi);
			n.left = q.n;
			return new Query(q.res, n);
		} else if (qlo > mid) {
			q = query(n.right, mid + 1, hi, qlo, qhi);
			n.right = q.n;
			return new Query(q.res, n);
		} else {
			Query q1 = query(n.left, lo, mid, qlo, mid);
			Query q2 = query(n.right, mid + 1, hi, mid + 1, qhi);
			n.left = q1.n;
			n.right = q2.n;
			return new Query(q1.res + q2.res, n);
		}
	}

	public static void main (String[] args) {
		ImplicitSegmentTree t = new ImplicitSegmentTree(10);
		t.update(1, 5, 5);

		System.out.println(t.query(1, 5));
		System.out.println(t.query(2, 5));
		System.out.println(t.query(3, 5));
		System.out.println(t.query(4, 5));
		System.out.println(t.query(5, 5));
	}
}
