import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AVL_Tree {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		long c = System.currentTimeMillis();
		for (int x = 0; x < 1000000; x++) {
			int ran = (int) (Math.random() * (1 << 30)) + 5;
			add(ran);
		}
		// traverse(root);
		add(1);
		System.out.println(contains(root, 1));
		System.out.println(contains(root, 2));
		remove(1);
		System.out.println(contains(root, 1));
		System.out.println(System.currentTimeMillis() - c);
		// add(9);
		// add(5);
		// add(10);
		// add(0);
		// add(6);
		// add(11);
		// add(-1);
		// add(1);
		// add(2);
		// traverse(root);
		// remove(10);
		// System.out.println();
		// traverse(root);

	}

	static Node root = null;

	static class Node {
		Integer value, height;
		Node left, right;

		Node (int value) {
			this.value = value;
			this.height = 0;
		}

	}

	// methods to reset and get the height of a node
	private static void resetHeight (Node n) {
		n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;
	}

	private static int getHeight (Node n) {
		return n == null ? -1 : n.height;
	}

	public static void traverse (Node n) {
		if (n == null)
			return;
		System.out.println(n.value);
		traverse(n.left);
		traverse(n.right);
	}

	public static boolean contains (Node n, Integer v) {
		if (n == null)
			return false;
		int cmp = v.compareTo(n.value);
		if (cmp < 0)
			return contains(n.left, v);
		else if (cmp > 0)
			return contains(n.right, v);
		return true;
	}

	public static void remove (int v) {
		root = remove(root, v);
	}

	@SuppressWarnings ("unused")
	private static Node remove (Node n, Integer v) {
		if (n == null)
			return n;
		int cmp = v.compareTo(n.value);
		if (cmp < 0)
			n.left = remove(n.left, v);
		else if (cmp > 0)
			n.right = remove(n.right, v);
		else {
			if (n.left == null) {
				n = n.right;
				return n;
			} else if (n.right == null) {
				n = n.left;
				return n;
			} else {
				Node replace = minV(n.right);
				n.value = replace.value;
				n.right = remove(n.right, n.value);
			}
		}
		if (n == null)
			return n;
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
		} else
			resetHeight(n);
		return n;
	}

	private static Node minV (Node n) {
		while (n.left != null)
			;
		n = n.left;
		return n;
	}

	public static void add (int v) {
		root = add(root, v);
	}

	private static Node add (Node n, Integer v) {
		if (n == null)
			return new Node(v);
		int cmp = v.compareTo(n.value);
		if (cmp < 0)
			n.left = add(n.left, v);
		else if (cmp > 0)
			n.right = add(n.right, v);

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
		} else
			resetHeight(n);
		return n;
	}

	// rotate left
	private static Node rotateLeft (Node n) {
		Node x = n.right;
		n.right = x.left;
		x.left = n;
		resetHeight(n);
		resetHeight(x);
		return x;
	}

	// rotate right
	private static Node rotateRight (Node n) {
		Node x = n.left;
		n.left = x.right;
		x.right = n;
		resetHeight(n);
		resetHeight(x);
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
