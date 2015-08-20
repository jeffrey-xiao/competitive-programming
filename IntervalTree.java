/*
 * An interval tree is a data structure that represents a series of ranges
 * It stores these ranges and facilitates searching for those ranges
 * Adding a range and finding a range can be done in log N time
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
			curr.max = Math.max(curr.left.max, curr.max);
		} else {
			curr.right = add(curr.right, lo, hi);
			curr.max = Math.max(curr.right.max, curr.max);
		}
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

