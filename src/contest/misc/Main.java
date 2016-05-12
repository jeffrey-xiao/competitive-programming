package contest.misc;

import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[] id, sz;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		TreeSet<Integer> ts = new TreeSet<Integer>();
		Node treap = null;
		long time = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			int rand = (int)(Math.random() * 1000000000);
			treap = insert(treap, rand);
			ts.add(rand);
//			assert(contains(treap, rand));
//			System.out.println(i);
		}
		System.out.println(treap.size);
		assert(treap.size == ts.size());
//		for (int i : ts) {
//			treap = delete(treap, i);
//			assert(!contains(treap, i));
//		}
//		assert(treap == null);
		System.out.println(System.currentTimeMillis() - time);
		out.close();
	}

	static void traverse (Node n) {
		if (n == null)
			return;
		traverse(n.left);
		System.out.print(n.val + " ");
		traverse(n.right);
	}
	
	static Node merge (Node a, Node b) {
		if (a == null)
			return b;
		if (b == null)
			return a;
		if (a.p < b.p) {
			a.right = merge(a.right, b);
			a = updateSize(a);
			return a;
//			return new Node(a.val, Math.random(), a.left, merge(a.right, b));
		} else {
			b.left = merge(a, b.left);
			b = updateSize(b);
			return b;
//			return new Node(b.val, Math.random(), merge(a, b.left), b.right);
		}
	}

	static Pair<Node, Node> split (Node a, int k) {
		if (a == null)
			return new Pair<Node, Node>(null, null);
		if (a.val < k) {
			Pair<Node, Node> ret = split(a.right, k);
			a.right = ret.first;
			a = updateSize(a);
			ret.first = a;
//			ret.first = merge(new Node(a.val, a.p, a.left, null), ret.first);
			return ret;
		} else if (a.val > k) {
			Pair<Node, Node> ret = split(a.left, k);
			a.left = ret.second;
			a = updateSize(a);
			ret.second = a;
//			ret.second = merge(ret.second, new Node(a.val, a.p, null, a.right));
			return ret;
		} else {
			return new Pair<Node, Node>(a.left, a.right);
		}
	}
	
	static Node insert (Node n, int val) {
		Node ret = new Node(val, Math.random(), null, null);
		Pair<Node, Node> nodes = split(n, val);
		return merge(nodes.first, merge(ret, nodes.second));
	}
	
	static Node delete (Node n, int val) {
		Pair<Node, Node> nodes = split(n, val);
		return merge(nodes.first, nodes.second);
	}
	
	static boolean contains (Node n, int val) {
		if (n == null)
			return false;
		
		if (n.val < val)
			return contains(n.right, val);
		else if (n.val > val)
			return contains(n.left, val);
		return true;
	}
	
	static int getSize (Node n) {
		if (n == null)
			return 0;
		return n.size;
	}
	
	static Node updateSize (Node n) {
		n.size = getSize(n.left) + getSize(n.right) + 1;
		return n;
	}
	
	static class Node {
		int val, size;
		double p;
		Node left, right;
		
		Node (int val, double p, Node left, Node right) {
			this.val = val;
			this.size = getSize(left) + getSize(right) + 1;
			this.left = left;
			this.right = right;
			this.p = p;
		}
	}
	
	static class Pair<A, B> {
		A first;
		B second;
		Pair (A first, B second) {
			this.first = first;
			this.second = second;
		}
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

