package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2006_IVANA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int[][][] dp;
	static int[] a;
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		a = new int[n + 2];
		dp = new int[n + 2][n + 2][n + 2];
		for (int i = 1; i <= n; i++)
			a[i] = readInt() % 2;
		for (int i = 0; i <= n + 1; i++)
			for (int j = 0; j <= n + 1; j++)
				for (int k = 0; k <= n + 1; k++)
					dp[i][j][k] = -1;
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			if (solve(i, i, i) > 0)
				ans++;
		}
		System.out.println(ans);
	}

	static int solve (int l, int r, int s) {
		if (l == 0)
			l = n;
		if (r == n + 1)
			r = 1;
		if (dp[l][r][s] != -1)
			return dp[l][r][s];
		if (Math.abs(l - r) == 1 || (l == 1 && r == n)) {
			if (l == s)
				return dp[l][r][s] = a[r];
			else if (r == s)
				return dp[l][r][s] = a[l];
			return dp[l][r][s] = Math.abs(a[r] - a[l]);
		}
		int res = -(1 << 30);
		if (l == r) {
			res = a[l] - solve(l - 1, r + 1, s);
		} else {
			if (l >= 1) {
				res = Math.max(res, a[l] - solve(l - 1, r, s));
			}
			if (r <= n) {
				res = Math.max(res, a[r] - solve(l, r + 1, s));
			}
		}
		dp[l][r][s] = res;
		// if (l == r && r == s)
		// System.out.println(l + " " + r + " " + s +" "+res);
		return res;
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
