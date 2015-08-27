package contest.noi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class NOI_2006_Maximum_Profit3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static int m;
	static int total = 0;
	static ArrayList<ArrayList<Edge>> adjlist;

	public static void main (String[] args) throws IOException {
		n = readInt();
		m = readInt();
		adjlist = new ArrayList<ArrayList<Edge>>();
		for (int x = 0; x < n + m + 2; x++) {
			adjlist.add(new ArrayList<Edge>());
		}
		for (int x = 0; x < n; x++)
			adjlist.get(x + m + 1).add(new Edge(n + m + 1, readInt()));
		int total = 0;
		for (int x = 1; x <= m; x++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();
			adjlist.get(0).add(new Edge(x, c));
			total += c;
			adjlist.get(x).add(new Edge(m + a, Integer.MAX_VALUE));
			adjlist.get(x).add(new Edge(m + b, Integer.MAX_VALUE));
		}
		/*
		 * for(int x = 0; x < adjlist.size(); x++)
		 * System.out.println(adjlist.get(x));
		 */
		System.out.println(maxflow(total));
		/*
		 * for(int x = 0; x < adjlist.size(); x++)
		 * System.out.println(adjlist.get(x));
		 */

	}

	private static int maxflow (int total2) {
		while (bfs(0, n + m + 1))
			;
		return total2 - total;
	}

	static class Next implements Comparable<Next> {
		int moves;
		int dest;
		int cost;

		Next (int dest, int moves, int cost) {
			this.dest = dest;
			this.moves = moves;
			this.cost = cost;
		}

		@Override
		public int compareTo (Next o) {
			if (this.moves == o.moves)
				return o.cost - this.cost;
			return this.moves - o.moves;
		}
	}

	private static boolean bfs (int i, int j) {
		boolean[] visited = new boolean[j + 1];
		int[] prev = new int[j + 1];
		int[] max = new int[j + 1];

		Queue<Next> moves = new LinkedList<Next>();
		visited[i] = true;
		prev[i] = -1;
		moves.offer(new Next(i, 0, 0));
		Next curr = null;
		// System.out.println();
		while (!moves.isEmpty()) {
			curr = moves.poll();
			// System.out.println(curr.dest);
			if (curr.dest == j)
				break;
			for (int x = 0; x < adjlist.get(curr.dest).size(); x++) {
				Edge next = adjlist.get(curr.dest).get(x);
				int nextValue = next.cost == Integer.MAX_VALUE ? 0 : next.cost;
				if (max[next.dest] >= Math.max(curr.cost, nextValue) || next.cost == 0)
					continue;
				max[next.dest] = Math.max(curr.cost, nextValue);
				visited[next.dest] = true;
				prev[next.dest] = curr.dest;

				moves.offer(new Next(next.dest, Math.max(curr.cost, nextValue), curr.moves + 1));
			}
		}
		int c = curr.dest;
		if (c != j)
			return false;

		int neck = Integer.MAX_VALUE;
		while (prev[c] != -1) {
			int previous = prev[c];
			int index = adjlist.get(previous).indexOf(new Edge(c, 0));
			neck = Math.min(neck, adjlist.get(previous).get(index).cost);
			c = previous;
		}
		c = j;

		// System.out.println(neck + " NECK");
		while (prev[c] != -1) {
			int previous = prev[c];
			// System.out.print(previous + " ");
			int index = adjlist.get(previous).indexOf(new Edge(c, 0));
			int cost = adjlist.get(previous).get(index).cost;

			// adjlist.get(previous).set(index, new Edge(curr, cost-min[j]));
			adjlist.get(previous).set(index, new Edge(c, cost - neck));
			c = previous;
		}
		// System.out.println();
		total += neck;
		return true;

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
				Edge e = (Edge) o;
				return dest == e.dest;
			}
			return false;
		}

		@Override
		public String toString () {
			return dest + " D " + cost + " C ";
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
