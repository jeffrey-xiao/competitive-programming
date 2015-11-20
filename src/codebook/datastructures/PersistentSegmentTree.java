/* 
 * Persistent Segment Tree implementation
 * Reference problem: http://www.spoj.com/problems/MKTHNUM/
 *
 * Time complexity:
 *  - Update: O(log N)
 *  - Query: O(log N)
 *  - Add element: O(log N)
 */

package codebook.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class PersistentSegmentTree {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, q;
	static TreeSet<Integer> ts = new TreeSet<Integer>();
	static int[] toVal, a;
	static java.util.HashMap<Integer, Integer> toIndex = new java.util.HashMap<Integer, Integer>();
	static Node[] tree;

	// tree[i] represents the count of the numbers from [1, i]
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		q = readInt();

		toVal = new int[n + 1];
		a = new int[n + 1];

		for (int i = 1; i <= n; i++)
			ts.add(a[i] = readInt());
		int cnt = 0;
		for (int val : ts) {
			toIndex.put(val, ++cnt);
			toVal[cnt] = val;
		}
		tree = new Node[n + 1];
		tree[0] = new Node(0);
		tree[0].left = tree[0].right = tree[0];

		for (int i = 1; i <= n; i++)
			tree[i] = update(tree[i - 1], 1, cnt, toIndex.get(a[i]));
		for (int i = 0; i < q; i++) {
			int l = readInt();
			int r = readInt();
			int k = readInt();
			out.println(toVal[query(tree[l - 1], tree[r], 1, cnt, k)]);
		}
		out.close();
	}

	static Node update (Node prev, int l, int r, int val) {
		if (l <= val && val <= r) {
			if (l == r)
				return new Node(prev.cnt + 1);
			int mid = (l + r) >> 1;
			return new Node(prev.cnt + 1, update(prev.left, l, mid, val), update(prev.right, mid + 1, r, val));
		}
		return prev;
	}

	static int query (Node lo, Node hi, int l, int r, int val) {
		if (l == r)
			return l;
		int mid = (l + r) >> 1;
		int count = hi.left.cnt - lo.left.cnt;
		if (val <= count)
			return query(lo.left, hi.left, l, mid, val);
		else
			return query(lo.right, hi.right, mid + 1, r, val - count);
	}

	static class Node {
		Node left, right;
		int cnt;

		Node (int cnt) {
			this(cnt, null, null);
		}

		Node (int cnt, Node left, Node right) {
			this.cnt = cnt;
			this.left = left;
			this.right = right;
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
