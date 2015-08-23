package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Glenforest_Mostly_Talking {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj.get(a).add(new Edge(b, c, false));
		}
		int d = readInt();
		for (int i = 0; i < d; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj.get(a).add(new Edge(b, c, true));
		}
		int[][] min = new int[n][2];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < 2; j++)
				min[i][j] = Integer.MAX_VALUE;
		min[0][0] = 0;
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(new Vertex(0, 0, 0));
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			// System.out.println(curr.index+1 + " " + curr.cost + " " +
			// curr.type);
			for (Edge e : adj.get(curr.index)) {
				int nextCost = curr.cost + e.cost;
				if (!e.secret && min[e.dest][curr.type] > nextCost) {
					min[e.dest][curr.type] = nextCost;
					pq.offer(new Vertex(e.dest, nextCost, curr.type));
				}
				if (curr.type == 0 && e.secret
						&& min[e.dest][1] > curr.cost + e.cost) {
					min[e.dest][1] = curr.cost + e.cost;
					pq.offer(new Vertex(e.dest, curr.cost + e.cost, 1));
				}
			}
		}
		int ans = Math.min(min[n - 1][0], min[n - 1][1]);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	static class Vertex implements Comparable<Vertex> {
		int index, cost, type;

		Vertex (int index, int cost, int type) {
			this.index = index;
			this.cost = cost;
			this.type = type;
		}

		@Override
		public int compareTo (Vertex o) {
			return cost - o.cost;
		}
	}

	static class Edge {
		int dest, cost;
		boolean secret;

		Edge (int dest, int cost, boolean secret) {
			this.dest = dest;
			this.cost = cost;
			this.secret = secret;
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
