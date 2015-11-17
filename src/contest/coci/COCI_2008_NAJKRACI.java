package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class COCI_2008_NAJKRACI {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int n, m;
	static int[] dist, from, to;
	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
	static int[] res;
	static final int MOD = 1000000007;

	public static void main (String[] args) throws IOException {
		n = readInt();
		m = readInt();
		dist = new int[n];
		from = new int[n];
		to = new int[n];
		res = new int[m];
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj.get(a).add(new Edge(b, c, i));
		}
		for (int i = 0; i < n; i++)
			dijkstras(i);
		for (int i = 0; i < m; i++)
			System.out.println(res[i]);
	}

	private static void dijkstras (int s) {
		for (int i = 0; i < n; i++) {
			dist[i] = 1 << 30;
			from[i] = to[i] = 0;
		}
		dist[s] = 0;
		to[s] = 1;
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		Stack<Integer> process = new Stack<Integer>();
		pq.offer(new Vertex(s, 0));
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			if (dist[curr.index] < curr.cost) {
				continue;
			}
			process.push(curr.index);
			for (Edge e : adj.get(curr.index)) {
				int nextCost = dist[curr.index] + e.cost;
				if (dist[e.dest] < nextCost)
					continue;
				if (dist[e.dest] > nextCost) {
					dist[e.dest] = nextCost;
					to[e.dest] = 0;
					pq.offer(new Vertex(e.dest, nextCost));
				}
				to[e.dest] = (to[e.dest] + to[curr.index]) % MOD;
			}
		}
		while (!process.isEmpty()) {
			int curr = process.pop();
			from[curr] = 1;
			for (Edge e : adj.get(curr)) {
				if (dist[e.dest] < e.cost + dist[curr])
					continue;
				from[curr] = (from[e.dest] + from[curr]) % MOD;
				res[e.index] = (int) ((res[e.index] + (long) (from[e.dest]) * (long) (to[curr])) % MOD);
			}
		}
	}

	static class Vertex implements Comparable<Vertex> {
		int index, cost;

		Vertex (int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo (Vertex o) {
			return cost - o.cost;
		}
	}

	static class Edge implements Comparable<Edge> {
		int dest, index, cost;

		Edge (int dest, int cost, int index) {
			this.dest = dest;
			this.cost = cost;
			this.index = index;
		}

		@Override
		public int compareTo (Edge o) {
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
