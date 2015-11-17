package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2015_Lelei_and_Contest {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static long[] a;
	static long[] tree;
	static long[] lazy;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int m = readInt();
		int n = readInt();
		int q = readInt();

		tree = new long[n * 4];
		lazy = new long[n * 4];
		a = new long[n + 1];
		for (int i = 1; i <= n; i++)
			a[i] = readInt();
		build(1, 1, n, m);
		for (int i = 0; i < q; i++) {
			int com = readInt();
			if (com == 1) {
				int l = readInt();
				int r = readInt();
				int x = readInt();
				update(1, 1, n, l, r, pow(x, m, m), m);
			} else {
				int l = readInt();
				int r = readInt();
				out.println(query(1, 1, n, l, r, m));
			}
		}
		out.close();
	}

	static long query (int n, int l, int r, int ql, int qr, int m) {
		if (l == ql && r == qr) {
			return tree[n];
		}
		int mid = (l + r) >> 1;
		if (lazy[n] != 0) {
			lazy[n << 1] += lazy[n];
			lazy[n << 1 | 1] += lazy[n];
			tree[n << 1] = (tree[n << 1] + lazy[n] * (mid - l + 1)) % m;
			tree[n << 1 | 1] = (tree[n << 1 | 1] + lazy[n] * (r - (mid + 1) + 1)) % m;
			lazy[n] = 0;
		}
		if (qr <= mid)
			return query(n << 1, l, mid, ql, qr, m);
		else if (ql > mid)
			return query(n << 1 | 1, mid + 1, r, ql, qr, m);
		return (query(n << 1, l, mid, ql, mid, m) + query(n << 1 | 1, mid + 1, r, mid + 1, qr, m)) % m;
	}

	static void update (int n, int l, int r, int ql, int qr, long val, int m) {
		if (l == ql && r == qr) {
			long add = val;
			tree[n] = (tree[n] + (r - l + 1) * add) % m;
			lazy[n] += add;
			return;
		}
		int mid = (l + r) >> 1;
		if (lazy[n] != 0) {
			lazy[n << 1] += lazy[n];
			lazy[n << 1 | 1] += lazy[n];
			tree[n << 1] = (tree[n << 1] + lazy[n] * (mid - l + 1)) % m;
			tree[n << 1 | 1] = (tree[n << 1 | 1] + lazy[n] * (r - (mid + 1) + 1)) % m;
			lazy[n] = 0;
		}
		if (qr <= mid)
			update(n << 1, l, mid, ql, qr, val, m);
		else if (ql > mid)
			update(n << 1 | 1, mid + 1, r, ql, qr, val, m);
		else {
			update(n << 1, l, mid, ql, mid, val, m);
			update(n << 1 | 1, mid + 1, r, mid + 1, qr, val, m);
		}
		tree[n] = (tree[n << 1] + tree[n << 1 | 1]) % m;
	}

	static void build (int n, int l, int r, int m) {
		if (l == r) {
			tree[n] = pow(a[l], m, m);
			return;
		}
		int mid = (l + r) >> 1;
		build(n << 1, l, mid, m);
		build(n << 1 | 1, mid + 1, r, m);
		tree[n] = (tree[n << 1] + tree[n << 1 | 1]) % m;
	}

	static long pow (long n, long k, long m) {
		if (k == 0)
			return 1;
		if (k == 1)
			return n;
		if (k % 2 == 0)
			return pow(n * n % m, k / 2, m) % m;
		return n * pow(n * n % m, k / 2, m) % m;
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
