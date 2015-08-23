package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class USACO_2014_Watering_The_Fields {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int n;
	static int[][] matrix;

	public static void main (String[] args) throws IOException {
		n = readInt();
		int c = readInt();
		matrix = new int[n][n];
		int[][] nodes = new int[n][2];
		for (int x = 0; x < n; x++) {
			nodes[x][0] = readInt();
			nodes[x][1] = readInt();
		}
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				int x1 = nodes[x][0];
				int x2 = nodes[y][0];
				int y1 = nodes[x][1];
				int y2 = nodes[y][1];
				int value = getDist(x1, x2, y1, y2);
				if (value < c)
					value = 0;
				matrix[x][y] = value;
				// matrix[y][x] = value;
			}
		}
		System.out.println(prim());
	}

	private static int prim () {
		int[] minValue = new int[n];
		for (int x = 1; x < n; x++)
			minValue[x] = 2000000000;
		boolean[] visited = new boolean[n];
		PriorityQueue<Edge> moves = new PriorityQueue<Edge>();
		int curr = 0;
		int totalCost = 0;
		for (int x = 0; x < n - 1; x++) {
			visited[curr] = true;
			for (int y = 0; y < n; y++) {
				if (visited[y])
					continue;
				if (matrix[curr][y] != 0 && matrix[curr][y] < minValue[y]) {
					moves.remove(new Edge(y, 0));
					moves.offer(new Edge(y, matrix[curr][y]));
					minValue[y] = matrix[curr][y];

				}
			}
			if (moves.size() == 0)
				return -1;
			Edge next = moves.poll();
			// System.out.println(next.cost);
			totalCost += next.cost;
			curr = next.dest;
		}
		return totalCost;
	}

	static class Edge implements Comparable<Edge> {
		int dest;
		int cost;

		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo (Edge e) {
			return cost - e.cost;
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

	private static int getDist (int x1, int x2, int y1, int y2) {
		int a = x1 - x2;
		int b = y1 - y2;
		return a * a + b * b;
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
