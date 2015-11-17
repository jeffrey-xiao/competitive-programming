package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Woburn_Challenge_2001_The_Man_With_The_Golden_Eye {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Edge>> adj;
	static int min;
	static int start;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			int n = readInt();
			min = Integer.MAX_VALUE;
			adj = new ArrayList<ArrayList<Edge>>();
			for (int x = 0; x < n; x++)
				adj.add(new ArrayList<Edge>());
			for (int x = 0; x < n; x++)
				for (int y = 0; y < n; y++) {
					int c = readInt();
					if (c != -1)
						adj.get(x).add(new Edge(y, c));
				}
			for (int x = 0; x < n; x++) {
				boolean[] visited = new boolean[n];
				start = x;
				int[] sum = new int[n];
				visited[x] = true;
				dfs(x, 0, -1, Arrays.copyOf(visited, visited.length), Arrays.copyOf(sum, sum.length));
				visited[x] = false;
			}
			System.out.println(min == Integer.MAX_VALUE ? "Infinity" : min);
		}
	}

	private static void dfs (int x, int s, int prev, boolean[] visited, int[] sum) {
		if (s >= min)
			return;
		for (int y = 0; y < adj.get(x).size(); y++) {
			Edge next = adj.get(x).get(y);
			if (!visited[next.dest] && prev != next.dest) {
				visited[next.dest] = true;
				sum[next.dest] = s + next.cost;
				dfs(next.dest, sum[next.dest], x, visited, sum);
				visited[next.dest] = false;
			} else if (visited[next.dest] && next.dest == start && next.dest != prev) {
				// System.out.println(start+1 + " START " +
				// (Math.abs(sum[x]-sum[next.dest])+next.cost));
				min = Math.min(Math.abs(sum[x] - sum[next.dest]) + next.cost, min);
			}
		}
	}

	static class Edge {
		int dest;
		int cost;

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
