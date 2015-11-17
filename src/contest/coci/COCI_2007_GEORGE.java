package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class COCI_2007_GEORGE {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int n, m, a, b, k, g;
	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
	static int[][] reach;

	public static void main (String[] args) throws IOException {
		n = readInt();
		m = readInt();

		reach = new int[n][n];
		int[] min = new int[n];
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Edge>());
			for (int j = 0; j < n; j++)
				reach[i][j] = -1;
			min[i] = Integer.MAX_VALUE;
		}

		a = readInt() - 1;
		b = readInt() - 1;
		k = readInt();
		g = readInt();
		int[] path = new int[g];
		for (int i = 0; i < g; i++)
			path[i] = readInt() - 1;
		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}
		int time = 0;
		for (int i = 0; i < path.length - 1; i++) {
			reach[path[i]][path[i + 1]] = time;
			reach[path[i + 1]][path[i]] = time;
			if (i != path.length - 1) {
				int index = adj.get(path[i]).indexOf(new Edge(path[i + 1], 0));
				time += adj.get(path[i]).get(index).cost;
			}
		}
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(new Vertex(a, 0));
		min[a] = 0;
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			if (curr.index == b) {
				System.out.println(curr.cost);
				break;
			}
			for (Edge e : adj.get(curr.index)) {
				int nextDest = e.dest;
				int nextCost = curr.cost + e.cost;
				if (reach[curr.index][nextDest] != -1) {
					if (curr.cost + k >= reach[curr.index][nextDest]) {
						nextCost += Math.max(0, e.cost - (curr.cost + k - reach[curr.index][nextDest]));
					}
				}
				if (min[nextDest] <= nextCost)
					continue;
				min[nextDest] = nextCost;
				pq.offer(new Vertex(nextDest, nextCost));
			}
		}
	}

	static class Edge {
		int dest, cost;

		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		public boolean equals (Object o) {
			if (o instanceof Edge) {
				return dest == ((Edge) o).dest;
			}
			return false;
		}
	}

	static class Vertex implements Comparable<Vertex> {
		int cost, index;

		Vertex (int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo (Vertex o) {
			return cost - o.cost;
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
