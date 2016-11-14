package contest.acm;

import java.util.*;
import java.io.*;

public class ACM_NEERC_2014_F {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int N = readInt();
		int M = Math.min(N, readInt());
		long[][] dp = new long[3][N + 1];
		long[] val = new long[N + 1];
		for (int i = 1; i <= N; i++)
			val[i] = readInt() + val[i - 1];
		long ans = 0;
		for (int i = 1; i <= 2; i++)
			for (int j = 1; j <= N; j++)
				ans = Math.max(ans, dp[i][j] = Math.max(Math.max(dp[i][j - 1], dp[i][j]), dp[i - 1][Math.max(0, j - M)] + val[j] - val[Math.max(0, j - M)]));
		out.println(ans);
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

