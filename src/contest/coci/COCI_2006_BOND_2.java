package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2006_BOND_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static double[] dp;
	static double[][] prob;
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		dp = new double[1 << n];
		prob = new double[n][n];
		for (int i = 0; i < 1 << n; i++)
			dp[i] = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				prob[i][j] = readInt() / 100.0d;
			}
		}
		System.out.println(compute(0, (1 << n) - 1) * 100);
	}

	private static double compute (int i, int j) {
		if (i == n)
			return 1.0d;
		if (dp[j] != -1)
			return dp[j];
		double res = 0.0;
		for (int k = 0; k < n; k++) {
			if ((j & (1 << k)) > 0) {
				double next = prob[i][k] * compute(i + 1, j ^ (1 << k));
				res = Math.max(res, next);
			}
		}
		return dp[j] = res;
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
