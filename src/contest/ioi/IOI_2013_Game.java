package contest.ioi;

import java.util.*;
import java.io.*;

public class IOI_2013_Game {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final Random generator = new Random(0);
	static int R, C, N;
	static Seg root;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		R = readInt();
		C = readInt();
		N = readInt();

		root = new Seg(0, R - 1);
		for (int t = 0; t < N; t++) {
			int cmd = readInt();
			if (cmd == 1) {
				int r = readInt();
				int c = readInt();
				long val = readLong();
				root = update(root, r, c, val);
			} else {
				int r1 = readInt();
				int c1 = readInt();
				int r2 = readInt();
				int c2 = readInt();
				out.println(query(root, r1, r2, c1, c2));
			}
		}

		out.close();
	}

	static long query (Seg s, int r1, int r2, int c1, int c2) {
		if (s == null)
			return 0;
		if (r1 == s.l && s.r == r2) {
			return query(s.treap, c1, c2);
		}

		int mid = (s.l + s.r) >> 1;
		if (r2 <= mid)
			return query(s.left, r1, r2, c1, c2);
		else if (r1 > mid)
			return query(s.right, r1, r2, c1, c2);
		else
			return gcd(query(s.left, r1, mid, c1, c2), query(s.right, mid + 1, r2, c1, c2));
	}
	
	static void check (Node n) {
		if (n == null)
			return;
		check(n.left);
		check(n.right);
		n = update(n);
	}
	
	static long lastVal;
	
	static Seg update (Seg s, int r, int c, long val) {
		if (s.l == s.r) {
			s.treap = add(s.treap, c, val);
			lastVal = val;
			return s;
		}
		
		int mid = (s.l + s.r) >> 1;
		if (r <= mid) {
			if (s.left == null)
				s.left = new Seg(s.l, mid);
			s.left = update(s.left, r, c, val);
			if (s.right != null)
				lastVal = gcd(lastVal, query(s.right.treap, c, c));
			s.treap = add(s.treap, c, lastVal);
		} else {
			if (s.right == null)
				s.right = new Seg(mid + 1, s.r);
			s.right = update(s.right, r, c, val);
			if (s.left != null)
				lastVal = gcd(lastVal, query(s.left.treap, c, c));
			s.treap = add(s.treap, c, lastVal);
		}
		return s;
	}

	static class Seg {
		int l, r;
		Node treap;
		Seg left, right;
		Seg (int l, int r) {
			this.l = l;
			this.r = r;
		}
	}

	static class Node {
		double priority;
		int key;
		long value, agg;
		Node left, right;
		int min, max;

		Node (int key, long value) {
			this.priority = generator.nextDouble();
			this.min = this.max = this.key = key;
			this.value = this.agg = value;
		}
	}

	static class NodePair {
		Node left, right;
		NodePair (Node left, Node right) {
			this.left = left;
			this.right = right;
		}
	}

	static long query (Node n, int l, int r) {
		if (n == null)
			return 0;
		if (l <= n.min && n.max <= r) {
			return n.agg;
		}
		if (n.max < l || n.min > r)
			return 0;
		
		long ret = 0;
		if (l <= n.key && n.key <= r)
			ret = n.value;
		
		if (r <= n.key)
			ret = gcd(ret, query(n.left, l, r));
		else if (l >= n.key)
			ret = gcd(ret, query(n.right, l, r));
		else
			ret = gcd(ret, gcd(query(n.left, l, r), query(n.right, l, r)));
		return ret;
	}

	static Node add (Node n, int key, long value) {
		NodePair res = split(n, key);
		return merge(res.left, merge(new Node(key, value), res.right));
	}

	static Node merge (Node t1, Node t2) {
		if (t1 == null) {
			t2 = update(t2);
			return t2;
		}
		if (t2 == null) {
			t1 = update(t1);
			return t1;
		}
		if (t1.priority > t2.priority) {
			t1.right = merge(t1.right, t2);
			t1 = update(t1);
			return t1;
		} else {
			t2.left = merge(t1, t2.left);
			t2 = update(t2);
			return t2;
		}
	}

	static NodePair split (Node n, int key) {
		NodePair res = new NodePair(null, null);
		if (n == null)
			return res;

		if (n.key > key) {
			res = split(n.left, key);
			n.left = res.right;
			res.right = n;
			res.left = update(res.left);
			res.right = update(res.right);
			return res;
		} else if (n.key < key) {
			res = split(n.right, key);
			n.right = res.left;
			res.left = n;
			res.left = update(res.left);
			res.right = update(res.right);
			return res;
		} else {
			return new NodePair(n.left, n.right);
		}
	}

	static Node update (Node n) {
		if (n == null)
			return n;
		n.max = n.min = n.key;
		n.agg = n.value;

		if (n.left != null) {
			n.min = Math.min(n.min, n.left.min);
			n.agg = gcd(n.agg, n.left.agg);
		}

		if (n.right != null) {
			n.max = Math.max(n.max, n.right.max);
			n.agg = gcd(n.agg, n.right.agg);
		}

		return n;
	}

	static long gcd (long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
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

