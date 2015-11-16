package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CCC_2002_Stage_2_Connect_The_Campus {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static double[][] matrix;
	static ArrayList<Connection> conn = new ArrayList<Connection>();

	public static void main (String[] args) throws IOException {
		n = readInt();
		matrix = new double[n][n];
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
				double value = getDist(x1, x2, y1, y2);
				matrix[x][y] = value;
			}
		}
		int m = readInt();
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			matrix[a][b] = 0;
			matrix[b][a] = 0;
		}
		System.out.printf("%.2f\n", prim());
		for (Connection c : conn) {
			System.out.println(c.source + 1 + " " + (c.dest + 1));
		}
	}

	private static double prim () {
		double[] minValue = new double[n];
		for (int x = 1; x < n; x++)
			minValue[x] = 2000000000;
		boolean[] visited = new boolean[n];
		PriorityQueue<Edge> moves = new PriorityQueue<Edge>();
		int curr = 0;
		double totalCost = 0;
		for (int x = 0; x < n - 1; x++) {
			visited[curr] = true;
			for (int y = 0; y < n; y++) {
				if (visited[y])
					continue;
				if (matrix[curr][y] < minValue[y]) {
					moves.remove(new Edge(y, 0));
					moves.offer(new Edge(y, matrix[curr][y]));
					minValue[y] = matrix[curr][y];

				}
			}
			if (moves.size() == 0)
				return -1;
			Edge next = moves.poll();
			totalCost += next.cost;
			if (next.cost != 0)
				conn.add(new Connection(curr, next.dest));
			curr = next.dest;
		}
		return totalCost;
	}

	static class Connection {
		int source;
		int dest;

		Connection (int source, int dest) {
			this.source = source;
			this.dest = dest;
		}
	}

	static class Edge implements Comparable<Edge> {
		int dest;
		double cost;

		Edge (int dest, double cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo (Edge o) {
			return cost - o.cost < 0 ? -1 : 1;
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

	private static double getDist (int x1, int x2, int y1, int y2) {
		double a = x1 - x2;
		double b = y1 - y2;
		return Math.sqrt(a * a + b * b);
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
