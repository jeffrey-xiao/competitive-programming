package acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ACM_Foxling_Feeding_Frenzy {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static final int MOD = 1000000007;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			int n = readInt();
			int m = readInt();
			int sumOfHi = 0;
			Interval[] i = new Interval[n + 1];
			for (int x = 1; x <= n; x++) {
				int a = readInt();
				int b = readInt();
				m -= a;
				b -= a;
				a -= a;
				sumOfHi += b;
				i[x] = new Interval(a, b);
			}
			if (m < 0 || m > sumOfHi) {
				System.out.println(0);
				continue;
			}
			long[][] dp = new long[n + 1][m + 1];
			for (int x = 0; x < n + 1; x++)
				for (int y = 1; y < m + 1; y++)
					dp[x][y] = -1;
			dp[0][0] = 0;
			for (int x = 1; x <= n; x++) {
				for (int y = 1; y <= m; y++) {
					long sum = Integer.MIN_VALUE;
					for (int z = 0; z <= i[x].hi; z++) {
						if (y - z >= 0) {
							if (dp[x - 1][y - z] >= 0)
								sum = (Math.max(sum, 0) + dp[x - 1][y - z])
										% MOD;
						}
					}
					if (y - i[x].hi <= 0)
						dp[x][y] = sum + 1;
					else
						dp[x][y] = sum;
				}
			}

			// for(int x = 0; x <= n; x++){
			// for(int y = 0; y <= m; y++)
			// System.out.print(dp[x][y] + " ");
			// System.out.println();
			// }
			System.out.println(dp[n][m] % MOD);
		}
	}

	static class Interval {
		int lo, hi;

		Interval (int lo, int hi) {
			this.lo = lo;
			this.hi = hi;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
