import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Splay_Tree {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		long c = System.currentTimeMillis();
		for (int x = 0; x < 100; x++) {
			int ran = (int) (Math.random() * (100)) + 5;
			add(ran);
		}
		traverse(root);
		add(1);
		System.out.println(contains(1));
		System.out.println(contains(2));
		remove(1);
		System.out.println(contains(1));
		// System.out.println(System.currentTimeMillis()-c);
	}

	static Node root = null;

	static class Node implements Comparable<Node> {
		int value;
		Node left, right;

		Node (int value) {
			this.value = value;
		}

		@Override
		public int compareTo (Node o) {
			return value - o.value;
		}
	}

	private static void traverse (Node n) {
		if (n == null)
			return;
		traverse(n.left);
		System.out.println(n.value);
		traverse(n.right);
	}

	private static void add (Integer v) {
		// empty tree
		if (root == null) {
			root = new Node(v);
			return;
		}
		root = splay(root, v);

		int cmp = v.compareTo(root.value);
		// take left subtree and put it as left, and the left subtree and root
		// and put it as right
		if (cmp < 0) {
			Node newRoot = new Node(v);
			newRoot.left = root.left;
			newRoot.right = root;
			root.left = null;
			root = newRoot;
		}
		// take right subtree and put it as right, and the left subtree and root
		// and put it as left
		else if (cmp > 0) {
			Node newRoot = new Node(v);
			newRoot.right = root.right;
			newRoot.left = root;
			root.right = null;
			root = newRoot;
		}
		// else the value already exists
	}

	public static boolean contains (Integer v) {
		root = splay(root, v);
		return v.compareTo(root.value) == 0;
	}

	public static void remove (Integer v) {
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

	public static Node splay (Node n, Integer v) {
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
	private static Node rotateLeft (Node n) {
		Node x = n.right;
		n.right = x.left;
		x.left = n;
		return x;
	}

	// right rotation
	private static Node rotateRight (Node n) {
		Node x = n.left;
		n.left = x.right;
		x.right = n;
		return x;
	}

	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong () throws IOException {
		return Long.parseLong(next());
	}

	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
