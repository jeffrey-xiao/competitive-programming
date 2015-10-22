package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sleigh_Ride {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
	static int total = 0;
	static int max = 0;
	static boolean[] v;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		v = new boolean[n + 1];
		for (int x = 0; x <= n; x++)
			adj.add(new ArrayList<Edge>());
		for (int x = 0; x < n; x++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();
			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}
		dfs(0, 0);
		System.out.println(total * 2 - max);
	}

	private static void dfs (int i, int curr) {
		v[i] = true;
		boolean isLeaf = true;
		for (Edge e : adj.get(i)) {
			if (!v[e.dest]) {
				if (!isLeaf)
					total -= curr;
				dfs(e.dest, e.cost + curr);
				isLeaf = false;
			}
		}
		if (isLeaf) {
			total += curr;
			max = Math.max(max, curr);
		}
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
