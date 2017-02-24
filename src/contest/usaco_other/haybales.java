package contest.usaco_other;

/*
ID: jeffrey40
LANG: JAVA
TASK: haybales
*/
import java.util.*;
import java.io.*;

public class haybales {
	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, q;
	static long[] total, lazy, min;
	static int[] a;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new FileReader("haybales.in"));
		out = new PrintWriter(new FileWriter("haybales.out"));
		//br = new BufferedReader(new InputStreamReader(System.in));
		//out = new PrintWriter(new OutputStreamWriter(System.out));

		n = readInt();
		q = readInt();

		total = new long[n * 4];
		lazy = new long[n * 4];
		min = new long[n * 4];
		a = new int[n + 1];

		for (int i = 1; i <= n; i++)
			a[i] = readInt();

		build(1, 1, n);

		for (int i = 0; i < q; i++) {
			char command = next().charAt(0);
			int a = readInt();
			int b = readInt();
			if (command == 'M') {
				out.println(query(1, 1, n, a, b).min);
			} else if (command == 'S') {
				out.println(query(1, 1, n, a, b).total);
			} else {
				update(1, 1, n, a, b, readInt());
			}
		}

		out.close();
		System.exit(0);
	}

	static class State {
		long total, min;

		State (long total, long min) {
			this.total = total;
			this.min = min;
		}
	}

	static State query (int n, int l, int r, int ql, int qr) {
		if (l == ql && r == qr) {
			return new State(total[n], min[n]);
		}
		int mid = (l + r) >> 1;
		if (lazy[n] != 0) {
			total[n << 1] += lazy[n] * (mid - l + 1);
			total[n << 1 | 1] += lazy[n] * (r - (mid + 1) + 1);
			min[n << 1] += lazy[n];
			min[n << 1 | 1] += lazy[n];
			lazy[n << 1] += lazy[n];
			lazy[n << 1 | 1] += lazy[n];
			lazy[n] = 0;
		}
		if (qr <= mid)
			return query(n << 1, l, mid, ql, qr);
		else if (ql > mid)
			return query(n << 1 | 1, mid + 1, r, ql, qr);
		else {
			State s1 = query(n << 1, l, mid, ql, mid);
			State s2 = query(n << 1 | 1, mid + 1, r, mid + 1, qr);
			return new State(s1.total + s2.total, Math.min(s1.min, s2.min));
		}
	}

	static void update (int n, int l, int r, int ql, int qr, long val) {
		if (l == ql && r == qr) {
			total[n] += (r - l + 1) * val;
			lazy[n] += val;
			min[n] += val;
			return;
		}
		int mid = (l + r) >> 1;
		if (lazy[n] != 0) {
			total[n << 1] += lazy[n] * (mid - l + 1);
			total[n << 1 | 1] += lazy[n] * (r - (mid + 1) + 1);
			min[n << 1] += lazy[n];
			min[n << 1 | 1] += lazy[n];
			lazy[n << 1] += lazy[n];
			lazy[n << 1 | 1] += lazy[n];
			lazy[n] = 0;
		}
		if (qr <= mid)
			update(n << 1, l, mid, ql, qr, val);
		else if (ql > mid)
			update(n << 1 | 1, mid + 1, r, ql, qr, val);
		else {
			update(n << 1, l, mid, ql, mid, val);
			update(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
		}
		total[n] = total[n << 1] + total[n << 1 | 1];
		min[n] = Math.min(min[n << 1], min[n << 1 | 1]);
	}

	static void build (int n, int l, int r) {
		if (l == r) {
			total[n] = a[l];
			min[n] = a[l];
			return;
		}
		int mid = (l + r) >> 1;
		build(n << 1, l, mid);
		build(n << 1 | 1, mid + 1, r);
		total[n] = total[n << 1] + total[n << 1 | 1];
		min[n] = Math.min(min[n << 1], min[n << 1 | 1]);
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
