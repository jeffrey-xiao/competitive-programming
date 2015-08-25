package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2015_Stage_2_Artskjid {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int n, m;
	static int[][] adj;
	static int[][] dp = new int[1 << 18][19];

	public static void main (String[] args) throws IOException {
		n = readInt();
		m = readInt();
		adj = new int[n][n];
		for (int i = 0; i < m; i++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();
			adj[a][b] = c;
		}
		for (int i = 0; i < (1 << 18); i++) {
			for (int j = 0; j < 18; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(solve(1, 0, 0));
	}

	static int solve (int v, int curr, int cnt) {

		if (dp[v][curr] != -1)
			return dp[v][curr];
		if (curr == n - 1) {
			return dp[v][curr] = 0;
		}
		int res = -1 << 30;
		for (int i = 0; i < n; i++) {
			if ((v & 1 << i) > 0)
				continue;
			if (adj[curr][i] == 0)
				continue;
			res = Math.max(res, adj[curr][i] + solve(v | 1 << i, i, cnt + 1));
		}
		return dp[v][curr] = res;
	}

	static class Edge {
		int dest, cost;

		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}

	private static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	private static int readInt () throws IOException {
		return Integer.parseInt(next());
	}
}