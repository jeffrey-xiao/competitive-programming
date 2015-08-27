package contest.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOI_2009_Money_Boxen {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static ArrayList<ArrayList<Integer>> adj;
	static ArrayList<ArrayList<Integer>> rev;

	public static void main (String[] args) throws IOException {
		for (int t = 0; t < 2; t++) {
			int n = readInt();
			adj = new ArrayList<ArrayList<Integer>>();
			rev = new ArrayList<ArrayList<Integer>>();
			for (int x = 0; x < n; x++) {
				adj.add(new ArrayList<Integer>());
				rev.add(new ArrayList<Integer>());
			}
			for (int x = 0; x < n; x++) {
				int a = readInt() - 1;
				if (a == x)
					continue;
				adj.get(a).add(x);
				rev.get(x).add(a);
			}
			boolean[] v = new boolean[n];
			int count = 0;
			for (int x = 0; x < n; x++) {
				if (!v[x]) {
					System.out.println(x);
					dfs(x, v);
					count++;
				}
			}
			// System.out.print(count + " ");
		}
	}

	private static void dfs (Integer i, boolean[] v) {
		v[i] = true;
		for (Integer parent : rev.get(i)) {
			if (!v[parent]) {
				dfs(parent, v);
			}
			break;
		}
		for (Integer child : adj.get(i)) {
			if (!v[child])
				dfs(child, v);
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
