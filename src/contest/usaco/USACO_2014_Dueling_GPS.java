package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class USACO_2014_Dueling_GPS {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static ArrayList<ArrayList<Edge>> newList = null;

	public static void main (String[] args) throws IOException {
		n = readInt();
		ArrayList<ArrayList<Edge>> reverseA = new ArrayList<ArrayList<Edge>>();
		ArrayList<ArrayList<Edge>> reverseB = new ArrayList<ArrayList<Edge>>();
		newList = new ArrayList<ArrayList<Edge>>();
		for (int x = 0; x < n; x++) {
			reverseA.add(new ArrayList<Edge>());
			reverseB.add(new ArrayList<Edge>());
			newList.add(new ArrayList<Edge>());
		}
		int m = readInt();
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			int d = readInt();
			reverseA.get(b).add(new Edge(a, c));
			reverseB.get(b).add(new Edge(a, d));
			newList.get(a).add(new Edge(b, 2));
		}
		// System.out.println();
		shortestPath(n - 1, 0, reverseA);
		// System.out.println();
		shortestPath(n - 1, 0, reverseB);
		// ystem.out.println();
		printShortestPath(0, n - 1, newList);
		/*
		 * for(int x = 0; x < newList.size(); x++){ for(int y = 0; y <
		 * newList.get(x).size(); y++){ Edge e = newList.get(x).get(y);
		 * System.out.printf("%d %d %d\n",x+1,e.dest+1,e.cost); } }
		 */
	}

	private static void shortestPath (int s, int d, ArrayList<ArrayList<Edge>> currList) {
		int[] min = new int[n];
		for (int x = 0; x < n; x++)
			if (x != s)
				min[x] = Integer.MAX_VALUE;
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.add(new Vertex(s, 0, -1));
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			min[curr.index] = curr.cost;
			// System.out.println(curr.index+1 + " " + (curr.prev+1));
			if (curr.prev != -1) {
				int index = newList.get(curr.index).indexOf(new Edge(curr.prev, 0));
				newList.get(curr.index).get(index).cost--;
			}
			for (int x = 0; x < currList.get(curr.index).size(); x++) {
				Edge e = currList.get(curr.index).get(x);
				Vertex next = new Vertex(e.dest, e.cost + curr.cost, curr.index);
				// System.out.println(curr.index + " " + next.index + " " +
				// next.cost);
				if (min[next.index] > next.cost) {
					min[next.index] = next.cost;
					pq.add(next);
				}
			}
		}
	}

	private static void printShortestPath (int s, int d, ArrayList<ArrayList<Edge>> currList) {
		boolean[] visited = new boolean[n];
		int[] min = new int[n];
		for (int x = 0; x < n; x++)
			min[x] = Integer.MAX_VALUE;
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.add(new Vertex(s, 0, -1));
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			visited[curr.index] = true;
			min[curr.index] = curr.cost;
			if (curr.index == d)
				break;
			// System.out.println(curr.index+1 + " " + (curr.prev+1));
			for (int x = 0; x < currList.get(curr.index).size(); x++) {
				Edge e = currList.get(curr.index).get(x);
				Vertex next = new Vertex(e.dest, e.cost + curr.cost, curr.index);
				// System.out.println(curr.index + " " + next.index + " " +
				// next.cost);
				if (visited[next.index])
					continue;
				if (min[next.index] > next.cost) {
					min[next.index] = next.cost;
					pq.remove(next);
					pq.add(next);
				}
			}
		}
		System.out.println(min[d]);
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
		public int compareTo (Vertex o) {
			return cost - o.cost;
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
