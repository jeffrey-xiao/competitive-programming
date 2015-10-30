package contest.ioi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class IOI_2015_Holiday {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, start, d;
	static City[] c;
	static int[] toPos;

	static int[] cnt;
	static long[] tree;

	static int[] dp1;
	static int[] dp2;
	static int[] dp3;
	static int[] dp4;

	static long[] val1;
	static long[] val2;
	static long[] val3;
	static long[] val4;

	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		start = readInt() + 1;
		d = readInt();

		c = new City[n + 1];
		toPos = new int[n + 1];
		cnt = new int[4 * n];
		tree = new long[4 * n];
		dp1 = new int[d + 1];
		dp2 = new int[d + 1];
		dp3 = new int[d + 1];
		dp4 = new int[d + 1];
		val1 = new long[d + 1];
		val2 = new long[d + 1];
		val3 = new long[d + 1];
		val4 = new long[d + 1];

		for (int i = 1; i <= n; i++) {
			c[i] = new City(i, readLong());
		}

		Arrays.sort(c, 1, n + 1);

		for (int i = 1; i <= n; i++)
			toPos[c[i].index] = i;
		long ans = 0;
		for (int i = start; i <= n; i++) {
			update(1, 1, n, toPos[i]);
			int timeOnVisiting = d - (i - start);
			ans = Math.max(ans, query(1, timeOnVisiting));
		}

		for (int i = start; i <= n; i++)
			update(1, 1, n, toPos[i]);
		solve1(0, d, start, n);
		solve2(0, d, 1, start - 1);
		solve3(0, d, start + 1, n);
		solve4(0, d, 1, start);
		for (int i = 0; i <= d; i++)
			ans = Math.max(ans, Math.max(val3[i] + val4[d - i], val1[i] + val2[d - i]));
		out.println(ans);
		out.close();
	}

	static void solve2 (int d1, int d2, int l, int r) {
		if (d1 > d2 || l > r)
			return;
		int targetD = (d1 + d2) >> 1;
		long max = -1;
		int maxIndex = -1;
		for (int i = r; i >= l; i--) {
			int timeOnVisiting = targetD - (start - i);
			update(1, 1, n, toPos[i]);
			long res = query(1, timeOnVisiting);
			if (res >= max) {
				max = res;
				maxIndex = i;
			}
		}
		dp2[targetD] = maxIndex;
		val2[targetD] = max;
		for (int i = l; i <= dp2[targetD]; i++)
			update(1, 1, n, toPos[i]);
		solve2(targetD + 1, d2, l, dp2[targetD]);
		for (int i = dp2[targetD] + 1; i <= r; i++)
			update(1, 1, n, toPos[i]);
		solve2(d1, targetD - 1, dp2[targetD], r);
	}

	static void solve1 (int d1, int d2, int l, int r) {
		if (d1 > d2 || l > r)
			return;
		int targetD = (d1 + d2) >> 1;
		long max = -1;
		int maxIndex = -1;
		for (int i = l; i <= r; i++) {
			int timeOnVisiting = targetD - (i - start) * 2;
			update(1, 1, n, toPos[i]);
			long res = query(1, timeOnVisiting);
			if (res >= max) {
				max = res;
				maxIndex = i;
			}
		}
		dp1[targetD] = maxIndex;
		val1[targetD] = max;
		for (int i = dp1[targetD]; i <= r; i++)
			update(1, 1, n, toPos[i]);
		solve1(targetD + 1, d2, dp1[targetD], r);
		for (int i = l; i < dp1[targetD]; i++)
			update(1, 1, n, toPos[i]);
		solve1(d1, targetD - 1, l, dp1[targetD]);
	}

	static void solve4 (int d1, int d2, int l, int r) {
		if (d1 > d2 || l > r)
			return;
		int targetD = (d1 + d2) >> 1;
		long max = -1;
		int maxIndex = -1;
		for (int i = r; i >= l; i--) {
			int timeOnVisiting = targetD - (start - i) * 2;
			update(1, 1, n, toPos[i]);
			long res = query(1, timeOnVisiting);
			if (res >= max) {
				max = res;
				maxIndex = i;
			}
		}
		dp4[targetD] = maxIndex;
		val4[targetD] = max;
		for (int i = l; i <= dp4[targetD]; i++)
			update(1, 1, n, toPos[i]);
		solve4(targetD + 1, d2, l, dp4[targetD]);
		for (int i = dp4[targetD] + 1; i <= r; i++)
			update(1, 1, n, toPos[i]);
		solve4(d1, targetD - 1, dp4[targetD], r);
	}

	static void solve3 (int d1, int d2, int l, int r) {
		if (d1 > d2 || l > r)
			return;
		int targetD = (d1 + d2) >> 1;
		long max = -1;
		int maxIndex = -1;
		for (int i = l; i <= r; i++) {
			int timeOnVisiting = targetD - (i - start);
			update(1, 1, n, toPos[i]);
			long res = query(1, timeOnVisiting);
			if (res >= max) {
				max = res;
				maxIndex = i;
			}
		}
		dp3[targetD] = maxIndex;
		val3[targetD] = max;
		for (int i = dp3[targetD]; i <= r; i++)
			update(1, 1, n, toPos[i]);
		solve3(targetD + 1, d2, dp3[targetD], r);
		for (int i = l; i < dp3[targetD]; i++)
			update(1, 1, n, toPos[i]);
		solve3(d1, targetD - 1, l, dp3[targetD]);
	}

	static void update (int n, int l, int r, int x) {
		if (l == x && x == r) {
			cnt[n] = (cnt[n] + 1) % 2;
			tree[n] = cnt[n] * c[l].cnt;
			return;
		}
		int mid = (l + r) >> 1;
		if (x <= mid)
			update(n << 1, l, mid, x);
		else
			update(n << 1 | 1, mid + 1, r, x);
		tree[n] = tree[n << 1] + tree[n << 1 | 1];
		cnt[n] = cnt[n << 1] + cnt[n << 1 | 1];
	}

	static long query (int n, int num) {
		if (num <= 0)
			return 0;
		if (cnt[n] <= num)
			return tree[n];
		return query(n << 1, num - cnt[n << 1 | 1]) + query(n << 1 | 1, num);
	}

	static class City implements Comparable<City> {
		int index;
		Long cnt;

		City (int index, long cnt) {
			this.index = index;
			this.cnt = cnt;
		}

		@Override
		public int compareTo (City c) {
			return cnt.compareTo(c.cnt);
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
