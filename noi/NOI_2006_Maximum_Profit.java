package noi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class NOI_2006_Maximum_Profit {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int n;
	static int[] node;
	static ArrayList<ArrayList<Edge>> adjlist;
	static boolean[] bought;

	public static void main (String[] args) throws IOException {
		n = readInt();
		bought = new boolean[n + 1];
		int m = readInt();
		adjlist = new ArrayList<ArrayList<Edge>>();
		node = new int[n + 1];

		for (int x = 0; x < n; x++) {
			node[x] = readInt();
			adjlist.add(new ArrayList<Edge>());
		}
		adjlist.add(new ArrayList<Edge>());
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adjlist.get(a).add(new Edge(b, c));
		}
		for (int x = 0; x < n; x++) {
			if (adjlist.get(x).size() == 0)
				adjlist.get(x).add(new Edge(n, 0));
		}
		int a = ff();
		System.out.println(a);
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
				return this.dest == e.dest;
			}
			return false;
		}
	}

	static int ff () {
		/*
		 * int total = 0; int value = bfs(); while(value != -1){ total+=value;
		 * value = bfs(); }
		 */

		// return value;
		return bfs();
	}

	static int bfs () {
		int[][] prev = new int[n + 1][2];
		boolean[] visited = new boolean[n + 1];
		prev[0][0] = -1;
		visited[0] = true;
		Queue<Integer> moves = new LinkedList<Integer>();
		moves.add(0);
		while (!moves.isEmpty()) {
			int curr = moves.poll();
			System.out.println("Current node in bfs: " + (curr + 1));
			for (int x = 0; x < adjlist.get(curr).size(); x++) {
				Edge next = adjlist.get(curr).get(x);
				if (visited[next.dest])
					continue;
				visited[next.dest] = true;
				prev[next.dest][0] = curr;
				prev[next.dest][1] = next.cost;
				moves.offer(next.dest);
			}
		}
		if (!visited[n])
			return -1;
		int curr = n;
		int totalCost = 0;
		int totalProfit = 0;
		for (int x = 0; x <= n; x++)
			System.out.println("Previous value of " + (x + 1) + " :"
					+ (prev[x][0] + 1));
		while (curr != -1) {
			if (!bought[curr])
				totalCost += node[curr];
			totalProfit += prev[curr][1];
			curr = prev[curr][0];
		}
		System.out.println(totalCost + " " + totalProfit);
		int netProfit = Math.max(0, totalCost - totalProfit);
		int index = -1;
		while (curr != -1) {
			if (!bought[curr])
				totalCost -= node[curr];
			totalProfit -= prev[curr][1];
			if (netProfit < totalCost - totalProfit) {
				index = prev[curr][0];
				netProfit = totalCost - totalProfit;
			}
			curr = prev[curr][0];
		}
		if (index == -1)
			return 0;
		curr = index;
		while (curr != -1) {
			bought[curr] = true;
			if (prev[curr][0] == -1)
				break;
			int edgeIndex = adjlist.get(prev[curr][0]).indexOf(
					new Edge(curr, 0));
			adjlist.get(prev[curr][0]).get(edgeIndex).cost = -1;
			curr = prev[curr][0];
		}
		return netProfit;
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
