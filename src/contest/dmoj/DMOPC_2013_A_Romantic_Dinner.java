package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2013_A_Romantic_Dinner {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int M = readInt();
		int U = readInt();
		int R = readInt();
		int[][][] dp = new int[M + 1][U + 1][R + 1];
		for (int i = 0; i <= M; i++)
			for (int j = 0; j <= U; j++)
				for (int k = 0; k <= R; k++)
					dp[i][j][k] = -1;
		int[] v = new int[R + 1];
		int[] m = new int[R + 1];
		int[] u = new int[R + 1];
		dp[0][0][0] = 0;
		for (int i = 1; i <= R; i++) {
			v[i] = readInt();
			m[i] = readInt();
			u[i] = readInt();
		}
		for (int i = 1; i <= R; i++) {
			for (int j = 0; j <= M; j++) {
				for (int k = 0; k <= U; k++) {
					dp[j][k][i] = Math.max(dp[j][k][i], dp[j][k][i - 1]);
					if (j > 0)
						dp[j][k][i] = Math.max(dp[j][k][i], dp[j - 1][k][i]);
					if (k > 0)
						dp[j][k][i] = Math.max(dp[j][k][i], dp[j][k - 1][i]);

					if (j - m[i] >= 0 && k - u[i] >= 0 && dp[j - m[i]][k - u[i]][i - 1] != -1) {
						dp[j][k][i] = Math.max(dp[j][k][i], dp[j - m[i]][k - u[i]][i - 1] + v[i]);
					}
				}
			}
		}
		System.out.println(dp[M][U][R]);
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
