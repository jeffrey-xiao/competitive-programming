package contest.misc;

import java.util.*;
import java.io.*;

public class Dominoes {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n;
	static Domino[] d;
	static int[] dp;
	static int[] min1;
	static int[] min2;
	static int[] minReach, maxReach;

	static ArrayDeque<Integer> poss = new ArrayDeque<Integer>();

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		d = new Domino[n + 1];
		dp = new int[n + 1];
		minReach = new int[n * 4];
		maxReach = new int[n * 4];
		min1 = new int[n * 4];
		min2 = new int[n * 4];

		build(min1, 1, 0, n);
		build(min2, 1, 0, n);
		build(maxReach, 1, 0, n);
		build(minReach, 1, 0, n);

		update(min2, 1, 0, n, 0, 0);
		update(min1, 1, 0, n, 0, 0);

		TreeSet<Domino> dominoes = new TreeSet<Domino>();
		for (int i = 1; i <= n; i++) {
			dp[i] = -1;
			d[i] = new Domino(readInt(), readInt());
		}
		Arrays.sort(d, 1, d.length);
		for (int i = 1; i <= n; i++)
			dominoes.add(new Domino(d[i].pos, d[i].height, i));

		update(minReach, 1, 0, n, 1, 1);
		for (int i = 2; i <= n; i++) {
			update(minReach, 1, 0, n, i, i);
			int loReach = dominoes.ceiling(new Domino(d[i].pos - d[i].height, 0)).index;
			update(minReach, 1, 0, n, i, query(minReach, 1, 0, n, loReach, i));
		}
		update(maxReach, 1, 0, n, n, -n);
		for (int i = n - 1; i >= 1; i--) {
			update(maxReach, 1, 0, n, i, -i);
			int hiReach = dominoes.floor(new Domino(d[i].pos + d[i].height, 0)).index;
			update(maxReach, 1, 0, n, i, query(maxReach, 1, 0, n, i, hiReach));
		}
		for (int i = 1; i <= n; i++) {
			dp[i] = 1 << 30;
			dp[i] = Math.min(dp[i], 1 + query(min1, 1, 0, n, query(minReach, 1, 0, n, i, i) - 1, i - 1));
			while (!poss.isEmpty()) {
				int index = poss.getFirst();
				if (-query(maxReach, 1, 0, n, index, index) < i) {
					poss.removeFirst();
					update(min2, 1, 0, n, index - 1, 1 << 30);
				} else {
					break;
				}
			}
			while (!poss.isEmpty()) {
				int index = poss.getLast();
				if (-query(maxReach, 1, 0, n, index, index) < i) {
					poss.removeLast();
					update(min2, 1, 0, n, index - 1, 1 << 30);
				} else {
					break;
				}
			}
			dp[i] = Math.min(dp[i], 1 + query(min2, 1, 0, n, 0, i - 1));
			update(min1, 1, 0, n, i, dp[i]);
			update(min2, 1, 0, n, i, dp[i]);
			poss.addLast(i);
		}
		out.println(dp[n]);
		out.close();
	}

	static void build (int[] min, int n, int lo, int hi) {
		min[n] = 1 << 30;
		if (lo == hi)
			return;
		int mid = (hi + lo) >> 1;
		build(min, n << 1, lo, mid);
		build(min, n << 1 | 1, mid + 1, hi);
	}

	static void update (int[] min, int n, int lo, int hi, int x, int val) {
		if (x == lo && x == hi) {
			min[n] = val;
			return;
		}
		int mid = (hi + lo) >> 1;
		if (x <= mid)
			update(min, n << 1, lo, mid, x, val);
		else
			update(min, n << 1 | 1, mid + 1, hi, x, val);
		min[n] = Math.min(min[n << 1], min[n << 1 | 1]);
	}

	static int query (int[] min, int n, int lo, int hi, int qlo, int qhi) {
		if (lo == qlo && hi == qhi) {
			return min[n];
		}
		int mid = (hi + lo) >> 1;
		if (qhi <= mid)
			return query(min, n << 1, lo, mid, qlo, qhi);
		else if (qlo > mid)
			return query(min, n << 1 | 1, mid + 1, hi, qlo, qhi);
		else
			return Math.min(query(min, n << 1, lo, mid, qlo, mid), query(min, n << 1 | 1, mid + 1, hi, mid + 1, qhi));
	}

	static class Domino implements Comparable<Domino> {
		int pos, height, index;

		Domino (int pos, int height) {
			this.pos = pos;
			this.height = height;
		}

		Domino (int pos, int height, int index) {
			this.pos = pos;
			this.height = height;
			this.index = index;
		}

		@Override
		public int compareTo (Domino d) {
			return pos - d.pos;
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
