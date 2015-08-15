import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TestClass1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int maxCost;
	static int n;
	static int e;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			n = readInt();
			e = readInt();
			maxCost = readInt();

			ArrayList<ArrayList<Edge>> adjlist = new ArrayList<ArrayList<Edge>>();
			for (int x = 0; x < n; x++)
				adjlist.add(new ArrayList<Edge>());

			for (int x = 0; x < e; x++) {
				int a = readInt() - 1;
				int b = readInt() - 1;
				int c = readInt();
				int d = readInt();
				// System.out.println(c + " " + d);
				adjlist.get(a).add(new Edge(b, d, c));
			}
			int q = readInt();
			for (int x = 0; x < q; x++) {
				int s = 0;
				int end = readInt() - 1;
				maxCost = readInt();
				int shortest = shortestPath(s, end, adjlist);
				System.out.println(shortest == Integer.MAX_VALUE ? -1
						: shortest);
			}
		}
	}

	private static int shortestPath (int s, int d,
			ArrayList<ArrayList<Edge>> adjlist) {
		int[] min = new int[n];
		for (int x = 1; x < n; x++)
			min[x] = Integer.MAX_VALUE;
		PriorityQueue<Vertex> moves = new PriorityQueue<Vertex>();
		moves.add(new Vertex(s, 0, 0));
		while (!moves.isEmpty()) {
			Vertex curr = moves.poll();
			if (curr.index == d)
				return curr.cost;
			// System.out.println("CURRENT " + curr.cost + " " + curr.index);
			for (int x = 0; x < adjlist.get(curr.index).size(); x++) {
				// System.out.println("NEXT " +
				// adjlist.get(curr.index).get(x).dest);
				Edge next = adjlist.get(curr.index).get(x);
				if (next.light + curr.totalLight > maxCost
						|| next.cost + curr.cost >= min[next.dest])
					continue;
				min[next.dest] = next.cost + curr.cost;
				moves.add(new Vertex(next.dest, curr.cost + next.cost,
						curr.totalLight + next.light));
			}
		}
		return min[d];
	}

	static class Vertex implements Comparable<Vertex> {
		int index;
		int cost;
		int totalLight;

		Vertex (int index, int cost, int totalLight) {
			this.index = index;
			this.cost = cost;
			this.totalLight = totalLight;
		}

		@Override
		public int compareTo (Vertex v) {
			return totalLight - v.totalLight;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Vertex) {
				Vertex v = (Vertex) o;
				return index == v.index && v.totalLight == totalLight;
			}
			return false;
		}
	}

	static class Edge {
		int dest;
		int cost;
		int light;

		Edge (int dest, int cost, int light) {
			this.dest = dest;
			this.cost = cost;
			this.light = light;
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