package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2008_DOSTAVA {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int R, C, D;
	static int[][] sum, dist;
	static ArrayList<ArrayList<Edge>> adj;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		R = readInt();
		C = readInt();

		sum = new int[R + 1][C + 1];
		dist = new int[2 * R][2 * R];
		adj = new ArrayList<ArrayList<Edge>>(2 * R);

		for (int i = 0; i < 2 * R; i++)
			adj.add(new ArrayList<Edge>());

		for (int i = 1; i <= R; i++)
			for (int j = 1; j <= C; j++)
				sum[i][j] = readInt() + sum[i][j - 1];

		for (int i = 1; i <= R; i++) {
			if (i > 1) {
				adj.get((i - 1) * 2).add(new Edge((i - 2) * 2, get(i - 1, 1)));
				adj.get((i - 1) * 2 + 1).add(new Edge((i - 2) * 2 + 1, get(i - 1, C)));
			}

			adj.get((i - 1) * 2).add(new Edge((i - 1) * 2 + 1, sum[i][C] - sum[i][1]));
			adj.get((i - 1) * 2 + 1).add(new Edge((i - 1) * 2, sum[i][C - 1]));

			if (i < R) {
				adj.get((i - 1) * 2).add(new Edge(i * 2, get(i + 1, 1)));
				adj.get((i - 1) * 2 + 1).add(new Edge(i * 2 + 1, get(i + 1, C)));
			}
		}

		for (int i = 0; i < 2 * R; i++) {
			dist[i] = getDist(i);
		}

		D = readInt();
		int currR = 1;
		int currC = 1;
		long ans = sum[1][1];
		for (int i = 0; i < D; i++) {
			int newR = readInt();
			int newC = readInt();

			long best = 1 << 30;

			// special case: same row
			if (currR == newR) {
				if (newC <= currC)
					best = Math.min(best, sum[currR][currC - 1] - sum[currR][newC - 1]);
				else
					best = Math.min(best, sum[currR][newC] - sum[currR][currC]);
			}
			// left; left
			best = Math.min(best, sum[currR][currC - 1] + sum[newR][newC] - sum[newR][1] + dist[(currR - 1) * 2][(newR - 1) * 2]);

			// left; right
			best = Math.min(best, sum[currR][currC - 1] + sum[newR][C - 1] - sum[newR][newC - 1] + dist[(currR - 1) * 2][(newR - 1) * 2 + 1]);

			// right; left
			best = Math.min(best, sum[currR][C] - sum[currR][currC] + sum[newR][newC] - sum[newR][1] + dist[(currR - 1) * 2 + 1][(newR - 1) * 2]);

			// right; right;
			best = Math.min(best, sum[currR][C] - sum[currR][currC] + sum[newR][C - 1] - sum[newR][newC - 1] + dist[(currR - 1) * 2 + 1][(newR - 1) * 2 + 1]);

			ans += best;
			currR = newR;
			currC = newC;
		}

		out.println(ans);
		out.close();
	}

	static int[] getDist (int u) {
		int[] d = new int[2 * R];
		Arrays.fill(d, 1 << 30);
		d[u] = 0;

		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(new Vertex(u, 0));

		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();

			for (Edge next : adj.get(curr.dest)) {
				if (d[next.dest] > curr.cost + next.cost) {
					d[next.dest] = curr.cost + next.cost;
					pq.offer(new Vertex(next.dest, d[next.dest]));
				}
			}
		}
		return d;
	}

	static int get (int r, int c) {
		return sum[r][c] - sum[r][c - 1];
	}

	static class Vertex implements Comparable<Vertex> {
		int dest, cost;

		Vertex (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo (Vertex v) {
			return cost - v.cost;
		}
	}

	static class Edge {
		int dest, cost;

		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
