package coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2007_NIKOLA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[][] dp = new int[0][0];
	static int[] row = new int[0];
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		dp = new int[n + 1][n + 1];
		row = new int[n + 1];
		for (int x = 1; x <= n; x++) {
			row[x] = readInt();
		}
		System.out.println(compute(1, 0) - row[1]);
	}

	private static int compute (int sq, int jump) {
		if (sq < 1 || sq > n)
			return 10000000;
		if (sq == n)
			return row[sq];
		if (dp[sq][jump] != 0)
			return dp[sq][jump];
		int currCost = row[sq];
		// System.out.println(sq + " " + jump);
		int min = Math.min(compute(sq + jump + 1, jump + 1), 10000000);
		if (jump != 0)
			min = Math.min(compute(sq - jump, jump), min);
		dp[sq][jump] = min + currCost;
		return dp[sq][jump];
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
