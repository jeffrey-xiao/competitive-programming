package contest.dmoj;

import java.util.*;
import java.io.*;

public class New_Year_DIY_Christmas_Tree {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n;
	static ArrayList<ArrayList<Integer>> adj;
	static ArrayList<Integer> ans;
	static int[] sz;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();

		sz = new int[n];
		adj = new ArrayList<ArrayList<Integer>>();
		ans = new ArrayList<Integer>();

		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Integer>());

		for (int i = 0; i < n; i++) {
			int m = readInt();
			for (int j = 0; j < m; j++) {
				int a = readInt() - 1;
				adj.get(i).add(a);
				adj.get(a).add(i);
			}
		}

		getSizes(0, -1);
		dfs(0, -1, 1, n, 0);

		for (int i : ans)
			out.print(i + " ");
		out.println();

		out.close();
	}

	static int getSizes (int u, int par) {
		sz[u] = 1;
		for (int v : adj.get(u)) {
			if (par == v)
				continue;
			sz[u] += getSizes(v, u);
		}
		return sz[u];
	}

	static void dfs (int u, int par, int min, int max, int prevLabel) {
		ans.add(min);
		for (int v : adj.get(u)) {
			if (par == v)
				continue;
			dfs(v, u, max - sz[v] + 1, max, min);
			max -= sz[v];
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
