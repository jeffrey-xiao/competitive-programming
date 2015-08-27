package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_ACM {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	static int[][] a;
	static int[][] p = { {0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 1, 0}, {2, 0, 1}};
	static int[][] dp;
	static int n;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		br = new BufferedReader(new FileReader("test.txt"));
		// ps = new PrintWriter("output.txt");

		n = readInt();
		a = new int[n][3];
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < n; i++) {
				a[i][j] = readInt();
			}
		}
		int ans = 1 << 30;
		for (int i = 0; i < 6; i++) {
			dp = new int[n][3];
			ans = Math.min(ans, solve(0, 0, i));
		}
		ps.println(ans);
		ps.close();
	}

	static int solve (int i, int j, int k) {
		if (i == n) {
			if (j == 3)
				return 0;
			return 1 << 30;
		}
		if (j == 3)
			return 1 << 30;
		if (dp[i][j] != 0)
			return dp[i][j];
		int res = Math.min(solve(i + 1, j + 1, k), solve(i + 1, j, k)) + a[i][p[k][j]];
		return dp[i][j] = res;
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