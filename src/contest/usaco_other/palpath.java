package contest.usaco_other;

/*
ID: jeffrey40
LANG: JAVA
TASK: palpath
 */
import java.util.*;
import java.io.*;

public class palpath {
	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;
	static int[][][] dp;
	static final int MOD = 1000000007;
	static int bruteans = 0;
	static int ans = 0;
	static int n;
	static char[][] g;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new FileReader("palpath.in"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new BufferedWriter(new FileWriter("palpath.out")));
		n = readInt();

		g = new char[n][n];
		for (int i = 0; i < n; i++)
			g[i] = next().toCharArray();

		char[][] ng = new char[2 * n - 1][n];
		dp = new int[2 * n - 1][][];
		for (int i = 0; i < (2 * n) - 1; i++) {
			int count = i;
			int sx = i;
			int sy = 0;
			if (sx >= n) {
				sy += (sx - n) + 1;
				sx = n - 1;
			}
			if (count >= n) {
				count = 2 * n - 1 - count - 1;
			}
			for (int j = 0; j <= count; j++) {
				ng[i][j] = g[sx][sy];
				sx--;
				sy++;
			}
			dp[i] = new int[count + 1][count + 1];
		}
		if (ng[0][0] == ng[2 * n - 2][0])
			dp[0][0][0] = 1;
		for (int i = 1; i < n; i++) {
			int l = i;
			int r = 2 * n - 2 - l;
			if (i < n - 1)
				for (int j = 0; j <= l; j++) {
					for (int k = 0; k <= l; k++) {
						if (ng[l][j] == ng[r][k]) {
							Integer res = (((get(l - 1, j, k) + get(l - 1, j - 1, k)) % MOD + get(l - 1, j, k - 1)) % MOD + get(l - 1, j - 1, k - 1)) % MOD;
							dp[l][j][k] = (dp[l][j][k] + res) % MOD;
						}
					}
				}
			else {
				for (int j = 0; j <= l; j++) {
					Integer res = (((get(l - 1, j, j) + get(l - 1, j - 1, j)) % MOD + get(l - 1, j, j - 1)) % MOD + get(l - 1, j - 1, j - 1)) % MOD;
					ans = (ans + res) % MOD;
				}
			}
		}
		pr.println(ans);
		pr.close();
		System.exit(0);
	}

	private static int get (int i, int j, int k) {
		int len = dp[i].length;
		if (j < 0 || k < 0 || j >= len || k >= len)
			return 0;
		return dp[i][j][k];
	}

	static class State {
		Integer l, r, lh, rh;

		State (int l, int r, int lh, int rh) {
			this.l = l;
			this.r = r;
			this.lh = lh;
			this.rh = rh;
		}

		public boolean equals (Object o) {
			if (o instanceof State) {
				State s = (State)o;
				return l == s.l && r == s.r && lh == s.lh && rh == s.rh;
			}
			return false;
		}

		public int hashCode () {
			return l.hashCode() * 31 * 31 * 31 + r.hashCode() * 31 * 31 + lh.hashCode() * 31 + rh.hashCode();
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
