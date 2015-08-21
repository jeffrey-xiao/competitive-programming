public class AVLTree {
	public static void main (String[] args) {
		AVLTree t = new AVLTree();
		long c = System.currentTimeMillis();
		for (int x = 0; x < 10000; x++) {
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
	// root of the tree
	Node root = null;

	// object representing the nodes of the tree
	static class Node {
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

	// in order traversal of tree
	public void traverse (Node n) {
		if (n == null)
			return;
		traverse(n.left);
		System.out.println(n.key);
		traverse(n.right);
	}

	public boolean contains (Integer k) {
		return contains(root, k);
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
		resetHeight(n);
		int diff1 = getHeight(n.left) - getHeight(n.right);
		// rotating right
		if (diff1 >= 2) {
			int diff2 = getHeight(n.left.right) - getHeight(n.left.left);
			if (diff2 > 0) {
				n.left = rotateLeft(n.left);
			}
			n = rotateRight(n);
		}
		// rotating left
		else if (diff1 <= -2) {
			int diff2 = getHeight(n.right.left) - getHeight(n.right.right);
			if (diff2 > 0) {
				n.right = rotateRight(n.right);
			}
			n = rotateLeft(n);
		}
		return n;
	}

	private Node minV (Node n) {
		while (n.left != null);
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
		resetHeight(n);
		int diff1 = getHeight(n.left) - getHeight(n.right);
		// rotating right
		if (diff1 >= 2) {
			int diff2 = getHeight(n.left.right) - getHeight(n.left.left);
			if (diff2 > 0) {
				n.left = rotateLeft(n.left);
			}
			n = rotateRight(n);
		}
		// rotating left
		else if (diff1 <= -2) {
			int diff2 = getHeight(n.right.left) - getHeight(n.right.right);
			if (diff2 > 0) {
				n.right = rotateRight(n.right);
			}
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
}
