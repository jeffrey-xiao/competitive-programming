package contest.spoj;

import java.util.*;
import java.io.*;

public class Mkthnum {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[][] tree;
	static int[] a;
	static int n, q;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		q = readInt();
		a = new int[n];
		tree = new int[4 * n][];
		for (int i = 0; i < n; i++)
			a[i] = readInt();
		build(1, 1, n);
		for (int i = 0; i < q; i++) {
			int x = readInt();
			int y = readInt();
			int z = readInt();
			out.println(query(x, y, z));
		}
		out.close();
	}

	static int[] merge (int[] a, int[] b) {
		int[] res = new int[a.length + b.length];
		int j = 0;
		int k = 0;
		for (int i = 0; i < res.length; i++) {
			if (k == b.length || (j != a.length && a[j] < b[k]))
				res[i] = a[j++];
			else
				res[i] = b[k++];
		}
		return res;
	}

	static void build (int n, int l, int r) {
		tree[n] = new int[(r - l) + 1];
		if (l == r) {
			tree[n][0] = a[l - 1];
			return;
		}
		int mid = (l + r) >> 1;
		build(n << 1, l, mid);
		build(n << 1 | 1, mid + 1, r);
		tree[n] = merge(tree[n << 1], tree[n << 1 | 1]);
	}

	static int query (int l, int r, int k) {
		int lo = -1000000000, hi = 1000000000;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (query(1, 1, n, l, r, mid) < k)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return lo;
	}

	static int query (int n, int l, int r, int ql, int qr, int val) {
		if (ql == l && qr == r) {
			int lo = 0, hi = r - l;
			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				if (tree[n][mid] <= val)
					lo = mid + 1;
				else
					hi = mid - 1;
			}
			return lo;
		}
		int mid = (l + r) >> 1;
		if (qr <= mid)
			return query(n << 1, l, mid, ql, qr, val);
		else if (ql > mid)
			return query(n << 1 | 1, mid + 1, r, ql, qr, val);
		else
			return query(n << 1, l, mid, ql, mid, val) + query(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
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
