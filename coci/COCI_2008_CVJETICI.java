package coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2008_CVJETICI {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final int SIZE = 100100;

	static int[] tree = new int[SIZE];

	public static void main (String[] args) throws IOException {
		int n = readInt();
		for (int x = 0; x < n; x++) {
			int ans = 0;
			int l = readInt();
			int r = readInt();
			if (l != r) {
				int ls = query(l);
				int rs = query(r);
				ans += ls + rs;
				update(l, l, -ls);
				update(r, r, -rs);
				// System.out.println(l + " " + query(l) + " " + r + " " +
				// query(r));
			} else {
				int ls = query(l);
				ans += ls;
				update(l, l, -ls);
			}

			if (l + 2 == r) {
				update(l + 1, l + 1, 1);
			} else if (l + 1 < r - 1) {
				update(l + 1, r - 1, 1);
			}
			System.out.println(ans);
		}
	}

	static void update (int x, int v) {
		for (; x < SIZE; x += (x & -x))
			tree[x] += v;
	}

	static void update (int a, int b, int v) {
		update(a, v);
		update(b + 1, -v);
	}

	static int query (int x) {
		int sum = 0;
		for (; x > 0; x -= (x & -x))
			sum += tree[x];
		return sum;
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
