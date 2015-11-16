package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CCC_2010_S4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		for (int x = 0; x <= n; x++)
			adj.add(new ArrayList<Edge>());
		HashMap<Pair, ArrayList<Integer>> hm = new HashMap<Pair, ArrayList<Integer>>();
		for (int x = 1; x <= n; x++) {
			int e = readInt();
			int[] vertices = new int[e];
			for (int y = 0; y < e; y++) {
				vertices[y] = readInt();
			}
			for (int y = 0; y < e; y++) {
				int f = Math.min(vertices[y % e], vertices[(y + 1) % e]);
				int s = Math.max(vertices[y % e], vertices[(y + 1) % e]);
				Pair p = new Pair(f, s, readInt());
				if (hm.get(p) == null)
					hm.put(p, new ArrayList<Integer>());
				hm.get(p).add(x);
			}

		}
		for (Map.Entry<Pair, ArrayList<Integer>> e : hm.entrySet()) {
			if (e.getValue().size() == 1) {
				adj.get(0).add(new Edge(e.getValue().get(0), e.getKey().c));
			} else {
				int f = e.getValue().get(0);
				int s = e.getValue().get(1);
				int c = e.getKey().c;
				adj.get(f).add(new Edge(s, c));
				adj.get(s).add(new Edge(f, c));
			}
		}
		int i = Math.min(prim(0), prim(1));
		System.out.println(i);
	}

	private static int prim (int i) {
		boolean[] visited = new boolean[n + 1];
		if (i != 0)
			visited[0] = true;
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(i, 0));
		int sum = 0;
		main : while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			while (visited[curr.dest]) {
				if (pq.isEmpty())
					break main;
				curr = pq.poll();
			}
			visited[curr.dest] = true;
			sum += curr.cost;
			for (Edge e : adj.get(curr.dest)) {
				if (visited[e.dest])
					continue;
				pq.offer(e);
			}
		}
		for (boolean b : visited)
			if (!b)
				return 10000000;
		return sum;
	}

	static class Pair {
		int x, y, c;

		Pair (int x, int y, int c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}

		@Override
		public int hashCode () {
			return 31 * x + y;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Pair) {
				Pair p = (Pair) o;
				return x == p.x && y == p.y;
			}
			return false;
		}
	}

	static class Edge implements Comparable<Edge> {
		int dest;
		int cost;

		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo (Edge arg0) {
			return cost - arg0.cost;
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
