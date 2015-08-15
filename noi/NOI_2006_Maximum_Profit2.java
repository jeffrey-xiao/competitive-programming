package noi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class NOI_2006_Maximum_Profit2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
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
		for (int x = 0; x < n; x++) {
			adjlist.get(x + m + 1).add(new Edge(n + m + 1, readInt()));
			adjlist.get(n + m + 1).add(new Edge(x + m + 1, 0));
		}
		int total = 0;
		for (int x = 1; x <= m; x++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();
			adjlist.get(0).add(new Edge(x, c));
			total += c;
			adjlist.get(x).add(new Edge(m + a, Integer.MAX_VALUE));
			adjlist.get(x).add(new Edge(m + b, Integer.MAX_VALUE));

			adjlist.get(x).add(new Edge(0, 0));
			adjlist.get(m + a).add(new Edge(x, 0));
			adjlist.get(m + b).add(new Edge(x, 0));
		}
		// for(int x = 0; x < adjlist.size(); x++)
		// System.out.println(adjlist.get(x));
		System.out.println(maxflow(total));
		// for(int x = 0; x < adjlist.size(); x++)
		// System.out.println(adjlist.get(x));

	}

	private static int maxflow (int total2) {
		while (bfs(0, n + m + 1))
			;
		return total2 - total;
	}

	private static boolean bfs (int i, int j) {
		boolean[] visited = new boolean[j + 1];
		int[] prev = new int[j + 1];
		int[] min = new int[j + 1];
		for (int x = 0; x < j + 1; x++)
			min[x] = Integer.MAX_VALUE;
		Queue<Integer> moves = new LinkedList<Integer>();
		visited[i] = true;
		prev[i] = -1;
		moves.offer(i);
		int curr = 0;
		while (!moves.isEmpty()) {
			curr = moves.poll();
			// System.out.println(curr);
			if (curr == j)
				break;
			for (int x = 0; x < adjlist.get(curr).size(); x++) {
				Edge next = adjlist.get(curr).get(x);
				if (visited[next.dest] || next.cost == 0)
					continue;
				visited[next.dest] = true;
				prev[next.dest] = curr;
				min[next.dest] = Math.min(next.cost, min[curr]);
				moves.offer(next.dest);
			}
		}
		if (curr != j)
			return false;

		// int neck = Integer.MAX_VALUE;
		// while(prev[curr] != -1){
		// int previous = prev[curr];
		// int index = adjlist.get(previous).indexOf(new Edge(curr,0));
		// neck = Math.min(neck, adjlist.get(previous).get(index).cost);
		// curr = previous;
		// }
		// curr = j;
		// System.out.print(curr + " ");
		while (prev[curr] != -1) {
			int previous = prev[curr];
			// System.out.print(previous + " ");
			int index = adjlist.get(previous).indexOf(new Edge(curr, 0));
			int cost = adjlist.get(previous).get(index).cost;

			int index1 = adjlist.get(curr).indexOf(new Edge(previous, 0));
			int cost1 = adjlist.get(curr).get(index1).cost;

			adjlist.get(previous).set(index, new Edge(curr, cost - min[j]));
			adjlist.get(curr).set(index1, new Edge(previous, cost1 + min[j]));
			curr = previous;
		}
		// System.out.println();
		total += min[j];
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
