package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2014_NORMA {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n;
	static int[] a;
	static Node[] tree;

	static final int MOD = 1000000000;

	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();

		a = new int[n + 1];
		tree = new Node[4 * n];

		build(1, 1, n);

		Stack<Integer> lo = new Stack<Integer>(); // monotonically increasing
		Stack<Integer> hi = new Stack<Integer>(); // monotonically decreasing

		long ans = 0;

		for (int i = 1; i <= n; i++) {
			a[i] = readInt();

			while (!lo.isEmpty() && a[lo.peek()] >= a[i])
				lo.pop();
			if (lo.isEmpty())
				updateMin(1, 1, n, 1, i, a[i]);

			else
				updateMin(1, 1, n, lo.peek() + 1, i, a[i]);
			lo.push(i);

			while (!hi.isEmpty() && a[hi.peek()] <= a[i])
				hi.pop();
			if (hi.isEmpty())
				updateMax(1, 1, n, 1, i, a[i]);
			else
				updateMax(1, 1, n, hi.peek() + 1, i, a[i]);
			hi.push(i);

			updateLen(1, 1, n, 1, i, 1);
			assert tree[1].lmM >= 0;
			ans = (ans + tree[1].lmM) % MOD;
		}

		out.println(ans);
		out.close();
	}

	static void updateLen (int n, int l, int r, int ql, int qr, long val) {
		if (l == ql && r == qr) {
			tree[n].l = (tree[n].l + val * (r - l + 1)) % MOD;
			tree[n].lm = (tree[n].lm + val * tree[n].m) % MOD;
			tree[n].lM = (tree[n].lM + val * tree[n].M) % MOD;
			tree[n].lmM = (tree[n].lmM + val * tree[n].mM) % MOD;
			tree[n].lazyl = (tree[n].lazyl + val) % MOD;
			return;
		}
		int mid = (l + r) >> 1;
		pushDownMax(n, l, r, mid);
		pushDownMin(n, l, r, mid);
		pushDownLen(n, l, r, mid);

		if (qr <= mid)
			updateLen(n << 1, l, mid, ql, qr, val);
		else if (ql > mid)
			updateLen(n << 1 | 1, mid + 1, r, ql, qr, val);
		else {
			updateLen(n << 1, l, mid, ql, mid, val);
			updateLen(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
		}
		pushUp(n);
	}

	static void updateMin (int n, int l, int r, int ql, int qr, long val) {
		if (l == ql && r == qr) {
			tree[n].m = (val * (r - l + 1)) % MOD;
			tree[n].mM = (val * tree[n].M) % MOD;
			tree[n].lm = (val * tree[n].l) % MOD;
			tree[n].lmM = (val * tree[n].lM) % MOD;
			tree[n].lazym = val;
			return;
		}
		int mid = (l + r) >> 1;
		pushDownMax(n, l, r, mid);
		pushDownMin(n, l, r, mid);
		pushDownLen(n, l, r, mid);

		if (qr <= mid)
			updateMin(n << 1, l, mid, ql, qr, val);
		else if (ql > mid)
			updateMin(n << 1 | 1, mid + 1, r, ql, qr, val);
		else {
			updateMin(n << 1, l, mid, ql, mid, val);
			updateMin(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
		}
		pushUp(n);
	}

	static void updateMax (int n, int l, int r, int ql, int qr, long val) {
		if (l == ql && r == qr) {
			tree[n].M = (val * (r - l + 1)) % MOD;
			tree[n].mM = (val * tree[n].m) % MOD;
			tree[n].lM = (val * tree[n].l) % MOD;
			tree[n].lmM = (val * tree[n].lm) % MOD;
			tree[n].lazyM = val;
			return;
		}
		int mid = (l + r) >> 1;
		pushDownMax(n, l, r, mid);
		pushDownMin(n, l, r, mid);
		pushDownLen(n, l, r, mid);

		if (qr <= mid)
			updateMax(n << 1, l, mid, ql, qr, val);
		else if (ql > mid)
			updateMax(n << 1 | 1, mid + 1, r, ql, qr, val);
		else {
			updateMax(n << 1, l, mid, ql, mid, val);
			updateMax(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
		}
		pushUp(n);
	}

	static void pushUp (int n) {
		tree[n].l = (tree[n << 1].l + tree[n << 1 | 1].l) % MOD;
		tree[n].m = (tree[n << 1].m + tree[n << 1 | 1].m) % MOD;
		tree[n].M = (tree[n << 1].M + tree[n << 1 | 1].M) % MOD;
		tree[n].mM = (tree[n << 1].mM + tree[n << 1 | 1].mM) % MOD;
		tree[n].lM = (tree[n << 1].lM + tree[n << 1 | 1].lM) % MOD;
		tree[n].lm = (tree[n << 1].lm + tree[n << 1 | 1].lm) % MOD;
		tree[n].lmM = (tree[n << 1].lmM + tree[n << 1 | 1].lmM) % MOD;
	}

	static void pushDownLen (int n, int l, int r, int mid) {
		if (tree[n].lazyl == 0)
			return;

		tree[n << 1].l = (tree[n << 1].l + tree[n].lazyl * (mid - l + 1)) % MOD;
		tree[n << 1 | 1].l = (tree[n << 1 | 1].l + tree[n].lazyl * (r - (mid + 1) + 1)) % MOD;

		tree[n << 1].lM = (tree[n << 1].lM + tree[n].lazyl * tree[n << 1].M) % MOD;
		tree[n << 1 | 1].lM = (tree[n << 1 | 1].lM + tree[n].lazyl * tree[n << 1 | 1].M) % MOD;

		tree[n << 1].lm = (tree[n << 1].lm + tree[n].lazyl * tree[n << 1].m) % MOD;
		tree[n << 1 | 1].lm = (tree[n << 1 | 1].lm + tree[n].lazyl * tree[n << 1 | 1].m) % MOD;

		tree[n << 1].lmM = (tree[n << 1].lmM + tree[n].lazyl * tree[n << 1].mM) % MOD;
		tree[n << 1 | 1].lmM = (tree[n << 1 | 1].lmM + tree[n].lazyl * tree[n << 1 | 1].mM) % MOD;

		tree[n << 1].lazyl = (tree[n << 1].lazyl + tree[n].lazyl) % MOD;
		tree[n << 1 | 1].lazyl = (tree[n << 1 | 1].lazyl + tree[n].lazyl) % MOD;
		tree[n].lazyl = 0;
	}

	static void pushDownMin (int n, int l, int r, int mid) {
		if (tree[n].lazym == 0)
			return;

		tree[n << 1].m = (tree[n].lazym * (mid - l + 1) % MOD);
		tree[n << 1 | 1].m = (tree[n].lazym * (r - (mid + 1) + 1)) % MOD;

		tree[n << 1].mM = (tree[n].lazym * tree[n << 1].M) % MOD;
		tree[n << 1 | 1].mM = (tree[n].lazym * tree[n << 1 | 1].M) % MOD;

		tree[n << 1].lm = (tree[n].lazym * tree[n << 1].l) % MOD;
		tree[n << 1 | 1].lm = (tree[n].lazym * tree[n << 1 | 1].l) % MOD;

		tree[n << 1].lmM = (tree[n].lazym * tree[n << 1].lM) % MOD;
		tree[n << 1 | 1].lmM = (tree[n].lazym * tree[n << 1 | 1].lM) % MOD;

		tree[n << 1].lazym = tree[n << 1 | 1].lazym = tree[n].lazym;
		tree[n].lazym = 0;
	}

	static void pushDownMax (int n, int l, int r, int mid) {
		if (tree[n].lazyM == 0)
			return;

		tree[n << 1].M = (tree[n].lazyM * (mid - l + 1)) % MOD;
		tree[n << 1 | 1].M = (tree[n].lazyM * (r - (mid + 1) + 1)) % MOD;

		tree[n << 1].mM = (tree[n].lazyM * tree[n << 1].m) % MOD;
		tree[n << 1 | 1].mM = (tree[n].lazyM * tree[n << 1 | 1].m) % MOD;

		tree[n << 1].lM = (tree[n].lazyM * tree[n << 1].l) % MOD;
		tree[n << 1 | 1].lM = (tree[n].lazyM * tree[n << 1 | 1].l) % MOD;

		tree[n << 1].lmM = (tree[n].lazyM * tree[n << 1].lm) % MOD;
		tree[n << 1 | 1].lmM = (tree[n].lazyM * tree[n << 1 | 1].lm) % MOD;

		tree[n << 1].lazyM = tree[n << 1 | 1].lazyM = tree[n].lazyM;
		tree[n].lazyM = 0;
	}

	static void build (int n, int l, int r) {
		tree[n] = new Node();
		if (l == r)
			return;
		int mid = (l + r) >> 1;
		build(n << 1, l, mid);
		build(n << 1 | 1, mid + 1, r);
	}

	static class Node {
		long l, m, M, lm, lM, mM, lmM;
		long lazyM, lazym, lazyl;
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
