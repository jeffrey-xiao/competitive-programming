// Implements a left leaning Red Black Tree

package codebook.datastructures;

public class RedBlackTree {
	// constants for the colot
	private static final boolean RED = true;
	private static final boolean BLACK = true;
	
	// represents the root of the tree
	private Node root;
	
	/**
	 * @param key represents the key to remove
	 */
	public void remove (int key) {
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;
		root = remove(root, key);
		if (root != null)
			root.color = BLACK;
	}
	private Node remove (Node n, int key) {
		if (n == null)
			return n;
		if (key < n.key) {
			if (!isRed(n.left) && !isRed(n.left.left))
				n = shiftLeft(n);
			n.left = remove(n.left, key);
		} else {
			if (isRed(n.left))
				n = rotateRight(n);
			if (key == n.key && n.right == null)
				return null;
			if (!isRed(n.right) && !isRed(n.right.left))
				n = shiftRight(n);
			if (key == n.key) {
				Node x = getMinNode(n.right);
				n.key = x.key;
				n.value = x.value;
				n.right = removeMinNode(n.right);
			}
			get(n.right, key);
		}
		return balance(n);
	}
	
	/**
	 * @param key represents the key to search for
	 * @return	   the value associated with the key 
	 */
	public Integer get (int key) {
		return get(root, key);
	}
	private Integer get (Node n, int key) {
		if (n == null)
			return null;
		if (key < n.key)
			get(n.left, key);
		else if (key > n.key)
			get(n.right, key);
		return n.value;
	}
	
	/**
	 * @param key	 represents the key to be inserted
	 * @param value	 represents the value associated with the key
	 */
	public void add (int key, int value) {
		root = add(root, key, value);
		root.color = BLACK;
	}
	private Node add (Node n, int key, int value) {
		if (n == null)
			return new Node(key, value, RED);
		if (key < n.key)
			n.left = add(n.left, key, value);
		else if (key > n.key)
			n.right = add(n.right, key, value);
		else
			n.value = value;
		
		if (isRed(n.right) && !isRed(n.left))
			n = rotateLeft(n);
		if (isRed(n.left) && isRed(n.left.left))
			n = rotateRight(n);
		if (isRed(n.left) && isRed(n.right))
			flipColors(n);
		return n;
	}
	
	
	/**
	 * Helper function that rotates a given node left
	 * @param n		represents the node to be rotated
	 * @return		the new rotated node
	 */
	private Node rotateLeft (Node n) {
		Node x = n.right;
		n.right = x.left;
		x.left = n;
		x.color = n.color;
		n.color = RED;
		return x;
	}
	
	/**
	 * Helper function that rotates a given node right
	 * @param n		represents the node to be rotated
	 * @return		the new rotated node
	 */
	private Node rotateRight (Node n) {
		Node x = n.left;
		n.left = x.right;
		x.right = n;
		x.color = n.color;
		n.color = RED;
		return x;
	}
	
	/**
	 * Helper function that flips the color of a node
	 * @param n		represents the node to have its color changed
	 * @return		the new node
	 */
	private void flipColors (Node n) {
		n.color = RED;
		n.left.color = n.right.color = BLACK;
	}
	/**
	 * Helper function that checks if a node is red
	 * @param n
	 * @return
	 */
	private boolean isRed(Node n) {
        if (n == null) 
        	return false;
        return n.color == RED;
    }
	/**
	 * Restores the Red Black Tree invariant
	 * @param h
	 * @return
	 */
    private Node balance(Node n) {
        if (isRed(n.right))                      
        	n = rotateLeft(n);
        if (isRed(n.left) && isRed(n.left.left))
        	n = rotateRight(n);
        if (isRed(n.left) && isRed(n.right))     
        	flipColors(n);
        return n;
    }
 
    /**
     * Shift a red link left
     * @param n represents a red node
     * @return 	 the new node
     */
    private Node shiftLeft(Node n) {
        flipColors(n);
        if (isRed(n.right.left)) { 
            n.right = rotateRight(n.right);
            n = rotateLeft(n);
            flipColors(n);
        }
        return n;
    }

    /**
    * Shift a red link right
    * @param n represents a red node
    * @return 	 the new node
    */
    private Node shiftRight(Node n) {
        flipColors(n);
        if (isRed(n.left.left)) { 
            n = rotateRight(n);
            flipColors(n);
        }
        return n;
    }
    /**
     * @return the Node with the minimum key
     */
    public Node getMinNode (Node n) {
    	Node curr = n;
    	while (curr.left != null)
    		curr = curr.left;
    	return curr;
    }
    /**
     * @return the Node with the maximum key
     */
    public Node getMaxNode (Node n) {
    	Node curr = n;
    	while (curr.right != null)
    		curr = curr.right;
    	return curr;
    }
    /**
     * @param n represents the root the subtree to remove the minimum
     * @return
     */
    private Node removeMinNode(Node n) { 
        if (n.left == null)
            return null;
        if (!isRed(n.left) && !isRed(n.left.left))
            n = shiftLeft(n);
        n.left = removeMinNode(n.left);
        return balance(n);
    }
    /**
     * @param n represents the root the subtree to remove the minimum
     * @return
     */
    @SuppressWarnings ("unused")
	private Node removeMaxNode(Node n) { 
        if (n.right == null)
            return null;
        if (!isRed(n.right) && !isRed(n.right.left))
            n = shiftRight(n);
        n.right = removeMaxNode(n.right);
        return balance(n);
    }
	// object representing the nodes of the tree
	private class Node {
		int key, value;
		Node left, right;
		boolean color;
		Node (int key, int value, boolean color) {
			this.key = key;
			this.value = value;
			this.color = color;
		}
	}
}

