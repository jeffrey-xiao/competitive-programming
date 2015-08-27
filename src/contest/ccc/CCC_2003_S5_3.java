package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CCC_2003_S5_3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Edge>> adj;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int r = readInt();
		int d = readInt();
		adj = new ArrayList<ArrayList<Edge>>();
		int[] max = new int[n];
		for (int x = 0; x < n; x++) {
			adj.add(new ArrayList<Edge>());
			max[x] = Integer.MIN_VALUE;
		}
		for (int x = 0; x < r; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}
		PriorityQueue<Vertex> moves = new PriorityQueue<Vertex>();

		moves.offer(new Vertex(0, 0));
		max[0] = Integer.MAX_VALUE;
		while (!moves.isEmpty()) {
			Vertex curr = moves.poll();
			for (Edge e : adj.get(curr.index)) {
				if (max[e.dest] >= e.cost)
					continue;
				max[e.dest] = e.cost;
				moves.offer(new Vertex(e.dest, e.cost));
			}
		}
		int min = Integer.MAX_VALUE;
		for (int x = 0; x < d; x++)
			min = Math.min(min, max[readInt() - 1]);
		System.out.println(min);
	}

	static class Vertex implements Comparable<Vertex> {
		int index;
		int cost;

		Vertex (int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo (Vertex o) {
			return o.cost - cost;
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
