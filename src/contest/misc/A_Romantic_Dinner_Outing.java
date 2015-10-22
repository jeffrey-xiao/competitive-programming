package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A_Romantic_Dinner_Outing {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static int n;
	static int[] t, w;
	static int[][][] dp;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		t = new int[n + 1];
		w = new int[n + 1];
		dp = new int[1 << n][n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			t[i] = readInt();
		for (int i = 1; i <= n; i++)
			w[i] = readInt();
		for (int i = 0; i < 1 << n; i++)
			for (int j = 0; j <= n; j++)
				for (int k = 0; k <= n; k++)
					dp[i][j][k] = -1;

		System.out.println(solve((1 << n) - 1, 0, 0, 1));

		pr.close();
	}

	static int solve (int s, int time, int wait, int currentTime) {
		if (dp[s][time][wait] != -1)
			return dp[s][time][wait];
		if (s == 0)
			return 0;
		int min = 1 << 30;
		for (int i = 0; i < n; i++) {
			if ((s & (1 << i)) > 0) {
				if (t[time] + w[wait] >= t[currentTime] + w[i + 1]) {
					min = Math.min(min, Math.max(t[time] + w[wait] - t[currentTime] - w[i + 1], solve(s ^ (1 << i), time, wait, currentTime + 1)));
				} else {
					min = Math.min(min, Math.max(t[currentTime] + w[i + 1] - t[time] - w[wait], solve(s ^ (1 << i), currentTime, i + 1, currentTime + 1)));
				}
			}
		}
		return dp[s][time][wait] = min;
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
