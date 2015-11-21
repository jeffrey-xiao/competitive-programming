/*
 * Boruvka's algorithm is an algorithm for finding a minimum spamming tree.
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
import java.util.StringTokenizer;

public class Boruvka {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m;
	static ArrayList<Edge> e;
	static Edge[] min;
	static int[] id, sz;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int m = readInt();

		id = new int[n];
		sz = new int[n];

		for (int i = 0; i < n; i++) {
			id[i] = i;
			sz[i] = 1;
		}

		e = new ArrayList<Edge>();

		for (int i = 0; i < m; i++) {
			e.add(new Edge(readInt() - 1, readInt() - 1, readInt()));
		}

		int nodesLeft = n;
		int res = 0;

		while (nodesLeft > 1) {
			min = new Edge[n];
			for (Edge edge : e) {
				int rx = find(edge.a);
				int ry = find(edge.b);
				if (rx == ry)
					continue;
				if (min[rx] == null || min[rx].cost > edge.cost)
					min[rx] = edge;
				if (min[ry] == null || min[ry].cost > edge.cost)
					min[ry] = edge;
			}
			for (int i = 0; i < n; i++) {
				if (min[i] == null)
					continue;
				int rx = find(min[i].a);
				int ry = find(min[i].b);
				if (rx == ry)
					continue;
				merge(rx, ry);
				nodesLeft--;
				res += min[i].cost;
			}
		}
		out.println(res);
		out.close();
	}

	static int find (int x) {
		return x == id[x] ? x : (id[x] = find(id[x]));
	}

	static void merge (int x, int y) {
		if (sz[x] > sz[y]) {
			sz[x] += sz[y];
			id[y] = x;
		} else {
			sz[y] += sz[x];
			id[x] = y;
		}
	}

	static class Edge {
		int a, b, cost;

		Edge (int a, int b, int cost) {
			this.a = a;
			this.b = b;
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
