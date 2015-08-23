package codebook.datastructures;
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

		Node (int key) {
			this.key = key;
			this.value = key;
		}
		
		Node (int key, int value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public int compareTo (Node o) {
			return key - o.key;
		}
	}

	// in order traversal of the tree
	public void traverse (Node n) {
		if (n == null)
			return;
		traverse(n.left);
		System.out.println(n.key);
		traverse(n.right);
	}

	public void add (Integer k) {
		add(k, k);
	}
	
	public void add (Integer k, Integer v) {
		// empty tree
		if (root == null) {
			root = new Node(k, v);
			return;
		}
		root = splay(root, k);

		int cmp = k.compareTo(root.key);
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

	public boolean contains (Integer k) {
		root = splay(root, k);
		return k.compareTo(root.key) == 0;
	}
	
	public Integer get (Integer k) {
		root = splay(root, k);
		if (k.compareTo(root.key) != 0)
			return null;
		return root.value;
	}

	public void remove (Integer k) {
		// empty tree
		if (root == null)
			return;
		// move the key to the root
		root = splay(root, k);
		int cmp = k.compareTo(root.key);

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
				splay(root, k);
				root.right = right;
			}

		}
	}
	
	// splay method
	public Node splay (Node n, Integer k) {
		if (n == null)
			return null;
		int cmp1 = k.compareTo(n.key);

		// going to the left
		if (cmp1 < 0) {
			if (n.left == null)
				return n;
			int cmp2 = k.compareTo(n.left.key);
			// right right rotation
			if (cmp2 < 0) {
				n.left.left = splay(n.left.left, k);
				n = rotateRight(n);
			}
			// left right rotation
			else if (cmp2 > 0) {
				n.left.right = splay(n.left.right, k);
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
			int cmp2 = k.compareTo(n.right.key);
			// right left rotation
			if (cmp2 < 0) {
				n.right.left = splay(n.right.left, k);
				if (n.right.left != null)
					n.right = rotateRight(n.right);
			}
			// left left rotation
			else if (cmp2 > 0) {
				n.right.right = splay(n.right.right, k);
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
