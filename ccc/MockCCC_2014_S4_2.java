package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MockCCC_2014_S4_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int n = 0;
	static ArrayList<Integer> shortestPath = null;

	public static void main (String[] args) throws IOException {
		ArrayList<ArrayList<Edge>> adjlist = new ArrayList<ArrayList<Edge>>();
		n = readInt();
		for (int x = 0; x < n; x++)
			adjlist.add(new ArrayList<Edge>());
		int m = readInt();
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adjlist.get(a).add(new Edge(b, 0));
			adjlist.get(b).add(new Edge(a, 0));
		}
		shortestPath(0, n - 1, adjlist);
		for (int x = 0; x < shortestPath.size() - 1; x++) {
			int a = shortestPath.get(x);
			int b = shortestPath.get(x + 1);
			int index1 = adjlist.get(a).indexOf(new Edge(b, 0));
			int index2 = adjlist.get(b).indexOf(new Edge(a, 0));
			adjlist.get(a).set(index1, new Edge(b, 1));
			adjlist.get(b).set(index2, new Edge(a, 1));
		}
		ArrayList<Integer> shortestPath2 = new ArrayList<Integer>(shortestPath);
		shortestPath(0, n - 1, adjlist);
		if (shortestPath2.size() != shortestPath.size()
				&& shortestPath2.indexOf(0) != -1
				&& shortestPath.indexOf(0) != -1) {
			System.out.println("Yes");
			return;
		} else if (shortestPath2.indexOf(0) == -1
				|| shortestPath.indexOf(0) == -1) {
			System.out.println("No");
			return;
		} else {
			Collections.sort(shortestPath);
			Collections.sort(shortestPath2);
			for (int x = 0; x < shortestPath.size(); x++) {
				if (shortestPath2.get(x) != shortestPath.get(x)) {
					System.out.println("Yes");
					return;
				}
			}
		}
		System.out.println("No");
	}

	private static void shortestPath (int i, int d,
			ArrayList<ArrayList<Edge>> adjlist) {
		int[] min = new int[n];
		int[] prevDest = new int[n];
		for (int x = 1; x < n; x++) {
			min[x] = Integer.MAX_VALUE;
			prevDest[x] = -2;
		}
		prevDest[0] = -1;
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.add(new Vertex(i, 0, -1));
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			for (int x = 0; x < adjlist.get(curr.index).size(); x++) {
				Edge e = adjlist.get(curr.index).get(x);
				Vertex next = new Vertex(e.dest, e.cost + curr.cost, curr.index);
				// System.out.println(curr.index + " " + next.index + " " +
				// next.cost);
				if (min[next.index] > next.cost) {
					min[next.index] = next.cost;
					prevDest[next.index] = curr.index;
					// pq.remove(next);
					pq.add(next);
				}
			}
		}

		shortestPath = new ArrayList<Integer>();
		int curr = n - 1;
		while (curr != -1 && curr != -2) {
			shortestPath.add(curr);
			curr = prevDest[curr];
		}
	}

	static class Vertex implements Comparable<Vertex> {
		int index;
		int cost;
		int prev;

		Vertex (int index, int cost, int prev) {
			this.index = index;
			this.cost = cost;
			this.prev = prev;
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