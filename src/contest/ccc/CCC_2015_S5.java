package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2015_S5 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int[] N, M;
	static int n, m;

	static int[][][][] dp;

	public static void main (String[] args) throws IOException {
		n = readInt();
		N = new int[n + 1];
		for (int x = 1; x <= n; x++)
			N[x] = readInt();
		m = readInt();
		M = new int[m + 1];
		for (int x = 1; x <= m; x++)
			M[x] = readInt();
		Arrays.sort(M);
		dp = new int[n + 2][m + 2][m + 2][2];
		for (int x = 0; x <= n + 1; x++) {
			for (int y = 0; y <= m + 1; y++) {
				for (int z = 0; z <= m + 1; z++) {
					for (int b = 0; b < 2; b++) {
						dp[x][y][z][b] = -1;
					}
				}
			}
		}
		System.out.println(compute(1, 1, m, 0));
	}

	// b = 0 means can eat, b = 1 means cannot eat
	private static int compute (int i, int j1, int j2, int b) {
		// System.out.printf("%d %d %d %d\n", i, j1, j2, b);
		// System.out.println(j1 + " " + j2);
		if (i == n + 1 && j1 > j2)
			return 0;
		if (dp[i][j1][j2][b] != -1)
			return dp[i][j1][j2][b];
		int max = 0;
		if (b == 0) {
			if (i != n + 1) {
				max = Math.max(max, N[i] + compute(i + 1, j1, j2, 1));
			}
			if (j1 <= j2)
				max = Math.max(max, M[j2] + compute(i, j1, j2 - 1, 1));
		}
		if (i != n + 1)
			max = Math.max(max, compute(i + 1, j1, j2, 0));
		if (j1 <= j2)
			max = Math.max(max, compute(i, j1 + 1, j2, 0));
		dp[i][j1][j2][b] = max;
		return max;
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
}
