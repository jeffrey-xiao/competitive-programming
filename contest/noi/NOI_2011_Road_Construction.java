package contest.noi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NOI_2011_Road_Construction {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static long[] size;
	static ArrayList<ArrayList<Integer>> adj;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		size = new long[n];
		adj = new ArrayList<ArrayList<Integer>>();
		ArrayList<Edge> e = new ArrayList<Edge>();
		for (int x = 0; x < n; x++)
			adj.add(new ArrayList<Integer>());
		for (int x = 0; x < n - 1; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj.get(a).add(b);
			adj.get(b).add(a);
			e.add(new Edge(a, b, c));
		}
		boolean[] v = new boolean[n];
		dfs(0, v);
		long cost = 0;
		for (Edge i : e) {
			long a = size[i.src];
			long b = size[i.dest];
			cost += i.cost * Math.abs((a > b ? ((n - b) - b) : ((n - a) - a)));
		}
		System.out.println(cost);
	}

	private static long dfs (int i, boolean[] v) {
		v[i] = true;
		int total = 0;
		for (Integer x : adj.get(i))
			if (!v[x])
				total += dfs(x, v);
		size[i] = total + 1;
		return size[i];
	}

	static class Edge {
		int src, dest, cost;

		Edge (int src, int dest, int cost) {
			this.src = src;
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
