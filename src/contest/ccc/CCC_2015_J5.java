package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2015_J5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int n, m;
	static int[][][] dp = new int[251][251][251];

	public static void main(String[] args) throws IOException {
		n = readInt();
		m = readInt();
		for (int i = 0; i <= 250; i++)
			for (int j = 0; j <= 250; j++)
				for (int k = 0; k <= 250; k++)
					dp[i][j][k] = -1;
		System.out.println(compute(0, n, 1));
	}

	private static int compute(int i, int j, int prev) {
		if (dp[i][j][prev] != -1)
			return dp[i][j][prev];
		if (i == m - 1) {
			if (j >= prev)
				dp[i][j][prev] = 1;
			else
				dp[i][j][prev] = 0;
			return dp[i][j][prev];
		}
		int sum = 0;
		for (int next = prev; next <= j; next++) {
			sum += compute(i + 1, j - next, next);
		}
		return dp[i][j][prev] = sum;
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong() throws IOException {
		return Long.parseLong(next());
	}

	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static char readCharacter() throws IOException {
		return next().charAt(0);
	}

	static String readLine() throws IOException {
		return br.readLine().trim();
	}
}
