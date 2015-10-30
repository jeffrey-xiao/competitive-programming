package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IOI_2015_Horses {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int MOD = 1000000007;
	static int n, m;
	static double[] a;
	static double[] tree;
	static int[] max;
	static double[] lazy;
	static long[] mult;
	static long[] x, y;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		x = new long[n + 1];
		y = new long[n + 1];
		a = new double[n + 1];
		tree = new double[4 * n];
		lazy = new double[4 * n];
		mult = new long[4 * n];
		max = new int[4 * n];
		for (int i = 1; i <= n; i++)
			a[i] = Math.log(x[i] = readLong()) + a[i - 1];
		for (int i = 1; i <= n; i++)
			a[i] += Math.log(y[i] = readLong());
		build1(1, 1, n);
		build2(1, 1, n);
		m = readInt();
		out.println(query2(1, 1, n, 1, max[1]) * y[max[1]] % MOD);
		for (int i = 0; i < m; i++) {
			int a = readInt();
			int b = readInt() + 1;
			int c = readInt();
			if (a == 1) {
				double diff = Math.log(c) - Math.log(x[b]);
				x[b] = c;
				update1(1, 1, n, b, n, diff);
				update2(1, 1, n, b);
			} else {
				double diff = Math.log(c) - Math.log(y[b]);
				y[b] = c;
				update1(1, 1, n, b, b, diff);
			}
			out.println(query2(1, 1, n, 1, max[1]) * y[max[1]] % MOD);
		}
		out.close();
	}

	static void build2 (int n, int l, int r) {
		if (l == r) {
			mult[n] = x[l];
			return;
		}
		int mid = (l + r) >> 1;
		build2(n << 1, l, mid);
		build2(n << 1 | 1, mid + 1, r);
		mult[n] = (mult[n << 1] * mult[n << 1 | 1]) % MOD;
	}

	static void update2 (int n, int l, int r, int i) {
		if (l == i && r == i) {
			mult[n] = x[i];
			return;
		}
		int mid = (l + r) >> 1;
		if (i <= mid)
			update2(n << 1, l, mid, i);
		else
			update2(n << 1 | 1, mid + 1, r, i);
		mult[n] = (mult[n << 1] * mult[n << 1 | 1]) % MOD;
	}

	static long query2 (int n, int l, int r, int ql, int qr) {
		if (l == ql && r == qr)
			return mult[n];
		int mid = (l + r) >> 1;
		if (qr <= mid)
			return query2(n << 1, l, mid, ql, qr);
		else if (ql > mid)
			return query2(n << 1 | 1, mid + 1, r, ql, qr);
		return (query2(n << 1, l, mid, ql, mid) * query2(n << 1 | 1, mid + 1, r, mid + 1, qr)) % MOD;
	}

	static void build1 (int n, int l, int r) {
		if (l == r) {
			tree[n] = a[l];
			max[n] = l;
			return;
		}
		int mid = (l + r) >> 1;
		build1(n << 1, l, mid);
		build1(n << 1 | 1, mid + 1, r);
		if (tree[n << 1] > tree[n << 1 | 1]) {
			tree[n] = tree[n << 1];
			max[n] = max[n << 1];
		} else {
			tree[n] = tree[n << 1 | 1];
			max[n] = max[n << 1 | 1];
		}
	}

	static void update1 (int n, int l, int r, int ql, int qr, double val) {
		if (l == ql && r == qr) {
			tree[n] += val;
			lazy[n] += val;
			return;
		}
		if (lazy[n] != 0) {
			lazy[n << 1] += lazy[n];
			lazy[n << 1 | 1] += lazy[n];
			tree[n << 1] += lazy[n];
			tree[n << 1 | 1] += lazy[n];
			lazy[n] = 0;
		}
		int mid = (l + r) >> 1;
		if (qr <= mid)
			update1(n << 1, l, mid, ql, qr, val);
		else if (ql > mid)
			update1(n << 1 | 1, mid + 1, r, ql, qr, val);
		else {
			update1(n << 1, l, mid, ql, mid, val);
			update1(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
		}
		if (tree[n << 1] > tree[n << 1 | 1]) {
			tree[n] = tree[n << 1];
			max[n] = max[n << 1];
		} else {
			tree[n] = tree[n << 1 | 1];
			max[n] = max[n << 1 | 1];
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
