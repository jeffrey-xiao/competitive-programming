package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class USACO_2012_Bovine_Alliance {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static boolean[] v;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static long comp = 0;
	static final int MOD = 1000000007;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		for (int x = 0; x < n; x++)
			adj.add(new ArrayList<Integer>());
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		v = new boolean[n];
		long total = 1;
		for (int x = 0; x < n; x++) {
			if (!v[x]) {
				comp = 0;
				long count = dfs(x) / 2;
				if (comp == count)
					total *= 2;
				else if (comp - 1 == count)
					total *= comp;
				total %= MOD;
			}
		}
		System.out.println(total % MOD);
	}

	private static long dfs (int x) {
		comp++;
		v[x] = true;
		int total = 0;
		for (Integer i : adj.get(x)) {
			if (!v[i])
				total += dfs(i);
		}
		return adj.get(x).size() + total;
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
