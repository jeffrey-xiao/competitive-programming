package dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2014_Kittans_Dilemma {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		Man[] men = new Man[n + 1];
		for (int i = 1; i <= n; i++) {
			men[i] = new Man(readInt(), readInt());
		}
		int[][] dp = new int[2][m + 1];
		for (int i = 0; i <= 1; i++) {
			for (int j = 1; j <= m; j++)
				dp[i][j] = -1;
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[i % 2][j - 1]);
				if (j - men[i].space >= 0) {
					dp[i % 2][j] = Math.max(dp[i % 2][j], dp[(i - 1) % 2][j
							- men[i].space]
							+ (men[i].protection ? 2 : 1));
				}
			}
		}
		System.out.println(dp[n % 2][m]);
	}

	static class Man {
		int space;
		boolean protection;

		Man (int space, int protection) {
			this.space = space;
			this.protection = protection == 2;
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
