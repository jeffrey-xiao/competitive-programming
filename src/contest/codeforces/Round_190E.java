package contest.codeforces;

import java.util.*;
import java.io.*;

public class Round_190E {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, k;
	static int[][] a;
	static int[][] dp;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		k = readInt();

		a = new int[n + 1][n + 1];
		dp = new int[n + 1][k + 1];

		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				a[i][j] = readInt() + a[i - 1][j] + a[i][j - 1] - a[i - 1][j - 1];

		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= k; j++)
				dp[i][j] = 1 << 30;
		dp[0][0] = 0;
		for (int i = 1; i <= k; i++) {
			compute(i, 1, n, 1, n);
		}
		out.println(dp[n][k] / 2);
		out.close();
	}

	static void compute (int g, int i, int j, int l, int r) {
		if (i > j)
			return;
		int mid = (i + j) / 2;
		int bestIndex = l;
		for (int k = l; k <= Math.min(r, mid); k++) {
			int val = dp[k - 1][g - 1] + (a[mid][mid] - a[mid][k - 1] - a[k - 1][mid] + a[k - 1][k - 1]);
			if (val < dp[mid][g]) {
				dp[mid][g] = val;
				bestIndex = k;
			}
		}
		compute(g, i, mid - 1, l, bestIndex);
		compute(g, mid + 1, j, bestIndex, r);
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
