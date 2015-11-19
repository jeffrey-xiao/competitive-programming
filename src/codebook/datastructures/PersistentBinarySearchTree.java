/*
 * Implementation of a persistent binary search tree.
 */

package codebook.datastructures;

import java.util.ArrayList;

public class PersistentBinarySearchTree {
	private ArrayList<Node> versions;
	private int versionNumber;

	private class Node {
		private Integer value;
		private Node left, right;

		Node (Integer value) {
			this.value = value;
		}

		@Override
		public String toString () {
			StringBuilder res = new StringBuilder("");
			if (left != null)
				res.append(left.toString());
			res.append(value + " ");
			if (right != null)
				res.append(right.toString());
			return res.toString();
		}
	}

	PersistentBinarySearchTree () {
		versions = new ArrayList<Node>();
		versions.add(null);
		versionNumber = 0;
	}

	public void add (Integer val) {
		versions.add(add(versions.get(versionNumber++), val));
	}

	private Node add (Node curr, Integer val) {
		if (curr == null)
			return new Node(val);
		int cmp = val.compareTo(curr.value);
		Node ret = new Node(curr.value);
		if (cmp <= -1) {
			ret.right = curr.right;
			ret.left = add(curr.left, val);
		} else if (cmp >= 1) {
			ret.left = curr.left;
			ret.right = add(curr.right, val);
		} else {
			ret.left = curr.left;
			ret.right = curr.right;
		}
		return ret;
	}

	public void remove (Integer val) {
		versions.add(remove(versions.get(versionNumber++), val));
	}

	private Node remove (Node curr, Integer val) {
		if (curr == null)
			return null;
		int cmp = val.compareTo(curr.value);
		Node ret = new Node(curr.value);
		if (cmp <= -1) {
			ret.right = curr.right;
			ret.left = remove(curr.left, val);
		} else if (cmp >= 1) {
			ret.left = curr.left;
			ret.right = remove(curr.right, val);
		} else {
			if (curr.left == null)
				ret = curr.right;
			else if (curr.right == null)
				ret = curr.left;
			else {
				Node toRemove = getMin(curr.right);
				ret.value = toRemove.value;
				ret.left = curr.left;
				ret.right = remove(curr.right, ret.value);
			}
		}
		return ret;
	}

	private Node getMin (Node curr) {
		while (curr.left != null)
			curr = curr.left;
		return curr;
	}

	@Override
	public String toString () {
		StringBuilder res = new StringBuilder();
		for (Node n : versions) {
			if (n == null)
				res.append("\n");
			else
				res.append(n.toString() + "\n");
		}
		return res.toString();
	}

	public static void main (String[] args) {
		PersistentBinarySearchTree bst = new PersistentBinarySearchTree();
		bst.add(5);
		bst.add(4);
		bst.add(6);
		bst.add(7);
		bst.add(1);
		bst.add(1);
		bst.add(2);
		bst.add(3);
		bst.remove(3);
		bst.add(10);
		bst.remove(5);
		bst.remove(4);
		System.out.println(bst);
	}
}
