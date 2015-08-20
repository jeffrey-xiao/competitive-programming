/*
 * An interval tree is a data structure that represents a series of ranges
 * It stores these ranges and facilitates searching for those ranges
 * Adding a range and finding a range can be done in log N time
 * There is an assumption that all left endpoints are unique
 */

public class IntervalTree {
	// root of the tree
	private Node root;
	
	/**
	 * Adds an interval into the tree -- Time complexity of log N
	 * 
	 * @param lo represents the beginning of the interval
	 * @param hi represents the end of the interval
	 */
	public void add (int lo, int hi) {
		// start at the root and do a standard BST search downwards
		root = add(root, lo, hi);
	}
	private Node add (Node curr, int lo, int hi) {
		if (curr == null)
			return new Node(lo, hi);
		if (lo < curr.lo) {
			curr.left = add(curr.left, lo, hi);
			curr = reset(curr);
		} else {
			curr.right = add(curr.right, lo, hi);
			curr = reset(curr);
		}
		return curr;
	}
	
	/**
	 * Deletes an interval into the tree -- Time complexity of log N
	 * 
	 * @param lo represents the beginning of the interval
	 * @param hi represents the end of the interval
	 */
	
	public void remove (int lo, int hi) {
		root = remove(root, lo, hi);
	}
	
	private Node remove (Node curr, int lo, int hi) {
		if (curr == null)
			return curr;
		if (curr.lo == lo) {
			if (curr.left == null && curr.right == null)
				return null;
			else if (curr.left == null)
				return curr.right;
			else if (curr.right == null)
				return curr.left;
			else {
				Node replace = getMax(curr.left);
				curr.lo = replace.lo;
				curr.hi = replace.hi;
				curr.left = remove(curr.left, curr.lo, curr.hi);
				curr = reset(curr);
				return curr;
			}
		}
		
		if (lo < curr.lo) {
			curr.left = remove(curr.left, lo, hi);
			curr = reset(curr);
		} else {
			curr.right = remove(curr.right, lo, hi);
			curr = reset(curr);
		}
		return curr;
	}
	/**
	 * Auxiliary function to find the node with the largest value in a subtree
	 * @param curr represents the subtree to search in
	 * @return node with largest value
	 */
	private Node getMax (Node curr) {
		while (curr.right != null)
			curr = curr.right;
		return curr;
	}
	/**
	 * Auxiliary function to reset the max attribute
	 * @param curr represents the node to reset
	 */
	private Node reset (Node curr) {
		curr.max = curr.hi;
		if (curr.left != null)
			curr.max = Math.max(curr.left.max, curr.max);
		if (curr.right != null)
			curr.max = Math.max(curr.right.max, curr.max);
		return curr;
	}
	/**
	 * @param lo represents the beginning of the interval
	 * @param hi represents the end of the interval
	 * @return true if the given interval intersects with any in the tree, else false
	 */
	public boolean isIntersect (int lo, int hi) {
		return isIntersect(root, lo, hi);
	}
	private boolean isIntersect (Node curr, int lo, int hi) {
		if (curr == null)
			return false;
		if (Math.max(curr.lo, lo) < Math.min(curr.hi, hi))
			return true;
		if (curr.left == null || curr.left.max <= lo)
			return isIntersect(curr.right, lo, hi);
		return isIntersect(curr.left, lo, hi);
	}
	
	// A node of the tree
	static class Node {
		// children of the node
		Node left, right;
		
		// range that the node represents
		int lo, hi;
		
		// max end point of the subtree rooted at this node
		int max;
		
		Node (int lo, int hi) {
			this.lo = lo;
			this.hi = hi;
			this.max = hi;
		}
	}
}

