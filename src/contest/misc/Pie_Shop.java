package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Pie_Shop {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static final int MOD = 1000000007;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int r = readInt();
		int c = readInt();
		int[][] g = new int[r][c];
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				g[i][j] = readInt();
		ArrayList<Integer> s = new ArrayList<Integer>();
		for (int i = 0; i < (1 << c); i++) {
			int[] curr = new int[c];
			for (int j = 0; j < c; j++)
				if ((1 << j & i) > 0) {
					curr[j] = 1;
				}
			boolean valid = true;
			for (int j = 0; j < c - 1; j++)
				if (curr[j] == 1 && curr[j + 1] == 1)
					valid = false;
			if (valid)
				s.add(i);
		}
		int[][] dp = new int[r][1 << c];
		int[][] cnt = new int[r][1 << c];
		for (int i = 0; i < r; i++) {
			for (int j : s) {
				int sum = 0;
				for (int k = 0; k < c; k++)
					if ((1 << k & j) > 0)
						sum += g[i][k];

				if (i == 0) {
					dp[i][j] = sum;
					cnt[i][j] = 1;
				} else {
					for (int k : s) {
						boolean valid = (k & j) == 0;

						if (valid) {
							if (dp[i - 1][k] + sum == dp[i][j]) {
								cnt[i][j] += cnt[i - 1][k];
								if (cnt[i][j] >= MOD)
									cnt[i][j] -= MOD;
							} else if (dp[i - 1][k] + sum > dp[i][j]) {
								dp[i][j] = dp[i - 1][k] + sum;
								cnt[i][j] = cnt[i - 1][k];
							}
						}
					}
				}
			}
		}
		int ans = 0;
		int ans2 = 0;
		for (int i : s) {
			if (dp[r - 1][i] > ans) {
				ans = dp[r - 1][i];
				ans2 = cnt[r - 1][i];
			} else if (dp[r - 1][i] == ans) {
				ans2 += cnt[r - 1][i];
				if (ans2 >= MOD)
					ans2 -= MOD;
			}
		}
		System.out.println(ans + " " + ans2);
		pr.close();
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
