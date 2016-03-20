package contest.codeforces;

import java.util.*;
import java.io.*;

public class CROC_2016_Elimination_E {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, K;
	static int MOD = 1000000007;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		K = readInt();
		char[] in = (" " + readLine()).toCharArray();

		int[] dp = new int[in.length + N + 1];
		int[][] state = new int[in.length + N + 1][K];
		for (int i = 0; i < K; i++)
			state[0][i] = -1;
		dp[0] = 1;
		for (int i = 1; i < in.length; i++) {
			dp[i] = dp[i - 1] * 2 % MOD;
			if (state[(i - 1)][in[i] - 'a'] != -1)
				dp[i] = (dp[i] - dp[state[(i - 1)][in[i] - 'a']] + MOD) % MOD;
			for (int j = 0; j < K; j++)
				state[i][j] = state[(i - 1)][j];
			state[i][in[i] - 'a'] = i - 1;
		}

		for (int i = in.length; i < N + in.length; i++) {
			dp[i] = dp[i - 1] * 2 % MOD;
			int index = 0;
			for (int j = 1; j < K; j++)
				if (state[(i - 1)][index] > state[(i - 1)][j])
					index = j;

			if (state[(i - 1)][index] != -1)
				dp[i] = (dp[i] - dp[state[(i - 1)][index]] + MOD) % MOD;

			for (int j = 0; j < K; j++)
				state[i][j] = state[(i - 1)][j];
			state[i][index] = i - 1;
		}
		out.println(dp[(in.length - 1 + N)]);
		out.close();
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
