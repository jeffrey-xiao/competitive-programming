package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class IOI_2009_Salesman {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, u, d, s;
	static Trade[] t;

	static TreeSet<Integer> ts = new TreeSet<Integer>();
	static TreeMap<Integer, Integer> toIndex = new TreeMap<Integer, Integer>();
	static TreeMap<Integer, Integer> toValue = new TreeMap<Integer, Integer>();
	static int cnt = 0;

	static int[] cost, lazy;
	static int[] dp;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		u = readInt();
		d = readInt();
		s = readInt();

		t = new Trade[n + 1];
		dp = new int[4 * n];
		cost = new int[4 * n];
		lazy = new int[4 * n];
		t[0] = new Trade(0, s, 0);

		for (int i = 1; i <= n; i++) {
			t[i] = new Trade(readInt(), readInt(), readInt());
			ts.add(t[i].pos);
		}

		for (Integer val : ts) {
			toIndex.put(val, ++cnt);
			toValue.put(cnt, val);
		}

		for (int i = 1; i < 4 * n; i++)
			dp[i] = -1 << 30;

		for (int i = 1; i <= cnt; i++)
			updateCost(1, 1, n, i, i, getCost(toValue.get(i), s));
		updateDp(1, 1, n, toIndex.get(s), 0);

		Arrays.sort(t, 1, t.length);
		int ans = 0;
		//		for (int i = 1; i <= n; i++) {
		//			dp[i] = - 1 << 30;
		//			for (int j = 0; j < i; j++) {
		//				dp[i] = Math.max(dp[i], t[i].profit + dp[j] - getCost(t[j].pos, t[i].pos));
		//			}
		//			ans = Math.max(ans, dp[i] - getCost(t[i].pos, s));
		//		}
		for (int i = 1; i <= n; i++) {
			int prev = t[i - 1].pos;
			int curr = t[i].pos;
		}
		out.println(ans);
		out.close();
	}

	static void updateDp (int n, int l, int r, int x, int val) {
		if (l == x && x == r) {
			dp[n] = val;
			return;
		}
		if (lazy[n] != 0) {
			cost[n << 1] += lazy[n];
			cost[n << 1 | 1] += lazy[n];
			lazy[n << 1] += lazy[n];
			lazy[n << 1 | 1] += lazy[n];
			lazy[n] = 0;
		}
		int mid = (l + r) >> 1;
		if (r <= mid)
			updateDp(n << 1, l, mid, x, val);
		else
			updateDp(n << 1 | 1, mid + 1, r, x, val);
		if (dp[n << 1] - cost[n << 1] > dp[n << 1 | 1] - cost[n << 1 | 1]) {
			dp[n] = dp[n << 1];
			cost[n] = cost[n << 1];
		} else {
			dp[n] = dp[n << 1 | 1];
			cost[n] = cost[n << 1 | 1];
		}
	}

	static void updateCost (int n, int l, int r, int ql, int qr, int val) {
		if (l == ql && r == ql) {
			cost[n] += val;
			lazy[n] += val;
			return;
		}
		if (lazy[n] != 0) {
			cost[n << 1] += lazy[n];
			cost[n << 1 | 1] += lazy[n];
			lazy[n << 1] += lazy[n];
			lazy[n << 1 | 1] += lazy[n];
			lazy[n] = 0;
		}
		int mid = (l + r) >> 1;
		if (qr <= mid)
			updateCost(n << 1, l, mid, ql, qr, val);
		else if (ql > mid)
			updateCost(n << 1 | 1, mid + 1, r, ql, qr, val);
		else {
			updateCost(n << 1, l, mid, ql, mid, val);
			updateCost(n << 1 | 1, mid + 1, r, mid + 1, qr, val);
		}
		if (dp[n << 1] - cost[n << 1] > dp[n << 1 | 1] - cost[n << 1 | 1]) {
			dp[n] = dp[n << 1];
			cost[n] = cost[n << 1];
		} else {
			dp[n] = dp[n << 1 | 1];
			cost[n] = cost[n << 1 | 1];
		}
	}

	static int getCost (int i, int j) {
		if (j >= i)
			return (j - i) * d;
		return (i - j) * u;
	}

	static class Trade implements Comparable<Trade> {
		int day, pos, profit;

		Trade (int day, int pos, int profit) {
			this.day = day;
			this.pos = pos;
			this.profit = profit;
		}

		@Override
		public int compareTo (Trade t) {
			return day - t.day;
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
