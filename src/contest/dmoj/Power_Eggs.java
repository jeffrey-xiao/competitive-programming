package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Power_Eggs {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		int n = 32;
		int k = 32;
		// n tries and k eggs
		long[][] dp = new long[n + 1][k + 1];
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 1;
			dp[0][i] = 1;
		}

		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
		int t = readInt();
		for (int qq = 1; qq <= t; qq++) {
			int floors = readInt();
			int eggs = readInt();
			int lo = 0, hi = 32;
			while (lo <= hi) {
				int mid = (hi + lo) >> 1;
				if (dp[mid][eggs] - 1 >= floors)
					hi = mid - 1;
				else
					lo = mid + 1;
			}
			out.println(lo > 32 ? "Impossible" : lo);
		}
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
