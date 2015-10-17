package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2015_4 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, k;
	static int[] flow;
	static int[][] dp;
	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		k = readInt();
		dp = new int[n][k+1];
		flow = new int[n];
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Edge>());
		for (int i = 1; i < n; i++) {
			flow[i] = readInt();
			int a = readInt()-1;
			int b = readInt();
			adj.get(i).add(new Edge(a, b));
			adj.get(a).add(new Edge(i, b));
		}
		solve(0, -1, 1 << 30);
		out.println(dp[0][k]);
		out.close();
	}

	static void solve (int curr, int par, int flowOut) {
		for (Edge next : adj.get(curr))
			if (next.dest != par)
				solve(next.dest, curr, next.cost);
		for (Edge next : adj.get(curr))
			if (next.dest != par)
				for (int i = k; i >= 0; i--) 
					for (int j = 0; j <= i; j++) 
						dp[curr][i] = Math.max(dp[curr][i], dp[curr][i-j] + dp[next.dest][j]);

		for (int i = k; i >= 1; i--)
			dp[curr][i] = Math.max(Math.min(flowOut, dp[curr][i] + flow[curr]), dp[curr][i-1] + flow[curr]);
		dp[curr][0] = Math.min(flowOut, dp[curr][0] + flow[curr]);
	}

	static class Edge {
		int dest, cost;
		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
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

