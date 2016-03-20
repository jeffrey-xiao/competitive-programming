package contest.dmoj;

import java.util.*;
import java.math.BigInteger;
import java.io.*;

public class New_Year_Tiles {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m;

	static long[][][][] dp;

	static final int MOD = 1000000007;
	static ArrayList<Integer> states;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();

		states = new ArrayList<Integer>();

		for (int i = 1; i < 1 << 5; i++) {
			boolean valid = true;
			for (int j = 1; j < 5; j++)
				if ((i & 1 << j) > 0 && (i & 1 << (j - 1)) > 0)
					valid = false;
			if (valid)
				states.add(i);
		}
		dp = new long[m + 1][Math.min(n, m) + 1][Math.min(2 * m - 1, n) + 1][32];

		for (int i = 0; i <= m; i++)
			for (int j = 0; j <= Math.min(n, m); j++)
				for (int k = 0; k <= Math.min(2 * m - 1, n); k++)
					for (int l = 0; l < 32; l++)
						dp[i][j][k][l] = -1;

		long ans = 0;
		for (int i = 1; i <= Math.min(n, m); i++) {
			for (int j = 1; j <= Math.min(2 * m - 1, n); j++) {
				long res = getGroups(m, i, j, 0);
				ans = (ans + choose(n - j + i, i) * res % MOD) % MOD;
			}
		}
		if (m == 0)
			out.println(1);
		else
			out.println(ans);
		out.close();
	}

	static long choose (long n, int k) {
		BigInteger res = new BigInteger("1");
		for (long i = n - k + 1; i <= n; i++)
			res = res.multiply(new BigInteger(Long.toString(i)));
		for (long i = 1; i <= k; i++)
			res = res.divide(new BigInteger(Long.toString(i)));
		return Long.parseLong(res.mod(new BigInteger(Integer.toString(MOD))).toString());
	}

	static long getGroups (int m, int groups, int left, int prev) {
		if (left < 0 || groups < 0)
			return 0;
		if (m == 0 && groups == 0 && left == 0)
			return 1;
		if (dp[m][groups][left][prev] != -1)
			return dp[m][groups][left][prev];

		long res = 0;
		for (int i : states) {
			int cnt = 0;
			for (int j = 0; j < 5; j++)
				if ((i & 1 << j) > 0)
					cnt++;
			if (cnt > m)
				continue;
			if (prev == 0) {
				res = (res + getGroups(m - cnt, groups - 1, left - 1, i)) % MOD;
			} else {
				if ((i & prev) == 0)
					res = (res + getGroups(m - cnt, groups, left - 1, i)) % MOD;
				res = (res + getGroups(m - cnt, groups - 1, left - 2, i)) % MOD;
			}
		}
		return dp[m][groups][left][prev] = res;
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
