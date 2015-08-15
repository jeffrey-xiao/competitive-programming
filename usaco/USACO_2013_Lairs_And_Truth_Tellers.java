package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class USACO_2013_Lairs_And_Truth_Tellers {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static ArrayList<Edge> edges = new ArrayList<Edge>();
	static int[] id;
	static int[] size;
	static int[] state;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		id = new int[n];
		size = new int[n];
		state = new int[n];
		for (int x = 0; x < n; x++)
			id[x] = x;
		for (int x = 0; x < m; x++)
			edges.add(new Edge(readInt() - 1, readInt() - 1,
					next().charAt(0) == 'T'));
		int x = 0;
		for (; x < m; x++) {
			int a = edges.get(x).a;
			int b = edges.get(x).b;
			boolean same = edges.get(x).same;
			int roota = find(a);
			int rootb = find(b);
			if (roota != rootb) {
				boolean change = false;
				if (state[a] == 0 && state[b] == 0) {
					state[a] = 1;
					state[b] = (same ? 1 : -1);
				} else if (state[a] == 0) {
					state[a] = same ? state[b] : -state[b];
				} else if (state[b] == 0) {
					state[b] = same ? state[a] : -state[a];
				} else {
					if ((state[a] == state[b] && !same)
							|| (state[a] != state[b] && same)) {
						change = true;
					}
				}
				if (change)
					for (int y = 0; y < n; y++)
						if (find(y) == rootb)
							state[y] = -state[y];
				merge(roota, rootb);
			} else if (roota == rootb) {
				if ((same && state[a] != state[b])
						|| (!same && state[a] == state[b])) {
					break;
				}
			}
		}
		System.out.println(x);
	}

	static int find (int x) {
		while (x != id[x])
			x = id[x];
		return x;
	}

	static void merge (int x, int y) {
		int rootx = find(x);
		int rooty = find(y);

		if (size[rootx] > size[rooty]) {
			size[rootx] += size[rooty];
			id[rooty] = id[rootx];
		} else {
			size[rooty] += size[rootx];
			id[rootx] = id[rooty];
		}
	}

	static class Edge {
		int a, b;
		boolean same;

		Edge (int a, int b, boolean same) {
			this.a = a;
			this.b = b;
			this.same = same;
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
