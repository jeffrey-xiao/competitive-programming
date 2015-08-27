package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2012_Landscaping {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static final int SIZE = 1010;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int x = readInt();
		int y = readInt();
		int z = readInt();
		int[] a = new int[SIZE];
		int[] b = new int[SIZE];
		int ia = 1, ib = 1;
		for (int c = 0; c < n; c++) {
			int first = readInt();
			int second = readInt();
			for (int d = 0; d < first; d++)
				a[ia++] = c;
			for (int d = 0; d < second; d++)
				b[ib++] = c;
		}

		int[][] dp = new int[ia][ib];
		for (int c = 0; c < ia; c++)
			dp[c][0] = y * c;
		for (int c = 0; c < ib; c++)
			dp[0][c] = x * c;
		for (int c = 1; c < ia; c++) {
			for (int d = 1; d < ib; d++) {
				if (a[c] == b[d]) {
					dp[c][d] = dp[c - 1][d - 1];
				} else {
					dp[c][d] = dp[c - 1][d] + y;
					dp[c][d] = Math.min(dp[c][d], dp[c][d - 1] + x);
					dp[c][d] = Math.min(dp[c][d], dp[c - 1][d - 1] + Math.abs(a[c] - b[d]) * z);
				}
			}
		}
		System.out.println(dp[ia - 1][ib - 1]);
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
