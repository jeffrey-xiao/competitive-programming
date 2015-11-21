/*
 * Prim's algorithm is an algorithm for finding a minimum spamming tree.
 *
 * Time complexity: O(E log V)
 */

package codebook.graph.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Prim {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m;
	static ArrayList<ArrayList<Edge>> adj;
	static boolean[] v;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int m = readInt();

		ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
		v = new boolean[n];

		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Edge>());

		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}

		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		q.offer(new Edge(0, 0));
		int res = 0;
		while (!q.isEmpty()) {
			Edge curr = q.poll();
			if (v[curr.dest])
				continue;
			v[curr.dest] = true;
			res += curr.cost;
			for (Edge next : adj.get(curr.dest))
				if (!v[next.dest])
					q.offer(next);
		}
		out.println(res);
		out.close();
	}

	static class Edge implements Comparable<Edge> {
		int dest, cost;

		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo (Edge o) {
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
