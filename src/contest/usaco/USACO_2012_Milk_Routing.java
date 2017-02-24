package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class USACO_2012_Milk_Routing {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Edge>> adjlist = new ArrayList<ArrayList<Edge>>();
	static int n;
	static int milk;

	public static void main (String[] args) throws IOException {
		n = readInt();
		int m = readInt();
		milk = readInt();
		for (int y = 0; y < n; y++)
			adjlist.add(new ArrayList<Edge>());
		TreeSet<Integer> cost = new TreeSet<Integer>();
		for (int y = 0; y < m; y++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int l = readInt();
			int c = readInt();
			cost.add(c);
			adjlist.get(a).add(new Edge(b, l, c));
			adjlist.get(b).add(new Edge(a, l, c));
		}
		int min = Integer.MAX_VALUE;
		for (Integer currCost : cost) {
			min = Math.min(min, shortestPath(currCost));
		}
		System.out.println(min);
	}

	private static int shortestPath (Integer currCost) {
		boolean[] visited = new boolean[n];
		int[] minCost = new int[n];
		for (int x = 1; x < n; x++)
			minCost[x] = Integer.MAX_VALUE;
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(0, 0, Integer.MAX_VALUE));
		visited[0] = true;
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			for (int x = 0; x < adjlist.get(curr.curr).size(); x++) {
				Edge next = adjlist.get(curr.curr).get(x);

				if (next.capacity < currCost)
					continue;

				float f = curr.latency + next.latency + ((float)milk) / (Math.min(curr.capacity, next.capacity));

				if (minCost[next.dest] < f)
					continue;

				minCost[next.dest] = (int)f;
				visited[next.dest] = true;

				pq.add(new Node(next.dest, curr.latency + next.latency, Math.min(curr.capacity, next.capacity)));
			}
		}
		return minCost[n - 1];
	}

	static class Node implements Comparable<Node> {
		int curr;
		int latency;
		int capacity;

		Node (int curr, int latency, int capacity) {
			this.curr = curr;
			this.latency = latency;
			this.capacity = capacity;
		}

		@Override
		public int compareTo (Node n) {
			float f1 = latency + ((float)(milk) / capacity);
			float f2 = n.latency + ((float)(milk) / n.capacity);
			if (f1 == f2)
				return 0;
			return f1 - f2 < 0 ? -1 : 1;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Node) {
				Node n = (Node)o;
				return curr == n.curr;
			}
			return false;
		}
	}

	static class Edge {
		int dest;
		int latency;
		int capacity;

		Edge (int dest, int latency, int capacity) {
			this.dest = dest;
			this.latency = latency;
			this.capacity = capacity;
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
