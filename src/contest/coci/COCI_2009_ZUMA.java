package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2009_ZUMA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int n, k;
	static int[] A;
	static int[][][] dp;

	public static void main (String[] args) throws IOException {
		n = readInt();
		k = readInt();
		dp = new int[n][n][k];
		A = new int[n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int l = 0; l < k; l++)
					dp[i][j][l] = -1;
		for (int i = 0; i < n; i++)
			A[i] = readInt();
		System.out.println(solve(0, n - 1, 0));
	}

	private static int solve (int i, int j, int count) {

		if (i > j)
			return 0;
		if (i == j)
			return k - 1 - count;
		if (dp[i][j][count] != -1)
			return dp[i][j][count];
		int res = Integer.MAX_VALUE;
		if (count == k - 1)
			res = solve(i + 1, j, 0);
		else
			res = solve(i, j, count + 1) + 1;
		for (int l = i + 1; l <= j; l++) {
			if (A[i] == A[l]) {
				res = Math.min(res, solve(i + 1, l - 1, 0) + solve(l, j, Math.min(k - 1, count + 1)));
			}
		}
		return dp[i][j][count] = res;
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
