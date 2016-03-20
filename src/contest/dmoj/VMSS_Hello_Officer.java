package contest.dmoj;

import java.util.*;
import java.io.*;

public class VMSS_Hello_Officer {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m, b, q;
	static ArrayList<ArrayList<Pair>> adj = new ArrayList<ArrayList<Pair>>();
	static int[] dist;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		b = readInt() - 1;
		q = readInt();

		dist = new int[n];

		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Pair>());
			dist[i] = 1 << 30;
		}
		for (int i = 0; i < m; i++) {
			int x = readInt() - 1;
			int y = readInt() - 1;
			int c = readInt();
			adj.get(x).add(new Pair(y, c));
			adj.get(y).add(new Pair(x, c));
		}
		dist[b] = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		pq.offer(new Pair(b, 0));

		while (!pq.isEmpty()) {
			Pair curr = pq.poll();
			for (Pair next : adj.get(curr.dest)) {
				if (dist[next.dest] > dist[curr.dest] + next.cost) {
					dist[next.dest] = dist[curr.dest] + next.cost;
					pq.offer(new Pair(next.dest, dist[next.dest]));
				}
			}
		}

		for (int i = 0; i < q; i++) {
			int x = readInt() - 1;
			out.println(dist[x] == 1 << 30 ? -1 : dist[x]);
		}

		out.close();
	}

	static class Pair implements Comparable<Pair> {
		int dest, cost;

		Pair (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo (Pair o) {
			return cost - o.cost;
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
