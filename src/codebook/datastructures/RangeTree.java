package codebook.datastructures;

/*
 * Range tree is a data structure that stores 2D points and is able to return
 * a list of points that is contained in a specific rectangle
 * 
 * This implementation uses two BSTs (treaps) and takes log N time for adding and deletion
 * There is an assumption that there are no two points with the same x or y coordinate
 * 
 * For the simplified 1D problem, it is suffice to use one BST to find all points in the range [x1, x2]
 * The leaves of the BST will be the points and the internal nodes will be the largest point in its left subtree
 * When searching for x1 and x2, find the split node where the search paths diverge
 * Points in the range [x1, x2] will be the leaves in the right subtrees in the search path of x1
 * and leaves in the left subtrees in the search path of x2
 *
 * The complexity for returning a range is O(log N ^ d + k) where d is the number of dimensions and k is the number of points returned
 */

public class RangeTree {

	// root of the tree
	private Node root;

	public void add (int x, int y) {
		root = add(root, x, y);
	}

	private Node add (Node n, int x, int y) {
		if (n == null)
			return new Node(x, y);
		n.bst.add(y, x);
		if (x < n.x)
			n.left = add(n.left, x, y);
		else
			n.right = add(n.right, x, y);
		return n;
	}

	public void query (int x1, int y1, int x2, int y2) {
		// finding the node where n.x is in the x-interval
		Node n = root;
		while (n != null && !(x1 <= n.x && n.x <= x2)) {
			if (x2 < n.x)
				n = n.left;
			else if (x1 > n.x)
				n = n.right;
		}
		if (n == null)
			return;
		if (y1 <= n.y && n.y <= y2)
			System.out.println(n.x + " " + n.y);
		queryL(n.left, x1, y1, x2, y2);
		queryR(n.right, x1, y1, x2, y2);
	}

	private void queryL (Node n, int x1, int y1, int x2, int y2) {
		if (n == null)
			return;
		if (x1 <= n.x && n.x <= x2 && y1 <= n.y && n.y <= y2)
			System.out.println(n.x + " " + n.y);
		if (x1 <= n.x) {
			printAll(n.right, y1, y2);
			queryL(n.left, x1, y1, x2, y2);
		} else
			queryL(n.right, x1, y1, x2, y2);
	}

	private void queryR (Node n, int x1, int y1, int x2, int y2) {
		if (n == null)
			return;
		if (x1 <= n.x && n.x <= x2 && y1 <= n.y && n.y <= y2)
			System.out.println(n.x + " " + n.y);
		if (x1 >= n.x) {
			printAll(n.left, y1, y2);
			queryR(n.right, x1, y1, x2, y2);
		} else
			queryR(n.left, x1, y1, x2, y2);
	}

	private void printAll (Node n, int y1, int y2) {
		if (n == null)
			return;
		for (Integer y : n.bst.range(y1, y2)) {
			System.out.println(n.bst.get(y) + " " + y);
		}
	}

	// object representing a node of the tree
	static class Node {
		// x and y coordinates
		int x, y;
		// left and right nodes
		Node left, right;
		// BST for y coordinate
		Treap bst;

		Node (int x, int y) {
			this.x = x;
			this.y = y;
			this.bst = new Treap();
			this.bst.add(y, x);
		}
	}
}
