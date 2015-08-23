package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class COCI_2014_STOGOVI {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;
	static int[] size;
	static int[] element;
	static int[] prev;
	static int[][] pa;
	static int LN = 19;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				System.out)));
		br = new BufferedReader(new FileReader("test.txt"));
		// ps = new PrintWriter("output.txt");

		int n = readInt();
		prev = new int[n + 1];
		element = new int[n + 1];
		size = new int[n + 1];
		int[] compress = new int[n + 1];
		for (int i = 0; i <= n; i++)
			compress[i] = i;
		prev[0] = -1;
		ArrayList<Query> q = new ArrayList<Query>();
		for (int i = 1; i <= n; i++) {
			char c = readCharacter();
			if (c == 'a') {
				prev[i] = compress[readInt()];
				size[i] = size[prev[i]] + 1;
				element[i] = i;
			} else if (c == 'b') {
				int v = compress[readInt()];
				q.add(new Query(element[v], -1));
				prev[i] = prev[v];
				element[i] = element[prev[i]];
				size[i] = size[prev[i]];
				prev[i] = prev[prev[i]];
				compress[i] = compress[prev[v]];
				// System.out.println("HERE: " + prev[v] + " " + v);
			} else {
				int v = compress[readInt()];
				int w = compress[readInt()];
				prev[i] = prev[v];
				element[i] = element[v];
				size[i] = size[v];
				compress[i] = v;
				q.add(new Query(w, v));
			}
			// System.out.println(element[i] + " " + size[i] + " " + prev[i] +
			// " " + compress[i]);
		}
		pa = new int[n + 1][LN];
		for (int i = 0; i <= n; i++)
			pa[i][0] = prev[i];
		for (int i = 1; i < LN; i++)
			for (int j = 0; j <= n; j++)
				if (pa[j][i - 1] != -1)
					pa[j][i] = pa[pa[j][i - 1]][i - 1];

		for (Query i : q) {
			if (i.v == -1)
				System.out.println(i.u);
			else {
				System.out.println(size[lca(i.u, i.v)]);
			}
		}
		ps.close();
	}

	static int lca (int u, int v) {
		if (size[u] < size[v]) {
			int temp = u;
			u = v;
			v = temp;
		}

		for (int i = LN - 1; i >= 0; i--) {
			if (pa[u][i] != -1 && size[pa[u][i]] >= size[v])
				u = pa[u][i];
		}
		if (u == v)
			return u;

		for (int i = LN - 1; i >= 0; i--) {
			if (pa[u][i] != -1 && pa[u][i] != pa[v][i]) {
				u = pa[u][i];
				v = pa[v][i];
			}
		}
		return pa[u][0];
	}

	static class Query {
		int u, v;

		Query (int u, int v) {
			this.u = u;
			this.v = v;
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