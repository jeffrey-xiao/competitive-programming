package codebook.datastructures;

/*
 * An interval tree is a data structure that represents a series of ranges
 * It stores these ranges and facilitates searching for those ranges
 * Adding a range and finding a range can be done in log N time
 * There is an assumption that all left end-points are unique
 */

public class IntervalTree {

	

	// root of the tree
	private Node root;

	public void add (int lo, int hi) {
		root = add(root, lo, hi);
	}

	private Node add (Node n, int lo, int hi) {
		if (n == null)
			return new Node(lo, hi);
		if (lo < n.lo)
			n.left = add(n.left, lo, hi);
		else if (lo > n.lo)
			n.right = add(n.right, lo, hi);
		resetMax(n);
		resetHeight(n);
		n = balance(n);
		return n;
	}

	public void remove (int lo, int hi) {
		root = remove(root, lo, hi);
	}

	private Node remove (Node n, int lo, int hi) {
		if (n == null)
			return n;
		if (n.lo == lo) {
			if (n.left == null && n.right == null)
				return null;
			else if (n.left == null)
				return n.right;
			else if (n.right == null)
				return n.left;
			else {
				Node replace = getMax(n.left);
				n.lo = replace.lo;
				n.hi = replace.hi;
				n.left = remove(n.left, n.lo, n.hi);
				resetMax(n);
				return n;
			}
		}

		if (lo < n.lo)
			n.left = remove(n.left, lo, hi);
		else
			n.right = remove(n.right, lo, hi);
		resetMax(n);
		resetHeight(n);
		n = balance(n);
		return n;
	}

	private void traverse (Node n) {
		if (n == null)
			return;
		System.out.println(n.lo + " " + (n.left == null ? "NULL" : n.left.lo) + " " + (n.right == null ? "NULL" : n.right.lo));
		traverse(n.left);
		traverse(n.right);
	}
	
	private Node getMax (Node n) {
		while (n.right != null)
			n = n.right;
		return n;
	}

	private void resetMax (Node n) {
		n.max = n.hi;
		if (n.left != null)
			n.max = Math.max(n.left.max, n.max);
		if (n.right != null)
			n.max = Math.max(n.right.max, n.max);
	}

	public boolean isIntersect (int lo, int hi) {
		return isIntersect(root, lo, hi);
	}

	private boolean isIntersect (Node n, int lo, int hi) {
		if (n == null)
			return false;
		if (Math.max(n.lo, lo) < Math.min(n.hi, hi))
			return true;
		if (n.left == null || n.left.max <= lo)
			return isIntersect(n.right, lo, hi);
		return isIntersect(n.left, lo, hi);
	}

	private Node rotateRight (Node n) {
		Node m = n.left;
		n.left = m.right;
		m.right = n;
		resetHeight(n);
		resetMax(n);
		resetHeight(m);
		resetMax(m);
		return m;
	}

	private Node rotateLeft (Node n) {
		Node m = n.right;
		n.right = m.left;
		m.left = n;
		resetHeight(n);
		resetMax(n);
		resetHeight(m);
		resetMax(m);
		return m;
	}

	private Node balance (Node n) {
		int diff1 = getHeight(n.left) - getHeight(n.right);
		if (diff1 >= 2) {
			if (getHeight(n.left.left) < getHeight(n.left.right))
				n.left = rotateLeft(n.left);
			n = rotateRight(n);
		} else if (diff1 <= -2) {
			if (getHeight(n.right.left) > getHeight(n.right.right))
				n.right = rotateRight(n.right);
			n = rotateLeft(n);
		}
		return n;
	}

	private void resetHeight (Node n) {
		n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;
	}

	private int getHeight (Node n) {
		return n == null ? -1 : n.height;
	}

	// A node of the tree
	static class Node {
		// children of the node
		Node left, right;

		// range that the node represents
		int lo, hi;

		// max end point of the subtree rooted at this node
		int max;

		// height of the node
		int height;

		Node (int lo, int hi) {
			this.lo = lo;
			this.hi = hi;
			this.max = hi;
			this.height = 0;
		}
	}
	
	public static void main (String[] args) {
		IntervalTree t = new IntervalTree();
		t.add(4, 8);
		t.traverse(t.root);
		System.out.println("---");
		t.add(5, 8);
		t.traverse(t.root);
		System.out.println("---");
		t.add(7, 10);
		t.traverse(t.root);
		System.out.println("---");
		t.add(15, 18);
		t.traverse(t.root);
		System.out.println("---");
		t.add(16, 22);
		t.traverse(t.root);
		System.out.println("---");
		t.add(17, 19);
		t.traverse(t.root);
		System.out.println("---");
		t.add(21, 24);
		t.traverse(t.root);
		System.out.println("---");

		// System.out.println("Expected true,   Actual: " + t.isIntersect(23,
		// 25));
		// System.out.println("Expected false,  Actual: " + t.isIntersect(12,
		// 14));
		// System.out.println("Expected true,   Actual: " + t.isIntersect(21,
		// 23));
		// testing adjoint
		// System.out.println("Expected false,  Actual: " + t.isIntersect(10,
		// 15));
		// System.out.println("Expected false,  Actual: " + t.isIntersect(10,
		// 14));
		// System.out.println("Expected false,  Actual: " + t.isIntersect(11,
		// 15));
		t.remove(21, 24);
		t.traverse(t.root);
		System.out.println("---");
		// System.out.println("Expected false,  Actual: " + t.isIntersect(23,
		// 25));
		// System.out.println("Expected false,  Actual: " + t.isIntersect(12,
		// 14));
		t.remove(16, 22);
		t.traverse(t.root);
		System.out.println("---");
		// System.out.println("Expected false,  Actual: " + t.isIntersect(21,
		// 23));
		t = new IntervalTree();
		for (int i = 0; i < 10000; i++) {
			t.add((int) (Math.random() * 100000), 1000000);
		}
		System.out.println(t.getHeight(t.root));
	}
}
