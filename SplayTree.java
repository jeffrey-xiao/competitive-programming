public class SplayTree {
	public static void main (String[] args) {
		SplayTree t = new SplayTree();
		long c = System.currentTimeMillis();
		for (int x = 0; x < 100; x++) {
			int ran = (int) (Math.random() * (100)) + 5;
			t.add(ran);
		}
		t.traverse(t.root);
		t.add(1);
		System.out.println(t.contains(1));
		System.out.println(t.contains(2));
		t.remove(1);
		System.out.println(t.contains(1));
		System.out.println(System.currentTimeMillis()-c);
	}
	// root of the tree
	Node root = null;

	// object representing a node of the tree
	static class Node implements Comparable<Node> {
		int key;
		int value;
		Node left, right;

		Node (int value) {
			this.key = value;
			this.value = value;
		}
		
		Node (int key, int value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo (Node o) {
			return value - o.value;
		}
	}

	// in order traversal of the tree
	public void traverse (Node n) {
		if (n == null)
			return;
		traverse(n.left);
		System.out.println(n.value);
		traverse(n.right);
	}

	public void add (Integer v) {
		add(v, v);
	}
	
	public void add (Integer k, Integer v) {
		// empty tree
		if (root == null) {
			root = new Node(k, v);
			return;
		}
		root = splay(root, v);

		int cmp = v.compareTo(root.value);
		// take left subtree and put it as left, and the left subtree and root
		// and put it as right
		if (cmp < 0) {
			Node newRoot = new Node(k, v);
			newRoot.left = root.left;
			newRoot.right = root;
			root.left = null;
			root = newRoot;
		}
		// take right subtree and put it as right, and the left subtree and root
		// and put it as left
		else if (cmp > 0) {
			Node newRoot = new Node(k, v);
			newRoot.right = root.right;
			newRoot.left = root;
			root.right = null;
			root = newRoot;
		}
		// else the value already exists
	}

	public boolean contains (Integer v) {
		root = splay(root, v);
		return v.compareTo(root.value) == 0;
	}
	
	public Integer get (Integer v) {
		root = splay(root, v);
		if (v.compareTo(root.value) != 0)
			return null;
		return root.key;
	}

	public void remove (Integer v) {
		// empty tree
		if (root == null)
			return;
		// move the key to the root
		root = splay(root, v);
		int cmp = v.compareTo(root.value);

		// if the value exists then remove it
		if (cmp == 0) {
			// if it has only one child
			if (root.left == null)
				root = root.right;
			// move the rightmost node from the left subtree up to replace the
			// node
			else {
				Node right = root.right;
				root = root.left;
				splay(root, v);
				root.right = right;
			}

		}
	}
	
	// splay method
	public Node splay (Node n, Integer v) {
		if (n == null)
			return null;
		int cmp1 = v.compareTo(n.value);

		// going to the left
		if (cmp1 < 0) {
			if (n.left == null)
				return n;
			int cmp2 = v.compareTo(n.left.value);
			// right right rotation
			if (cmp2 < 0) {
				n.left.left = splay(n.left.left, v);
				n = rotateRight(n);
			}
			// left right rotation
			else if (cmp2 > 0) {
				n.left.right = splay(n.left.right, v);
				if (n.left.right != null)
					n.left = rotateLeft(n.left);
			}
			if (n.left == null)
				return n;
			else
				return rotateRight(n);
		}
		// going to the right
		else if (cmp1 > 0) {
			if (n.right == null)
				return n;
			int cmp2 = v.compareTo(n.right.value);
			// right left rotation
			if (cmp2 < 0) {
				n.right.left = splay(n.right.left, v);
				if (n.right.left != null)
					n.right = rotateRight(n.right);
			}
			// left left rotation
			else if (cmp2 > 0) {
				n.right.right = splay(n.right.right, v);
				n = rotateLeft(n);
			}
			if (n.right == null)
				return n;
			else
				return rotateLeft(n);
		}
		// value exists already
		else
			return n;
	}

	// left rotation
	private Node rotateLeft (Node n) {
		Node x = n.right;
		n.right = x.left;
		x.left = n;
		return x;
	}

	// right rotation
	private Node rotateRight (Node n) {
		Node x = n.left;
		n.left = x.right;
		x.right = n;
		return x;
	}
}
