package woburn;

import java.util.*;
import java.io.*;
public class Woburn_Team_Practice_Strategy_Meeting {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int n, m;
	static int[][] adj;
	static int[][] dp;
	public static void main (String[] args) throws IOException {
		int t = readInt();
		for (int qq = 0; qq < t; qq++) {
			
			n = readInt();
			adj = new int[n][n];
			dp = new int[1 << n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					adj[i][j] = readInt();
				}
			}
			for (int i = 0; i < (1<<n); i++) {
				for (int j = 0; j < n; j++) {
					dp[i][j] = -1;
				}
			}
			System.out.println(solve(1, 0));
		}
	}
	static int solve (int v, int curr) {
		if (dp[v][curr] != -1)
			return dp[v][curr];
		if (curr == n-1) {
			return dp[v][curr] = 1;
		}
		int res = 0;
		for (int i = 0; i < n; i++) {
			if ((v & 1 << i) > 0)
				continue;
			if (adj[curr][i] == 0)
				continue;
			res += solve(v | 1 << i, i);
		}
		return dp[v][curr] = res;
	}
	private static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	private static int readInt() throws IOException {
		return Integer.parseInt(next());
	}
}