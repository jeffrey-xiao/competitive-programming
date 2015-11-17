package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2014_Math_Homework {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final int MOD = 1000000007;
	static int[][] C = new int[3301][3301];

	public static void main (String[] args) throws IOException {
		for (int i = 1; i <= 3300; i++) {
			for (int j = 1; j <= 3300; j++) {
				if (i == j || j == 1)
					C[i][j] = 1;
				else
					C[i][j] = (C[i - 1][j] + C[i - 1][j - 1]) % MOD;
			}
		}
		int t = readInt();
		for (int i = 0; i < t; i++) {
			int r = readInt();
			int c = readInt();
			if (c > r) {
				int temp = c;
				c = r;
				r = temp;
			}
			long ans = 0;
			for (int k = 0; k <= c; k++) {
				int mult = k % 2 == 0 ? 1 : -1;
				ans = (ans + mult * ((long) (C[c + 1][k + 1]) * pow(pow(2, c - k) - 1, r)) % MOD) % MOD;
			}
			System.out.println(ans < 0 ? ans + MOD : ans);
		}
	}

	static long pow (long a, long b) {
		if (b == 0)
			return 1;
		if (b == 1)
			return a;
		if (b % 2 == 0)
			return pow((a * a) % MOD, b / 2) % MOD;
		return (a * pow((a * a) % MOD, b / 2)) % MOD;
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
