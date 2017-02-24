/* 
 * A size balanced tree (SBT) is a balanced binary search tree (BBST) which is rebalanced by examining the sizes of each node's subtrees
 * This data structure is very useful and easy to implement. The only additional information stored is the size of each node.
 * 
 * Time complexity:
 *  - Remove: O(log N)
 *  - Insertion: O(log N)
 *  - Search: O(log N)
 *  - Access: O(log N)
 */

package codebook.datastructures;

import java.util.TreeSet;

public class SizeBalancedTree {
	// represents the root of the tree
	private Node root;

	private class Node {
		Node left, right;
		Integer key, value, size;

		Node (Integer key, Integer value) {
			this.key = key;
			this.value = value;
			this.size = 1;
		}
	}

	public boolean contains (Integer key) {
		return contains(root, key);
	}

	private boolean contains (Node n, Integer key) {
		if (n == null)
			return false;
		int cmp = key.compareTo(n.key);
		if (cmp < 0)
			return contains(n.left, key);
		else if (cmp > 0)
			return contains(n.right, key);
		else
			return true;
	}

	public Integer get (Integer key) {
		return get(root, key);
	}

	private Integer get (Node n, Integer key) {
		if (n == null)
			return null;
		int cmp = key.compareTo(n.key);
		if (cmp < 0)
			return get(n.left, key);
		else if (cmp > 0)
			return get(n.right, key);
		else
			return n.value;
	}

	public void remove (Integer key) {
		root = remove(root, key);
	}

	private Node remove (Node n, Integer k) {
		if (n == null)
			return n;
		int cmp = k.compareTo(n.key);
		if (cmp < 0)
			n.left = remove(n.left, k);
		else if (cmp > 0)
			n.right = remove(n.right, k);
		else {
			if (n.right == null)
				n = n.left;
			else if (n.left == null)
				n = n.right;
			else {
				Node remove = getFirst(n.right);
				n.value = remove.value;
				n.key = remove.key;
				remove(n.right, n.key);
			}
		}
		return n;
	}

	public void add (Integer key) {
		add(key, key);
	}

	public void add (Integer key, Integer value) {
		root = add(root, key, value);
	}

	private Node add (Node n, Integer key, Integer value) {
		if (n == null)
			return new Node(key, value);
		int cmp = key.compareTo(n.key);
		if (cmp < 0)
			n.left = add(n.left, key, value);
		else if (cmp > 0)
			n.right = add(n.right, key, value);
		else
			n.value = value;
		n = resetSize(n);
		n = maintain(n, key >= n.key);
		return n;
	}

	private Node maintain (Node n, boolean flag) {
		if (n == null)
			return n;
		if (flag) {
			if (n.left == null || n.right == null)
				return n;
			if (getSize(n.left) < getSize(n.right.left)) {
				n.right = rotateRight(n.right);
				n = rotateLeft(n);
			} else if (getSize(n.left) < getSize(n.right.right)) {
				n = rotateLeft(n);
			} else {
				return n;
			}
		} else {
			if (n.left == null || n.right == null)
				return n;
			if (getSize(n.right) < getSize(n.left.right)) {
				n.left = rotateLeft(n.left);
				n = rotateRight(n);
			} else if (getSize(n.right) < getSize(n.left.left)) {
				n = rotateRight(n);
			} else {
				return n;
			}
		}
		n.left = maintain(n.left, false);
		n.right = maintain(n.right, true);
		n = maintain(n, true);
		n = maintain(n, false);
		return n;
	}

	public Integer getFirst () {
		return getFirst(root).value;
	}

	public Integer getLast () {
		return getLast(root).value;
	}

	private Node getFirst (Node n) {
		while (n.left != null)
			n = n.left;
		return n;
	}

	private Node getLast (Node n) {
		while (n.right != null)
			n = n.right;
		return n;
	}

	private Node resetSize (Node n) {
		n.size = getSize(n.left) + getSize(n.right) + 1;
		return n;
	}

	private Integer getSize (Node n) {
		return n == null ? 0 : n.size;
	}

	// rotate left
	private Node rotateLeft (Node n) {
		Node x = n.right;
		n.right = x.left;
		x.left = n;
		n = resetSize(n);
		x = resetSize(x);
		return x;
	}

	// rotate right
	private Node rotateRight (Node n) {
		Node x = n.left;
		n.left = x.right;
		x.right = n;
		n = resetSize(n);
		x = resetSize(x);
		return x;
	}

	// in order traversal of nodes
	public void traverse (Node n) {
		if (n == null)
			return;
		traverse(n.left);
		System.out.print(n.key + " ");
		traverse(n.right);
	}

	public static void main (String[] args) {
		SizeBalancedTree t = new SizeBalancedTree();
		long c = System.currentTimeMillis();
		TreeSet<Integer> hs = new TreeSet<Integer>();
		for (int x = 0; x < 100000; x++) {
			int ran = (int)(Math.random() * (100000)) + 5;
			hs.add(ran);
			t.add(ran);
		}
		System.out.println(hs.size() + " " + t.getSize(t.root));
		for (Integer i : hs)
			System.out.print(i + " ");
		System.out.println();
		t.traverse(t.root);
		System.out.println();
		t.add(1);
		assert t.contains(t.root, 1);
		assert !t.contains(t.root, 2);
		t.remove(1);
		assert !t.contains(t.root, 1);
		System.out.println(System.currentTimeMillis() - c);
		for (Integer i : hs) {
			t.remove(i);
			assert !t.contains(t.root, i);
		}
		System.out.println("SUCCESS");
	}
}
