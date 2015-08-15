import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Treap {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		long c = System.currentTimeMillis();
		for (int x = 0; x < 10; x++) {
			int ran = (int) (Math.random() * (20)) + 5;
			System.out.print(ran + " ");
			add(ran);
		}
		System.out.println();
		traverse(root);
		System.out.println();
		add(1);
		System.out.println(contains(root, 1));
		System.out.println(contains(root, 2));
		remove(1);
		System.out.println(contains(root, 1));
		System.out.println(System.currentTimeMillis() - c);
	}

	static Node root = null;

	static class Node {
		Integer value;
		Double priority;
		Node left, right;

		Node (int value) {
			this.value = value;
			priority = Math.random();
		}
	}

	public static void remove (Integer v) {
		root = remove(root, v);
	}

	public static void add (Integer v) {
		root = add(root, v);
	}

	public static void traverse (Node n) {
		if (n == null)
			return;
		traverse(n.left);
		System.out.print(n.value + " ");
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

	// auxiliary function to delete
	private static Node remove (Node n, Integer v) {
		if (n == null)
			return n;
		int cmp = v.compareTo(n.value);
		if (cmp < 0)
			n.left = remove(n.left, v);
		else if (cmp > 0)
			n.right = remove(n.right, v);
		else {
			n = merge(n.left, n.right);
		}
		return n;
	}

	// auxiliary function to merge
	private static Node merge (Node t1, Node t2) {
		if (t1 == null)
			return t2;
		else if (t2 == null)
			return t1;

		Node newRoot = null;
		if (t1.priority > t2.priority) {
			t1.left = merge(t1.left, t1.right);
			newRoot = t1;
			newRoot.right = t2;
		} else {
			t2.right = merge(t2.left, t2.right);
			newRoot = t2;
			newRoot.left = t1;
		}
		return newRoot;
	}

	// auxiliary function to insert
	private static Node add (Node n, Integer v) {
		if (n == null)
			return new Node(v);
		int cmp = v.compareTo(n.value);
		// going left
		if (cmp < 0) {
			n.left = add(n.left, v);
			if (n.priority < n.left.priority) {
				n = rotateRight(n);
			}
		}
		// going right
		else if (cmp > 0) {
			n.right = add(n.right, v);
			if (n.priority < n.right.priority)
				n = rotateLeft(n);
		}
		// else the value already exists
		return n;
	}

	// rotate left
	private static Node rotateLeft (Node n) {
		Node x = n.right;
		n.right = x.left;
		x.left = n;
		return x;
	}

	// rotate right
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
