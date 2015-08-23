package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class USACO_2015_Superbull {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int[] id = new int[2000];
	static int[] sz = new int[2000];

	public static void main (String[] args) throws IOException {
		for (int i = 0; i < 200; i++)
			id[i] = i;
		int n = readInt();
		long[] t = new long[n];
		for (int i = 0; i < n; i++)
			t[i] = readInt();
		ArrayList<Edge> e = new ArrayList<Edge>();
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				e.add(new Edge(i, j, (t[j] ^ t[i])));
		Collections.sort(e);
		long res = 0;
		for (Edge curr : e) {
			if (merge(curr.a, curr.b)) {
				res += curr.c;
				System.out.println(res);
			}
		}
		System.out.println(res);
	}

	private static int find (int i) {
		return i == id[i] ? i : (id[i] = find(id[i]));
	}

	private static boolean merge (int x, int y) {
		int rootx = find(x);
		int rooty = find(y);
		if (rootx == rooty)
			return false;
		if (sz[rootx] > sz[rooty]) {
			sz[rootx] += sz[rooty];
			id[rooty] = rootx;
		} else {
			sz[rooty] += sz[rootx];
			id[rootx] = rooty;
		}
		return true;
	}

	static class Edge implements Comparable<Edge> {
		int a, b;
		Long c;

		Edge (int a, int b, Long c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public int compareTo (Edge o) {
			return o.c.compareTo(c);
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
