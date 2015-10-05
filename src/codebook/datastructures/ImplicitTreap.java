/*
 * An implicit treap is a treap that uses array indexes as its key and the value at that index as its value.
 * As a result, it can perform removal and deletion in O(log N)
 */

package codebook.datastructures;

class ImplicitTreap {
	// root of the tree
	Node root = null;

	// object representing the nodes of the tree
	static class Node {
		Integer size;
		Integer value;
		Double priority;
		Node left, right;

		Node (int value) {
			this.value = value;
			this.size = 1;
			priority = Math.random();
		}
	}

	// object representing a pair of nodes of the tree
	static class NodePair {
		Node left, right;

		NodePair (Node left, Node right) {
			this.left = left;
			this.right = right;
		}
	}

	public void remove (Integer k) {
		if (k > getSize(root))
			throw new IllegalArgumentException();
		root = remove(root, k, 0);
	}

	// auxiliary function to delete
	private Node remove (Node n, Integer k, Integer lowerCnt) {
		if (n == null)
			return n;
		Integer key = lowerCnt + getSize(n.left) + 1;
		int cmp = k.compareTo(key);
		if (cmp < 0)
			n.left = remove(n.left, k, lowerCnt);
		else if (cmp > 0)
			n.right = remove(n.right, k, lowerCnt + getSize(n.left) + 1);
		else {
			n = merge(n.left, n.right);
		}
		resetSize(n);
		return n;
	}

	public Integer get (Integer k) {
		if (k > getSize(root) || k <= 0)
			throw new IllegalArgumentException();
		return get(root, k, 0);
	}

	// auxiliary function for get
	private Integer get (Node n, Integer k, Integer lowerCnt) {
		if (n == null)
			return null;
		Integer key = lowerCnt + getSize(n.left) + 1;
		int cmp = k.compareTo(key);
		if (cmp < 0)
			return get(n.left, k, lowerCnt);
		else if (cmp > 0)
			return get(n.right, k, lowerCnt + getSize(n.left) + 1);
		return n.value;
	}

	public void pushBack (Integer val) {
		root = merge(root, new Node(val));
	}

	public void add (Integer key, Integer val) {
		NodePair n = split(root, key, 0);
		Node newRoot = merge(n.left, new Node(val));
		newRoot = merge(newRoot, n.right);
		root = newRoot;
	}

	public void modify (Integer key, Integer val) {
		root = modify(root, key, val, 0);
	}

	// auxiliary method for modify
	private Node modify (Node n, Integer key, Integer val, Integer lowerCnt) {
		Integer nKey = lowerCnt + getSize(n.left) + 1;
		if (nKey == key)
			n.value = val;
		else if (nKey > key)
			n.left = modify(n.left, key, val, lowerCnt);
		else
			n.right = modify(n.right, key, val, lowerCnt + getSize(n.left) + 1);
		return n;
	}

	public void traverse () {
		traverse(root);
	}

	// auxiliary method for traverse
	private void traverse (Node n) {
		if (n == null)
			return;
		traverse(n.left);
		System.out.println(n.value + " " + n.priority);
		traverse(n.right);
	}

	private void resetSize (Node n) {
		if (n != null)
			n.size = getSize(n.left) + getSize(n.right) + 1;
	}

	private int getSize (Node n) {
		return n == null ? 0 : n.size;
	}

	// auxiliary function to merge
	private Node merge (Node t1, Node t2) {
		if (t1 == null)
			return t2;
		else if (t2 == null)
			return t1;

		Node newRoot = null;
		if (t1.priority > t2.priority) {
			t1.right = merge(t1.right, t2);
			newRoot = t1;
		} else {
			t2.left = merge(t1, t2.left);
			newRoot = t2;
		}
		resetSize(newRoot);
		return newRoot;
	}

	// auxiliary function to split
	private NodePair split (Node n, Integer key, Integer lowerCnt) {
		NodePair res = new NodePair(null, null);
		if (n == null)
			return res;
		Integer nKey = lowerCnt + getSize(n.left) + 1;
		if (nKey > key) {
			res = split(n.left, key, lowerCnt);
			n.left = res.right;
			res.right = n;
			resetSize(res.left);
			resetSize(res.right);
			return res;
		} else {
			res = split(n.right, key, lowerCnt + getSize(n.left) + 1);
			n.right = res.left;
			res.left = n;
			resetSize(res.left);
			resetSize(res.right);
			return res;
		}
	}

	public static void main (String[] args) {
		ImplicitTreap t = new ImplicitTreap();
		for (int i = 1; i <= 10; i++)
			t.pushBack(i);
		t.add(1, 100);
		for (int i = 1; i <= 11; i++)
			System.out.println(t.get(i));
	}
}
