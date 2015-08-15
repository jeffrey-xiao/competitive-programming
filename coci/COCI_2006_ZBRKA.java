package coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2006_ZBRKA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final int MOD = 1000000007;

	public static void main (String[] args) throws IOException {
		// the row dp is symmetrical
		// the number of possibilities increase to when n = k then decrease
		int n = readInt();
		int k = readInt();
		int[][] dp = new int[2][k + 1];
		dp[1][0] = 1;
		for (int x = 2; x <= n; x++) {
			int cumulativeSum = 0;
			for (int y = 0; y <= Math.min(x - 1, k); y++) {
				cumulativeSum += dp[(x - 1) % 2][y];
				cumulativeSum %= MOD;
				dp[x % 2][y] = cumulativeSum;
				// System.out.print(dp[x%2][y] + " ");
			}
			for (int y = x; y <= k; y++) {
				cumulativeSum += dp[(x - 1) % 2][y];
				cumulativeSum %= MOD;
				cumulativeSum -= dp[(x - 1) % 2][y - x];
				cumulativeSum = (cumulativeSum + MOD) % MOD;
				dp[x % 2][y] = cumulativeSum;
				// System.out.println(dp[x%2][y] + " ");
			}
			// System.out.println();
		}
		System.out.println(dp[n % 2][k]);
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
