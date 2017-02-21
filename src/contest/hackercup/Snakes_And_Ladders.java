package contest.hackercup;

import java.util.*;
import java.io.*;

public class Snakes_And_Ladders {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int T = readInt();
		for (int t = 1; t <= T; t++) {
			root = null;
			int n = readInt();
			Ladder[] l = new Ladder[n];
			for (int i = 0; i < n; i++)
				l[i] = new Ladder(readInt(), readInt());
			Arrays.sort(l);
			long ans = 0;
			for (int i = 0; i < n; i++) {
				Long firstKey = getFirst(root, l[i].h);
				while (firstKey != null && firstKey < l[i].h) {
					remove(firstKey);
					firstKey = getFirst(root, l[i].h);
				}
				if (firstKey != null && firstKey == l[i].h) {
					State add = get(l[i].h);
					ans += (add.addValue + add.sz * l[i].x * l[i].x);
					ans -= 2 * l[i].x * (add.total);
					update(l[i].h, l[i].x);
				} else {
					add(l[i].h, l[i].x);
				}
			}
			out.printf("Case #%d: %d\n", t, ans);
		}

		out.close();
	}

	static class Ladder implements Comparable<Ladder> {
		long x, h;

		Ladder (long x, long h) {
			this.x = x;
			this.h = h;
		}

		@Override
		public int compareTo (Ladder o) {
			if (x == o.x)
				return new Long(x).compareTo(new Long(o.h));
			return new Long(x).compareTo(new Long(o.x));
		}

	}

	// root of the tree
	static Node root = null;

	// object representing the nodes of the tree
	static class Node {
		long key;
		long value;
		long addValue;
		long sz;
		double priority;
		Node left, right;

		Node (long key, long value) {
			this.key = key;
			this.value = value;
			this.addValue = value * value;
			this.sz = 1;
			priority = Math.random();
		}

		Node (long key) {
			this.key = key;
			this.value = key;
			this.addValue = value * value;
			this.sz = 1;
			priority = Math.random();
		}
	}

	private static void update (long key, long value) {
		root = update(root, key, value);
	}

	private static Node update (Node n, long key, long value) {
		if (n.key == key) {
			n.value += value;
			n.addValue += value * value;
			n.sz++;
			return n;
		}
		if (n.key < key)
			return update(n.left, key, value);
		else
			return update(n.right, key, value);
	}

	private static Long getFirst (Node n, long key) {
		if (n == null)
			return null;
		if (n.left == null)
			return n.key;
		return getFirst(n.left, key);
	}

	// object representing a pair of nodes of the tree
	static class NodePair {
		Node left, right;

		NodePair (Node left, Node right) {
			this.left = left;
			this.right = right;
		}
	}

	public static void remove (long k) {
		root = remove(root, k);
	}

	public static void add (long k) {
		root = add(root, new Node(k));
	}

	public static void add (long k, long v) {
		root = add(root, new Node(k, v));
	}

	public static boolean contains (long k) {
		return contains(root, k);
	}

	public static State get (long k) {
		return get(root, k);
	}

	// auxiliary function for contains
	private static boolean contains (Node n, long k) {
		if (n == null)
			return false;
		long cmp = k - n.key;
		if (cmp < 0)
			return contains(n.left, k);
		else if (cmp > 0)
			return contains(n.right, k);
		return true;
	}

	static class State {
		long total, sz, addValue;

		State (long total, long sz, long addValue) {
			this.total = total;
			this.sz = sz;
			this.addValue = addValue;
		}
	}

	// auxiliary function for get
	private static State get (Node n, long k) {
		if (n == null)
			return null;
		long cmp = k - n.key;
		if (cmp < 0)
			return get(n.left, k);
		else if (cmp > 0)
			return get(n.right, k);
		return new State(n.value, n.sz, n.addValue);
	}

	// auxiliary function to delete
	private static Node remove (Node n, long k) {
		if (n == null)
			return n;
		long cmp = k - n.key;
		if (cmp < 0)
			n.left = remove(n.left, k);
		else if (cmp > 0)
			n.right = remove(n.right, k);
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
			t1.right = merge(t1.right, t2);
			newRoot = t1;
		} else {
			t2.left = merge(t1, t2.left);
			newRoot = t2;
		}
		return newRoot;
	}

	// auxiliary function to split
	private static NodePair split (Node n, long key) {
		NodePair res = new NodePair(null, null);
		if (n == null)
			return res;

		if (n.key > key) {
			res = split(n.left, key);
			n.left = res.right;
			res.right = n;
			return res;
		} else if (n.key < key) {
			res = split(n.right, key);
			n.right = res.left;
			res.left = n;
			return res;
		} else {
			return new NodePair(n.left, n.right);
		}
	}

	// auxiliary function to insert
	private static Node add (Node n, Node m) {
		if (n == null)
			return m;
		if (m.priority > n.priority) {
			NodePair pair = split(n, m.key);
			m.left = pair.left;
			m.right = pair.right;
			return m;
		} else {
			long cmp = n.key - m.key;
			if (cmp < 0)
				n.right = add(n.right, m);
			else if (cmp > 0)
				n.left = add(n.left, m);
			else
				n.value = m.value;
		}
		return n;
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
