package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MMCC_Kirito {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[] tree, lcp;
	static String[] grid;
	static int n, m;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		tree = new int[n * 3];
		grid = new String[n + 1];
		for (int i = 1; i <= n; i++)
			grid[i] = next();
		lcp = new int[n + 1];
		for (int i = 2; i <= n; i++) {
			int j = 0;
			for (; j < m; j++)
				if (grid[i].charAt(j) != grid[i - 1].charAt(j))
					break;
			lcp[i] = j;
		}
		build(1, 1, n);
		int q = readInt();
		for (int i = 0; i < q; i++) {
			int l = readInt();
			int r = readInt();
			if (l == r)
				out.println(m * (r - l + 1));
			else {
				out.println(query(1, 1, n, l + 1, r) * (r - l + 1));
				String temp = grid[l];
				grid[l] = grid[r];
				grid[r] = temp;
				update(l);
				update(l + 1);
				update(r);
				update(r + 1);
			}
		}
		out.close();
	}

	static void update (int i) {
		if (i <= 1 || i > n)
			return;
		int j = 0;
		for (; j < m; j++)
			if (grid[i].charAt(j) != grid[i - 1].charAt(j))
				break;
		lcp[i] = j;
		update(1, 1, n, i);
	}

	static void update (int n, int l, int r, int x) {
		if (x == l && x == r) {
			tree[n] = lcp[x];
			return;
		}
		int mid = (l + r) >> 1;
		if (x <= mid)
			update(n << 1, l, mid, x);
		else
			update(n << 1 | 1, mid + 1, r, x);
		tree[n] = Math.min(tree[n << 1], tree[n << 1 | 1]);
	}

	static void build (int n, int l, int r) {
		if (l == r) {
			tree[n] = lcp[l];
			return;
		}
		int mid = (l + r) >> 1;
		build(n << 1, l, mid);
		build(n << 1 | 1, mid + 1, r);
		tree[n] = Math.min(tree[n << 1], tree[n << 1 | 1]);
	}

	static int query (int n, int l, int r, int ql, int qr) {
		if (l == ql && r == qr) {
			return tree[n];
		}
		int mid = (l + r) >> 1;
		if (qr <= mid)
			return query(n << 1, l, mid, ql, qr);
		else if (ql > mid)
			return query(n << 1 | 1, mid + 1, r, ql, qr);
		else
			return Math.min(query(n << 1, l, mid, ql, mid), query(n << 1 | 1, mid + 1, r, mid + 1, qr));
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
