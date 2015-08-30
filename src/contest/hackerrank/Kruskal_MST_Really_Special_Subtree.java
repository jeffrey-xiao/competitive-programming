package contest.hackerrank;

import java.util.*;
import java.io.*;

public class Kruskal_MST_Really_Special_Subtree {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

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
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (int i = 0; i < m; i++)
			edges.add(new Edge(readInt()-1, readInt()-1, readInt()));
		Collections.sort(edges);
		int res = 0;
		for (Edge e : edges) {
			int rx = find(e.a);
			int ry = find(e.b);
			if (rx != ry) {
				merge(rx, ry);
				res += e.c;
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
	static class Edge implements Comparable<Edge >{
		int a, b, c;
		Edge (int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
		@Override
		public int compareTo (Edge o) {
			return c - o.c;
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

