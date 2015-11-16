package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MockCCC_2014_S4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
	static int n;
	static int m;
	static ArrayList<Integer> shortestPath = new ArrayList<Integer>();

	public static void main (String[] args) throws IOException {
		n = readInt();
		m = readInt();
		for (int x = 0; x < n; x++)
			adj.add(new ArrayList<Edge>());
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(new Edge(b, 1));
			adj.get(b).add(new Edge(a, 1));
		}
		int s1 = shortestPath(0, true);
		for (int x = 1; x < shortestPath.size(); x++) {
			int a = shortestPath.get(x);
			int b = shortestPath.get(x - 1);
			int index1 = adj.get(a).indexOf(new Edge(b, 2));
			int index2 = adj.get(b).indexOf(new Edge(a, 2));
			adj.get(a).set(index1, new Edge(b, 2));
			adj.get(b).set(index2, new Edge(a, 2));
		}
		int s2 = shortestPath(1, false);
		if (s1 != s2 && s1 != -1 && s2 != -1)
			System.out.println("Yes");
		else
			System.out.println("No");
	}

	static int shortestPath (int mod, boolean getShortest) {
		int[] min = new int[n];
		int[] prev = new int[n];
		PriorityQueue<Vertex> moves = new PriorityQueue<Vertex>();
		for (int x = 1; x < n; x++) {
			min[x] = Integer.MAX_VALUE;
			prev[x] = -2;
		}
		prev[0] = -1;
		moves.offer(new Vertex(0, 0));
		while (!moves.isEmpty()) {
			Vertex curr = moves.poll();
			if (curr.index == n - 1)
				break;
			for (int x = 0; x < adj.get(curr.index).size(); x++) {
				Edge next = adj.get(curr.index).get(x);
				if (curr.cost - mod + next.cost >= min[next.dest])
					continue;
				min[next.dest] = curr.cost - mod + next.cost;
				prev[next.dest] = curr.index;
				moves.offer(new Vertex(next.dest, min[next.dest]));
			}
		}
		if (getShortest) {
			int curr = n - 1;
			while (curr != -1 && curr != -2) {
				shortestPath.add(curr);
				curr = prev[curr];
			}
		}
		return min[n - 1];
	}

	static class Vertex implements Comparable<Vertex> {
		int index;
		int cost;

		Vertex (int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo (Vertex v) {
			return cost - v.cost;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Vertex) {
				Vertex v = (Vertex) o;
				return index == v.index;
			}
			return false;
		}
	}

	static class Edge {
		int dest;
		int cost;

		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Edge) {
				Edge v = (Edge) o;
				return dest == v.dest;
			}
			return false;
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
