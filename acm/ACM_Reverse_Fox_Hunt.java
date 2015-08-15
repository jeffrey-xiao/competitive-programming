package acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ACM_Reverse_Fox_Hunt {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Edge>> adj;
	static int r;
	static int c;
	static char[] grid;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			r = readInt();
			c = readInt();
			grid = new char[r * c];
			adj = new ArrayList<ArrayList<Edge>>();
			for (int x = 0; x < r * c; x++)
				adj.add(new ArrayList<Edge>());
			int start = 0;
			int end = 0;
			for (int x = 0; x < r; x++) {
				char[] next = next().toCharArray();
				for (int y = 0; y < c; y++) {
					int index = x * r + y;
					// System.out.println(index);
					grid[index] = next[y];
					if (index - c >= 0)
						adj.get(index).add(new Edge(index - c, 1));
					if (index + c < r * c)
						adj.get(index).add(new Edge(index + c, 1));
					if (index % c - 1 >= 0)
						adj.get(index).add(new Edge(index - 1, 1));
					if (index % c + 1 < c)
						adj.get(index).add(new Edge(index + 1, 1));
					if (grid[index] == 'F')
						start = index;
					else if (grid[index] == 'H')
						end = index;
				}
			}
			int ans = maxFlow(start, end);
			System.out.println(ans);
		}
	}

	private static int maxFlow (int start, int end) {
		while (bfs(start, end) != 0) {
		}
		int count = 0;

		if (end - c >= 0) {
			int index = adj.get(end - c).indexOf(new Edge(end, 0));
			if (index != -1)
				count += adj.get(end - c).get(index).flow == 0 ? 1 : 0;
		}
		if (end + c < r * c) {
			int index = adj.get(end + c).indexOf(new Edge(end, 0));
			if (index != -1)
				count += adj.get(end + c).get(index).flow == 0 ? 1 : 0;
		}
		if (end % c - 1 >= 0) {
			int index = adj.get(end - 1).indexOf(new Edge(end, 0));
			if (index != -1)
				count += adj.get(end - 1).get(index).flow == 0 ? 1 : 0;
		}
		if (end % c + 1 < c) {
			int index = adj.get(end + 1).indexOf(new Edge(end, 0));
			if (index != -1)
				count += adj.get(end + 1).get(index).flow == 0 ? 1 : 0;
		}
		return count;
	}

	private static int bfs (int start, int end) {
		Queue<Integer> moves = new LinkedList<Integer>();
		boolean[] v = new boolean[r * c];
		int[] prev = new int[r * c];
		for (int x = 0; x < r * c; x++)
			prev[x] = -2;
		prev[start] = -1;
		v[start] = true;
		moves.offer(start);
		while (!moves.isEmpty()) {
			int curr = moves.poll();
			if (curr == end)
				break;
			for (int x = 0; x < adj.get(curr).size(); x++) {
				Edge next = adj.get(curr).get(x);
				if (grid[next.dest] == 'T' || v[next.dest] || next.flow == 0)
					continue;
				v[next.dest] = true;
				prev[next.dest] = curr;
				moves.offer(next.dest);
			}
		}
		if (prev[end] == -2)
			return 0;
		int c = end;
		while (prev[c] != -1) {
			int index = adj.get(prev[c]).indexOf(new Edge(c, 0));
			int index1 = adj.get(c).indexOf(new Edge(prev[c], 0));

			int cost = adj.get(prev[c]).get(index).flow;
			int cost1 = adj.get(c).get(index1).flow;

			adj.get(prev[c]).set(index, new Edge(c, cost - 1));
			adj.get(c).set(index1, new Edge(prev[c], cost1 + 1));
			c = prev[c];
		}
		return 1;
	}

	static class Edge {
		int dest;
		int flow;

		Edge (int dest, int flow) {
			this.dest = dest;
			this.flow = flow;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Edge) {
				Edge e = (Edge) o;
				return dest == e.dest;
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
