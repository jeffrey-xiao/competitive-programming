package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CCC_2009_S4_3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		int[] min = new int[n];
		for (int x = 0; x < n; x++) {
			adj.add(new ArrayList<Edge>());
			min[x] = Integer.MAX_VALUE;
		}
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		int k = readInt();
		for (int x = 0; x < k; x++) {
			int a = readInt() - 1;
			int b = readInt();
			if (b < min[a]) {
				min[a] = b;
				pq.offer(new Vertex(a, b));
			}
		}
		int d = readInt() - 1;
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			if (curr.index == d)
				break;
			for (int x = 0; x < adj.get(curr.index).size(); x++) {
				Edge next = adj.get(curr.index).get(x);
				if (next.cost + curr.cost >= min[next.dest])
					continue;
				min[next.dest] = next.cost + curr.cost;
				pq.offer(new Vertex(next.dest, min[next.dest]));
			}
		}
		System.out.println(min[d]);
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
			return cost - o.cost;
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
