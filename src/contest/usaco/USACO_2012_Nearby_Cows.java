package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class USACO_2012_Nearby_Cows {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int k = readInt();
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

		for (int x = 0; x < n; x++)
			adj.add(new ArrayList<Integer>());

		for (int x = 0; x < n - 1; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		long[][] dp = new long[n][k + 1];
		for (int x = 0; x < n; x++) {
			int a = readInt();
			dp[x][0] = dp[x][1] = a;
		}
		for (int x = 1; x <= k; x++) {
			for (int y = 0; y < n; y++) {
				long total = 0;
				for (Integer z : adj.get(y))
					total += dp[z][x - 1];
				long minus = 0;
				if (x != 1)
					minus = dp[y][x - 2] * (adj.get(y).size() - 1);
				dp[y][x] += total - minus;
			}
		}
		for (int y = 0; y < n; y++) {
			System.out.println(dp[y][k]);
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
