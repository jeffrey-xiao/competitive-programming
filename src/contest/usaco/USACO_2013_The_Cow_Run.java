package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class USACO_2013_The_Cow_Run {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt() + 1;
		int[][][] dp = new int[n + 1][n + 1][2];
		int[] cows = new int[n + 2];
		for (int x = 1; x < n; x++)
			cows[x] = readInt();

		Arrays.sort(cows, 1, n + 1);
		for (int x = 0; x <= n; x++)
			for (int y = 0; y <= n; y++) {
				dp[x][y][0] = 10000000;
				dp[x][y][1] = 10000000;
			}
		for (int x = 1; x <= n; x++)
			if (cows[x] == 0)
				dp[x][1][0] = 0;
		for (int x = 1; x < n; x++) {
			int length = n - x;
			for (int y = 1; y + x <= n + 1; y++) {
				dp[y - 1][x + 1][0] = Math.min(dp[y - 1][x + 1][0], dp[y][x][0] + length * (cows[y] - cows[y - 1]));
				dp[y - 1][x + 1][0] = Math.min(dp[y - 1][x + 1][0], dp[y][x][1] + length * (cows[y + x - 1] - cows[y - 1]));
				dp[y][x + 1][1] = Math.min(dp[y][x + 1][1], dp[y][x][0] + length * (cows[y + x] - cows[y]));
				dp[y][x + 1][1] = Math.min(dp[y][x + 1][1], dp[y][x][1] + length * (cows[y + x] - cows[y + x - 1]));
			}
		}
		System.out.println(Math.min(dp[1][n][0], dp[1][n][1]));

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
