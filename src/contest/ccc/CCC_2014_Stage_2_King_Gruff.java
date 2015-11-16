package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CCC_2014_Stage_2_King_Gruff {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int n;
	static int m;

	public static void main (String[] args) throws IOException {
		ArrayList<ArrayList<Path>> adjlist = new ArrayList<ArrayList<Path>>();
		ArrayList<ArrayList<Path>> revlist = new ArrayList<ArrayList<Path>>();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		n = readInt();
		m = readInt();
		for (int x = 0; x < n; x++) {
			adjlist.add(new ArrayList<Path>());
			revlist.add(new ArrayList<Path>());
		}
		int start = readInt() - 1;
		int finish = readInt() - 1;
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			int d = readInt();
			adjlist.get(a).add(new Path(b, c));
			revlist.get(b).add(new Path(a, c));
			edges.add(new Edge(a, b, c, d));
		}
		int[] s = shortestPath(adjlist, start);
		int[] d = shortestPath(revlist, finish);
		ArrayList<Edge> sorted = new ArrayList<Edge>();
		for (int x = 0; x < m; x++) {
			int source = edges.get(x).source;
			int dest = edges.get(x).dest;
			if (s[source] >= Integer.MAX_VALUE || d[dest] >= Integer.MAX_VALUE)
				continue;
			int length = s[source] + d[dest] + edges.get(x).dist;
			sorted.add(new Edge(length, edges.get(x).cost));
		}
		int[] sums = new int[m + 1];
		int count = 1;
		Collections.sort(sorted);
		for (Edge e : sorted) {
			sums[count] = sums[count - 1] + e.cost;
			count++;

		}
		for (int q = readInt(); q > 0; q--) {
			int distance = readInt();
			int index = bsearch(sorted, distance);
			System.out.println(sums[index]);
		}
	}

	private static int bsearch (ArrayList<Edge> edges, int dist) {
		int lower = 0;
		int higher = edges.size() - 1;
		while (lower <= higher) {
			int mid = lower + (higher - lower) / 2;
			if (edges.get(mid).dist > dist)
				higher = mid - 1;
			else
				lower = mid + 1;
		}
		return lower;
	}

	private static int[] shortestPath (ArrayList<ArrayList<Path>> l, int s) {
		int[] min = new int[n];
		for (int x = 0; x < n; x++) {
			if (x != s)
				min[x] = Integer.MAX_VALUE;
		}
		PriorityQueue<Node> moves = new PriorityQueue<Node>();

		moves.offer(new Node(s, 0));

		while (!moves.isEmpty()) {
			Node node = moves.poll();
			if (node.cost > min[node.index])
				continue;
			int size = l.get(node.index).size();
			for (int x = 0; x < size; x++) {
				Path next = l.get(node.index).get(x);
				if (min[next.dest] <= node.cost + next.cost)
					continue;
				min[next.dest] = node.cost + next.cost;
				moves.offer(new Node(next.dest, min[next.dest]));
			}
		}
		return min;
	}

	static class Edge implements Comparable<Edge> {
		int source;
		int dest;
		int dist;
		int cost;

		Edge (int source, int dest, int dist, int cost) {
			this.source = source;
			this.dest = dest;
			this.cost = cost;
			this.dist = dist;
		}

		Edge (int dist, int cost) {
			this.dist = dist;
			this.cost = cost;
		}

		@Override
		public boolean equals (Object o) {
			return false;
		}

		@Override
		public int compareTo (Edge o) {
			return dist - o.dist;
		}
	}

	static class Path {
		int dest;
		int cost;

		Path (int dest, int cost) {
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
