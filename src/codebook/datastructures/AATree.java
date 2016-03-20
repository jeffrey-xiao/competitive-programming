/* 
 * An AA Tree is a balanced binary search tree that is a variation of the red-black tree with an additional restriction.
 * Unlike red-black trees, no red node can be left subchild. This results in the simulation of 2-3 tree instead of a 2-3-4 tree.
 *
 * Time complexity:
 *  - Remove: O(log N)
 *  - Insertion: O(log N)
 *  - Search: O(log N)
 *  - Access: O(log N)
 */

package codebook.datastructures;

import java.util.TreeSet;

public class AATree {
	// represents the root of the tree
	private Node root;

	public void remove (int key) {
		root = remove(root, key);
	}

	private Node remove (Node n, int key) {
		if (n == null)
			return n;
		if (key < n.key)
			n.left = remove(n.left, key);
		else if (key > n.key)
			n.right = remove(n.right, key);
		else {
			if (n.right == null && n.left == null)
				return null;
			else if (n.left != null) {
				Node x = getMaxNode(n.left);
				n.left = remove(n.left, x.value);
				n.value = x.value;
			} else {
				Node x = getMinNode(n.right);
				n.right = remove(n.right, x.value);
				n.value = x.value;
			}
		}
		n = decreaseLevel(n);
		n = skew(n);
		n.right = skew(n.right);
		if (n.right != null)
			n.right.right = skew(n.right.right);
		n = split(n);
		n.right = split(n.right);
		return n;
	}

	private Node decreaseLevel (Node n) {
		int l = Math.min(getLevel(n.left), getLevel(n.right)) + 1;
		if (l < getLevel(n)) {
			n.level = l;
			if (l < getLevel(n.right))
				n.right.level = l;
		}
		return n;
	}

	private int getLevel (Node n) {
		if (n == null)
			return 0;
		return n.level;
	}

	public boolean contains (int key) {
		return contains(root, key);
	}

	private boolean contains (Node n, int key) {
		if (n == null)
			return false;
		if (key < n.key)
			return contains(n.left, key);
		else if (key > n.key)
			return contains(n.right, key);
		else
			return true;
	}

	public Integer get (int key) {
		return get(root, key);
	}

	private Integer get (Node n, int key) {
		if (n == null)
			return null;
		if (key < n.key)
			return get(n.left, key);
		else if (key > n.key)
			return get(n.right, key);
		else
			return n.value;
	}

	public void add (int key) {
		root = add(root, key, key);
	}

	public void add (int key, int value) {
		root = add(root, key, value);
	}

	private Node add (Node n, int key, int value) {
		if (n == null)
			return new Node(key, value);
		if (key < n.key)
			n.left = add(n.left, key, value);
		else if (key > n.key)
			n.right = add(n.right, key, value);
		else
			n.value = value;
		n = skew(n);
		n = split(n);
		return n;
	}

	private Node skew (Node n) {
		if (n == null)
			return null;
		if (n.left == null)
			return n;
		if (n.left.level == n.level)
			n = rotateRight(n);
		return n;
	}

	private Node split (Node n) {
		if (n == null)
			return null;
		if (n.right == null || n.right.right == null)
			return n;
		if (n.right.right.level == n.level) {
			n = rotateLeft(n);
			n.level++;
		}
		return n;
	}

	private Node rotateLeft (Node n) {
		Node x = n.right;
		n.right = x.left;
		x.left = n;
		return x;
	}

	private Node rotateRight (Node n) {
		Node x = n.left;
		n.left = x.right;
		x.right = n;
		return x;
	}

	public Node getMinNode (Node n) {
		Node curr = n;
		while (curr.left != null)
			curr = curr.left;
		return curr;
	}

	public Node getMaxNode (Node n) {
		Node curr = n;
		while (curr.right != null)
			curr = curr.right;
		return curr;
	}

	// in order traversal of nodes
	public void traverse (Node n) {
		if (n == null)
			return;
		traverse(n.left);
		System.out.print(n.key + " ");
		traverse(n.right);
	}

	// object representing the nodes of the tree
	private class Node {
		int key, value;
		Node left, right;
		int level;

		Node (int key, int value) {
			this.key = key;
			this.value = value;
			this.level = 1;
		}
	}

	public static void main (String[] args) {
		AATree t = new AATree();
		long c = System.currentTimeMillis();
		TreeSet<Integer> hs = new TreeSet<Integer>();
		for (int x = 0; x < 100000; x++) {
			int ran = (int) (Math.random() * (100000)) + 5;
			hs.add(ran);
			t.add(ran);
		}
		System.out.println(hs.size());
		for (Integer i : hs)
			System.out.print(i + " ");
		System.out.println();
		t.traverse(t.root);
		System.out.println();
		t.add(1);
		assert (t.contains(t.root, 1));
		assert (!t.contains(t.root, 2));
		t.remove(1);
		assert (!t.contains(t.root, 1));
		System.out.println(System.currentTimeMillis() - c);
		for (Integer i : hs) {
			t.remove(i);
			assert (!t.contains(t.root, i));
		}
		System.out.println("SUCCESS");
	}

}
