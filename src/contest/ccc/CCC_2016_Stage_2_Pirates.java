package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2016_Stage_2_Pirates {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static long K;
	static int[] V;
	static long[][] dp;
	static boolean contains = false;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		K = readLong();
		
		V = new int[N];

		for (int i = 0; i < N; i++)
			V[i] = readInt();

		out.println(K);
		
		Node treap = null;
		treap = insert(treap, K, 1);

		long inc = 0;
		for (int i = 1; i < N; i++) {
			int needToSatisfy = V[i] - 1;
			if (needToSatisfy == 0) {
				treap = null;
				out.println(K);
				treap = insert(treap, K - inc, 1);
				treap = insert(treap, 0 - inc, i);
			} else {
				long haveToPay = needToSatisfy * inc + querySmallest(treap, needToSatisfy) + needToSatisfy;
				if (haveToPay > K) {
					out.println(-1);
					treap = insert(treap, -1 - inc, 1);
				} else {
					treap = removeLargest(treap, needToSatisfy);
					inc++;
					if (needToSatisfy != i) {
						treap = insert(treap, 0 - inc, i - needToSatisfy);
					}
					treap = insert(treap, K - haveToPay - inc, 1);
					out.println(K - haveToPay);
				}
			}
		}
		out.close();
	}

	static void bf () {
		dp = new long[N][N];
		dp[0][0] = K;
		out.println(K);
		for (int i = 1; i < N; i++) {
			int needToSatisfy = V[i] - 1;
			ArrayList<State> a = new ArrayList<State>();
			for (int j = 0; j < i; j++)
				a.add(new State(j, dp[i - 1][j]));
			Collections.sort(a);
			long needToGive = 0;
			for (int j = 0; j < needToSatisfy; j++) {
				needToGive += a.get(j).val + 1;
			}
			if (needToGive > K) {
				out.println(-1);
				for (State s : a)
					dp[i][s.index] = s.val;
				dp[i][i] = -1;
			} else {
				out.println(K - needToGive);
				dp[i][i] = K - needToGive;
				for (int j = 0; j < needToSatisfy; j++)
					dp[i][a.get(j).index]= a.get(j).val + 1; 
			}
		}
	}

	static class State implements Comparable<State> {
		Long val;
		int index;
		State (int index, long val) {
			this.val = val;
			this.index = index;
		}
		@Override
		public int compareTo(State o) {
			return val.compareTo(o.val);
		}
	}

	static void traverse (Node n) {
		if (n == null) {
			return;
		}
		traverse(n.left);
		out.printf("(%d, %d %d) ", n.val, n.size, n.totalSize);
		traverse(n.right);
	}

	static Node removeLargest (Node n, int amt) {
		if (n == null || amt == 0)
			return null;
		if (n.totalSize == amt)
			return n;
		else if (n.totalSize < amt) {
			// this should never happen
			return null;
		} else {
			if (amt <= getTotalSize(n.left))
				n = removeLargest(n.left, amt);
			else if (amt <= getTotalSize(n.left) + n.size) {
				n.right = null;
				n.size = amt - getTotalSize(n.left);
			} else {
				n.right = removeLargest(n.right, amt - getTotalSize(n.left) - n.size);
			}
		}

		n.totalSize = getTotalSize(n.left) + getTotalSize(n.right) + n.size;
		n.sum = getSum(n.left) + getSum(n.right) + n.val * n.size;
		return n;
	}

	static long querySmallest (Node n, int amt) {
		if (n == null || amt == 0)
			return 0;

		if (n.totalSize == amt) {
			return n.sum;
		} else if (n.totalSize < amt) {
			// this should never happen
			return -1;
		} else {
			if (amt <= getTotalSize(n.left)) {
				return querySmallest(n.left, amt);
			}
			else if (getTotalSize(n.left) + n.size >= amt)
				return querySmallest(n.left, getTotalSize(n.left)) + n.val * (amt - getTotalSize(n.left));
			return getSum(n.left) + querySmallest(n.right, amt - getTotalSize(n.left) - n.size) + n.size * n.val;
		}
	}

	static long getSum (Node n) {
		return n == null ? 0 : n.sum;
	}

	static int getTotalSize (Node n) {
		return n == null ? 0 : n.totalSize;
	}

	static class Node {
		long val;
		int size;
		int totalSize;
		long sum;
		double p;
		Node left, right;
		Node (long val, double p, Node left, Node right, int size) {
			this.val = val;
			this.p = p;
			this.size = size;
			this.left = left;
			this.right = right;
			this.totalSize = getTotalSize(left) + getTotalSize(right) + this.size;
			this.sum = getSum(left) + getSum(right) + this.val * this.size;
		}
	}

	static class Pair {
		Node left, right;
		Pair (Node left, Node right) {
			this.left = left;
			this.right = right;
		}
	}

	static Node merge (Node n, Node m) {
		if (n == null)
			return m;
		if (m == null)
			return n;

		if (n.p < m.p) {
			return new Node(n.val, n.p, n.left, merge(n.right, m), n.size);
		} else {
			return new Node(m.val, m.p, merge(n, m.left), m.right, m.size);
		}
	}

	static Pair split (Node n, long val) {
		if (n == null)
			return new Pair(null, null);
		if (n.val < val) {
			Pair ret = split(n.right, val);
			ret.left = new Node(n.val, n.p, n.left, ret.left, n.size);
			return ret;
		} else if (n.val > val) {
			Pair ret = split(n.left, val);
			ret.right = new Node(n.val, n.p, ret.right, n.right, n.size);
			return ret;
		} else {
			return new Pair(n.left, n.right);
		}
	}

	static Node inc (Node n, long val, int size) {
		if (n == null)
			return null;
		if (n.val < val)
			n.right = inc(n.right, val, size);
		else if (n.val > val)
			n.left = inc(n.left, val, size);
		else {
			n.size += size;
			n.sum += n.val * size;
			contains = true;
		}
		n.sum = getSum(n.left) + getSum(n.right) + n.val * n.size;
		n.totalSize = getTotalSize(n.left) + getTotalSize(n.right) + n.size;
		return n;
	}

	static Node insert (Node n, long val, int size) {
		contains = false;
		inc(n, val, size);
		if (contains)
			return n;
		Pair res = split(n, val);
		Node newNode = new Node(val, Math.random(), null, null, size);
		return merge(res.left, merge(newNode, res.right));
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

