package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2007_ZAPIS {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static char[] c1 = {'(', '{', '['};
	static char[] c2 = {')', '}', ']'};
	static char[] c;
	static final int MOD = 100000;
	static long[][] dp;
	static boolean flag = false;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		dp = new long[n][n];
		for (int x = 0; x < n; x++)
			for (int y = 0; y < n; y++)
				dp[x][y] = -1;
		c = next().toCharArray();
		long res = compute(0, n - 1);
		if (flag)
			System.out.printf("%05d", res);
		else
			System.out.println(compute(0, n - 1));
	}

	private static long compute (int i, int j) {
		// string is complete
		if (i > j)
			return 1;
		if (dp[i][j] != -1)
			return dp[i][j];
		long total = 0;
		for (int x = i + 1; x <= j; x += 2)
			for (int z = 0; z < 3; z++)
				if ((c[i] == c1[z] || c[i] == '?')
						&& (c[x] == c2[z] || c[x] == '?')) {
					total += (compute(i + 1, x - 1)) * (compute(x + 1, j));
					if (total > 100000)
						flag = true;
					total %= MOD;
				}

		dp[i][j] = total % MOD;
		return total % MOD;
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
