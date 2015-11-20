/* 
 * An AVL tree is a balanced binary search tree where the heights of the two subtrees of any node differ by at most one.
 * If at any point in time, the difference between two subtrees is greater than two, rebalancing is done to maintain this property.
 *
 * Time complexity:
 *  - Remove: O(log N)
 *  - Insertion: O(log N)
 *  - Search: O(log N)
 *  - Access: O(log N)
 */

package codebook.datastructures;

public class AVLTree {
	// root of the tree
	Node root = null;

	// object representing the nodes of the tree
	class Node {
		Integer key, value, height;
		Node left, right;

		Node (int key, int value) {
			this.key = key;
			this.value = value;
			this.height = 0;
		}

		Node (int key) {
			this.key = key;
			this.value = key;
			this.height = 0;
		}

	}

	// methods to reset and get the height of a node
	private void resetHeight (Node n) {
		n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;
	}

	private int getHeight (Node n) {
		return n == null ? -1 : n.height;
	}

	public void traverse () {
		traverse(root);
	}

	// auxiliary method for traverse
	private void traverse (Node n) {
		if (n == null)
			return;
		traverse(n.left);
		System.out.println(n.key);
		traverse(n.right);
	}

	public Integer get (Integer k) {
		return get(root, k);
	}

	// auxiliary method for get
	private Integer get (Node n, Integer k) {
		if (n == null)
			return null;
		int cmp = k.compareTo(n.key);
		if (cmp < 0)
			return get(n.left, k);
		else if (cmp > 0)
			return get(n.right, k);
		return n.value;
	}

	public boolean contains (Integer k) {
		return contains(root, k);
	}

	// auxiliary method for contains
	private boolean contains (Node n, Integer k) {
		if (n == null)
			return false;
		int cmp = k.compareTo(n.key);
		if (cmp < 0)
			return contains(n.left, k);
		else if (cmp > 0)
			return contains(n.right, k);
		return true;
	}

	public void remove (int k) {
		root = remove(root, k);
	}

	// auxiliary method for move
	private Node remove (Node n, Integer k) {
		if (n == null)
			return n;
		int cmp = k.compareTo(n.key);
		if (cmp < 0)
			n.left = remove(n.left, k);
		else if (cmp > 0)
			n.right = remove(n.right, k);
		else {
			if (n.left == null) {
				n = n.right;
				return n;
			} else if (n.right == null) {
				n = n.left;
				return n;
			} else {
				Node replace = minV(n.right);
				n.key = replace.key;
				n.value = replace.value;
				n.right = remove(n.right, n.key);
				return n;
			}
		}
		return balance(n);
	}

	private Node minV (Node n) {
		while (n.left != null)
			n = n.left;
		return n;
	}

	public void add (int k, int v) {
		root = add(root, k, v);
	}

	public void add (int k) {
		root = add(root, k, k);
	}

	// auxiliary method for add
	private Node add (Node n, Integer k, Integer v) {
		if (n == null)
			return new Node(k, v);
		int cmp = k.compareTo(n.key);
		if (cmp < 0)
			n.left = add(n.left, k, v);
		else if (cmp > 0)
			n.right = add(n.right, k, v);
		else
			n.value = v;
		return balance(n);
	}

	// auxiliary function to rebalance the tree
	private Node balance (Node n) {
		resetHeight(n);
		int diff1 = getHeight(n.left) - getHeight(n.right);
		// rotating right
		if (diff1 >= 2) {
			int diff2 = getHeight(n.left.right) - getHeight(n.left.left);
			// rotating left
			if (diff2 > 0)
				n.left = rotateLeft(n.left);
			n = rotateRight(n);
		}
		// rotating left
		else if (diff1 <= -2) {
			int diff2 = getHeight(n.right.left) - getHeight(n.right.right);
			// rotating right
			if (diff2 > 0)
				n.right = rotateRight(n.right);
			n = rotateLeft(n);
		}
		return n;
	}

	// rotate left
	private Node rotateLeft (Node n) {
		Node x = n.right;
		n.right = x.left;
		x.left = n;
		resetHeight(n);
		resetHeight(x);
		return x;
	}

	// rotate right
	private Node rotateRight (Node n) {
		Node x = n.left;
		n.left = x.right;
		x.right = n;
		resetHeight(n);
		resetHeight(x);
		return x;
	}

	public static void main (String[] args) {
		AVLTree t = new AVLTree();
		long c = System.currentTimeMillis();
		for (int x = 0; x < 1000000; x++) {
			int ran = (int) (Math.random() * (1 << 30)) + 5;
			t.add(ran);
		}
		// t.traverse(root);
		t.add(1);
		System.out.println(t.contains(t.root, 1));
		System.out.println(t.contains(t.root, 2));
		t.remove(1);
		System.out.println(t.contains(t.root, 1));
		System.out.println(System.currentTimeMillis() - c);
		// t.add(9);
		// t.add(5);
		// t.add(10);
		// t.add(0);
		// t.add(6);
		// t.add(11);
		// t.add(-1);
		// t.add(1);
		// t.add(2);
		// traverse(root);
		// t.remove(10);
		// System.out.println();
		// t.traverse(root);

	}
}
