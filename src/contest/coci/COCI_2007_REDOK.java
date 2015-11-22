package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2007_REDOK {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m;
	static char[] in;
	static int[][] tree;
	static int[] lazy;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		in = (" " + readLine()).toCharArray();

		tree = new int[4 * n][10];
		lazy = new int[4 * n];
		build(1, 1, n);
		for (int i = 0; i < m; i++) {
			int l = readInt();
			int r = readInt();
			out.println(query(1, 1, n, l, r));
			update(1, 1, n, l, r, 1);
		}

		out.close();
	}

	static void build (int n, int l, int r) {
		if (l == r) {
			tree[n][in[l] - '0']++;
			return;
		}
		int mid = (l + r) >> 1;
		build(n << 1, l, mid);
		build(n << 1 | 1, mid + 1, r);
		for (int i = 0; i < 10; i++)
			tree[n][i] = tree[n << 1][i] + tree[n << 1 | 1][i];
	}

	static int query (int n, int l, int r, int ql, int qr) {
		if (l == ql && r == qr) {
			int res = 0;
			for (int i = 0; i < 10; i++)
				res += i * tree[n][i];
			return res;
		}
		if (lazy[n] != 0) {
			lazy[n << 1] += lazy[n];
			lazy[n << 1 | 1] += lazy[n];

			tree[n << 1] = shift(tree[n << 1], lazy[n]);
			tree[n << 1 | 1] = shift(tree[n << 1 | 1], lazy[n]);

			lazy[n] = 0;
		}
		int mid = (l + r) >> 1;
		if (qr <= mid)
			return query(n << 1, l, mid, ql, qr);
		else if (ql > mid)
			return query(n << 1 | 1, mid + 1, r, ql, qr);
		else
			return query(n << 1, l, mid, ql, mid) + query(n << 1 | 1, mid + 1, r, mid + 1, qr);
	}

	static void update (int n, int l, int r, int ql, int qr, int val) {
		if (l == ql && r == qr) {
			lazy[n] += val;
			tree[n] = shift(tree[n], val);
			return;
		}
		if (lazy[n] != 0) {
			lazy[n << 1] += lazy[n];
			lazy[n << 1 | 1] += lazy[n];

			tree[n << 1] = shift(tree[n << 1], lazy[n]);
			tree[n << 1 | 1] = shift(tree[n << 1 | 1], lazy[n]);

			lazy[n] = 0;
		}
		int mid = (l + r) >> 1;
		if (qr <= mid)
			update(n << 1, l, mid, ql, qr, val);
		else if (ql > mid)
			update(n << 1 | 1, mid + 1, r, ql, qr, val);
		else {
			update(n << 1, l, mid, ql, mid, val);
			update(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
		}
		for (int i = 0; i < 10; i++)
			tree[n][i] = tree[n << 1][i] + tree[n << 1 | 1][i];
	}

	static int[] shift (int[] a, int num) {
		int[] res = new int[10];
		for (int i = 0; i < 10; i++)
			res[(i + num) % 10] = a[i];
		return res;
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
