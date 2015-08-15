package ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class IOI_2010_Traffic_Congestion {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int n;
	static int[] sum;
	static boolean[] visited;
	static int[] dp;
	static ArrayList<ArrayList<Integer>> adjlist;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		adjlist = new ArrayList<ArrayList<Integer>>();
		sum = new int[n];
		visited = new boolean[n];
		dp = new int[n];
		for (int x = 0; x < n; x++) {
			adjlist.add(new ArrayList<Integer>());
			sum[x] = readInt();
		}
		for (int x = 0; x < n - 1; x++) {
			int a = readInt();
			int b = readInt();
			adjlist.get(a).add(b);
			adjlist.get(b).add(a);
		}
		int root = n / 2;
		dfs(root);
		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int x = 0; x < n; x++) {
			dp[x] = Math.max(dp[x], sum[root] - sum[x]);
			if (dp[x] < min) {
				min = dp[x];
				index = x;
			}

		}
		System.out.println(index);
	}

	private static int dfs (int c) {
		visited[c] = true;
		int total = sum[c];
		for (int x = 0; x < adjlist.get(c).size(); x++) {
			int next = adjlist.get(c).get(x);
			if (visited[next])
				continue;
			int nextSum = dfs(next);
			total += nextSum;
			dp[c] = Math.max(nextSum, dp[c]);
		}
		sum[c] = total;
		return total;
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
