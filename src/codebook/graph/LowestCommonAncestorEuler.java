package codebook.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LowestCommonAncestorEuler {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[] toId, toLabel, first;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static int n, q, cnt, sz, num;
	static int[] order;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		// number of nodes
		n = readInt();
		// number of queries
		q = readInt();
		sz = 2 * n - 1;

		toId = new int[n];
		toLabel = new int[n];
		order = new int[2 * sz];
		first = new int[n];
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
			first[i] = -1;
		}

		for (int i = 0; i < n - 1; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}

		dfs(0, -1);

		for (int i = 2 * sz - 2; i > 1; i -= 2)
			order[i >> 1] = Math.min(order[i], order[i ^ 1]);

		for (int i = 0; i < q; i++) {
			out.println(lca(readInt() - 1, readInt() - 1) + 1);
		}

		out.close();
	}

	private static int lca (int i, int j) {
		int a = toId[i];
		int b = toId[j];
		int lo = Math.min(first[a], first[b]);
		int hi = Math.max(first[a], first[b]);
		int res = 1 << 30;
		for (lo += sz, hi += sz; lo <= hi; lo = (lo + 1) >> 1, hi = (hi - 1) >> 1)
			res = Math.min(res, Math.min(order[lo], order[hi]));
		return toLabel[res];
	}

	private static void dfs (int i, int prev) {
		int curr = num++;
		toId[i] = curr;
		toLabel[curr] = i;
		for (int j : adj.get(i)) {
			if (j != prev) {
				order[sz + cnt++] = curr;
				if (first[curr] == -1)
					first[curr] = cnt - 1;
				dfs(j, i);
			}
		}
		order[sz + cnt++] = curr;
		if (first[curr] == -1)
			first[curr] = cnt - 1;
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
