/*
 * Dijkstra's algorithm solves the single-source shortest path (SSSP) problem. It does not work with negative edge weights.
 *
 * Time complexity: O(E + V log V)
 */

package codebook.graph.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m, orig, dest;
	static ArrayList<ArrayList<Edge>> adj;
	static PriorityQueue<Vertex> pq;
	static int[] dist;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();

		orig = readInt() - 1;
		dest = readInt() - 1;

		adj = new ArrayList<ArrayList<Edge>>();

		dist = new int[n];
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Edge>());
			dist[i] = 1 << 30;
		}
		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj.get(a).add(new Edge(b, c));
		}
		pq = new PriorityQueue<Vertex>();
		dist[orig] = 0;
		pq.offer(new Vertex(orig, 0));
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			for (Edge next : adj.get(curr.index)) {
				if (dist[next.dest] > curr.cost + next.cost) {
					dist[next.dest] = curr.cost + next.cost;
					pq.offer(new Vertex(next.dest, dist[next.dest]));
				}
			}
		}
		out.println(dist[dest]);
		out.close();
	}

	static class Edge {
		int dest, cost;

		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
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
