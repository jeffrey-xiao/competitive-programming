package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CCC_2012_Stage_2_The_Hungary_Games {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static int m;

	public static void main (String[] args) throws IOException {
		ArrayList<ArrayList<Edge>> adjlist = new ArrayList<ArrayList<Edge>>();
		ArrayList<ArrayList<Edge>> revlist = new ArrayList<ArrayList<Edge>>();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		n = readInt();
		m = readInt();
		for (int x = 0; x < n; x++) {
			adjlist.add(new ArrayList<Edge>());
			revlist.add(new ArrayList<Edge>());
		}
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adjlist.get(a).add(new Edge(a, b, c));
			revlist.get(b).add(new Edge(b, a, c));
			edges.add(new Edge(a, b, c));
		}
		int[] s = shortestPath(adjlist, 0, n - 1);
		int[] d = shortestPath(revlist, n - 1, 0);
		long min = Integer.MAX_VALUE;
		int shortest = s[n - 1];
		for (int x = 0; x < edges.size(); x++) {
			Edge e = edges.get(x);
			long cost = (long) s[e.source] + d[e.dest] + e.cost;
			if (cost < min && cost > shortest)
				min = cost;
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static int[] shortestPath (ArrayList<ArrayList<Edge>> l, int s, int d) {
		int[] min = new int[n];
		for (int x = 0; x < n; x++) {
			if (x != s)
				min[x] = Integer.MAX_VALUE;
		}
		PriorityQueue<Node> moves = new PriorityQueue<Node>();
		moves.offer(new Node(s, 0));
		while (!moves.isEmpty()) {
			Node node = moves.poll();
			int size = l.get(node.index).size();
			for (int x = 0; x < size; x++) {
				Edge next = l.get(node.index).get(x);
				if (min[next.dest] <= node.cost + next.cost)
					continue;
				min[next.dest] = node.cost + next.cost;
				moves.offer(new Node(next.dest, min[next.dest]));
			}
		}
		return min;
	}

	static class Edge {
		int source;
		int dest;
		int cost;

		Edge (int source, int dest, int cost) {
			this.source = source;
			this.dest = dest;
			this.cost = cost;
		}
	}

	static class Node implements Comparable<Node> {
		int cost;
		int index;

		Node (int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo (Node arg0) {
			return this.cost - arg0.cost;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Node) {
				Node n = (Node) o;
				return this.index == n.index;
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
