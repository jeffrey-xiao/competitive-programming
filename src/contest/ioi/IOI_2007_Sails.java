package contest.ioi;

import java.util.*;
import java.io.*;

public class IOI_2007_Sails {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static Node root = null;
	static Node toAdd = null;
	static Node removed = null;
	static int newL;

	static int n;
	static long[] diff;
	static Mast[] m;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		Mast[] m = new Mast[n];
		for (int i = 0; i < n; i++)
			m[i] = new Mast(readInt(), readInt());

		Arrays.sort(m);
		root = add(root, new Node(1, m[n-1].height));
		diff = new long[m[n-1].height + 2];
		Queue<Node> q = new ArrayDeque<Node>();
		for (int i = 0; i < n; i++) {
			int left = m[i].height - m[i].cnt + 1;
			int right = m[i].height;
			toAdd = null;
			root = splitFirst(root, left, right);
			if (toAdd != null) {
				diff[toAdd.l]++;
				diff[toAdd.r+1]--;
				left += toAdd.r - toAdd.l + 1;
				q.offer(toAdd);
			}
			toAdd = null;
			root = splitLast(root, left, right);
			if (toAdd != null) {
				diff[toAdd.l]++;
				diff[toAdd.r+1]--;
				right = toAdd.l - 1;
				q.offer(toAdd);
			}
			if (left <= right) {
				diff[left]++;
				diff[right+1]--;
			}
			while (!q.isEmpty())
				root = add(root, q.poll());
			if (left <= right)
				merge(left, right);
		}
		long sum = 0;
		for (int i = 1; i <= m[n-1].height; i++) {
			diff[i] += diff[i-1];
			sum += (diff[i] * (diff[i]-1))/2;
		}
		out.println(sum);
		out.close();
	}
	
	static void traverse (Node n) {
		if (n == null)
			return;
		traverse(n.left);
		out.println("IN TRAVERSE " + n.l + " " + n.r);
		traverse(n.right);
	}
	
	static void merge (int left, int right) {
		if (diff[left] == 0) {
			removed = null;
			root = remove(root, left);
			root = add(root, removed);
		}
		if (diff[right+1] == 0 && right + 1 <= m[n-1].height) {
			removed = null;
			root = remove(root, right+1);
			root = add(root, removed);
		}
	}
	static Node splitLast (Node n, int left, int right) {
		if (n == null || n.r == right || left > right)
			return n;
		if (n.r > right && right >= n.l) {
			int end = right;
			int start = Math.max(left, n.l);
			int sz = end - start + 1;
			toAdd = new Node(n.l, n.l + sz - 1);
			n.l = n.l + sz;
			return n;
		} else if (right < n.r) {
			n.left = splitLast(n.left, left, right);
		} else {
			n.right = splitLast(n.right, left, right);
		}
		return n;
	}

	static Node splitFirst (Node n, int left, int right) {
		if (n == null || n.l == left || left > right)
			return n;
		if (n.l < left && left <= n.r) {
			int end = Math.min(n.r, right);
			int start = left;
			int sz = end - start + 1;
			toAdd = new Node(n.l, n.l + sz - 1);
			n.l = n.l + sz;
			newL = n.r + 1;
			return n;
		} else if (left < n.l) {
			n.left = splitFirst(n.left, left, right);
		} else {
			n.right = splitFirst(n.right, left, right);
		}
		return n;
	}

	static class Mast implements Comparable<Mast> {
		int height, cnt;
		Mast (int height, int cnt) {
			this.height = height;
			this.cnt = cnt;
		}
		@Override
		public int compareTo (Mast m) {
			return height - m.height;
		}
	}

	static Node remove (Node n, Integer k) {
		if (n == null)
			return n;
		int cmp = k - n.l;
		if (cmp < 0)
			n.left = remove(n.left, k);
		else if (cmp > 0)
			n.right = remove(n.right, k);
		else {
			if (removed == null)
				removed = new Node(n.l, n.r);
			if (n.left == null) {
				n = n.right;
				return n;
			} else if (n.right == null) {
				n = n.left;
				return n;
			} else {
				Node replace = minV(n.right);
				n.l = replace.l;
				n.r = replace.r;
				n.right = remove(n.right, n.l);
				return n;
			}
		}
		return balance(n);
	}

	static Node minV (Node n) {
		while (n.left != null)
			n = n.left;
		return n;
	}

	static Node add (Node n, Node add) {
		if (n == null)
			return add;
		if (n.r + 1 == add.l && diff[add.l] == 0) {
			n.r = add.r;
			return n;
		}
		int cmp = add.l - n.l;
		if (cmp < 0)
			n.left = add(n.left, add);
		else if (cmp > 0)
			n.right = add(n.right, add);
		return balance(n);
	}

	static class Node {
		int l, r, height;
		Node left, right;
		Node (int l, int r) {
			this.l = l;
			this.r = r;
		}
	}

	static int getHeight (Node n) {
		return n == null ? -1 : n.height;
	}

	static void resetHeight (Node n) {
		n.height = Math.max(getHeight(n.left), getHeight(n.right)) + 1;
	}

	static Node rotateLeft (Node n) {
		Node x = n.right;
		n.right = x.left;
		x.left = n;
		resetHeight(n);
		resetHeight(x);
		return x;
	}

	static Node rotateRight (Node n) {
		Node x = n.left;
		n.left = x.right;
		x.right = n;
		resetHeight(n);
		resetHeight(x);
		return x;
	}

	static Node balance (Node n) {
		resetHeight(n);
		int diff1 = getHeight(n.left) - getHeight(n.right);
		if (diff1 >= 2) {
			int diff2 = getHeight(n.left.right) - getHeight(n.left.left);
			if (diff2 > 0)
				n.left = rotateLeft(n.left);
			n = rotateRight(n);
		} else if (diff1 <= -2) {
			int diff2 = getHeight(n.right.left) - getHeight(n.right.right);
			if (diff2 > 0)
				n.right = rotateRight(n.right);
			n = rotateLeft(n);
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

