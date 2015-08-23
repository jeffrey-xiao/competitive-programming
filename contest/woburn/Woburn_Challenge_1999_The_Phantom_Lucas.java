package contest.woburn;

import java.io.BufferedReader;
//Don't think this is correct solution but it passes the test cases...
//Probably needs tarjan strongly connected components.
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Woburn_Challenge_1999_The_Phantom_Lucas {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int n;
	static ArrayList<ArrayList<Edge>> adj;

	public static void main (String[] args) throws IOException {
		cases : for (int t = readInt(); t > 0; t--) {
			n = readInt();
			adj = new ArrayList<ArrayList<Edge>>();
			for (int x = 0; x < n; x++)
				adj.add(new ArrayList<Edge>());
			boolean[] edgeTo = new boolean[n];
			for (int a = readInt(), b = readInt(), c = readInt(); a != 0; a = readInt(), b = readInt(), c = readInt()) {
				adj.get(a - 1).add(new Edge(b - 1, c));
				edgeTo[b - 1] = true;
			}
			for (int x = 0; x < n; x++)
				if (!edgeTo[x]) {
					System.out.println("IMPOSSIBLE");
					continue cases;
				}
			boolean[] v = new boolean[n];
			int total = 0;
			for (int x = 0; x < n; x++) {
				if (!v[x]) {
					v[x] = true;
					total += bfs(x, v);
				}
			}
			System.out.println(total);
		}
	}

	private static int bfs (int y, boolean[] v) {
		Integer min = Integer.MAX_VALUE;
		for (int x = 0; x < adj.get(y).size(); x++) {
			Edge next = adj.get(y).get(x);
			min = Math.min(next.cost, min);
			if (!v[next.dest]) {
				v[next.dest] = true;
				min = Math.min(min, bfs(next.dest, v));
			}
		}
		return min;
	}

	static class Edge {
		int cost;
		int dest;

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
