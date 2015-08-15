package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2010_Stage_2_Computer_Purchase_Return {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<int[]>> p = new ArrayList<ArrayList<int[]>>();// 0
																				// cost,
																				// 1
																				// value
	static int[][] dp;
	static boolean[][] visited;

	public static void main (String[] args) throws IOException {
		int n = nextInt();
		for (int x = 0; x < n; x++)
			p.add(new ArrayList<int[]>());

		int np = nextInt();
		for (int x = 0; x < np; x++) {
			int c = nextInt();
			int v = nextInt();
			int i = nextInt() - 1;
			p.get(i).add(new int[] {c, v});
		}
		int b = nextInt();
		dp = new int[n][b + 1];
		visited = new boolean[n][b + 1];
		int m = compute(0, b, n);
		System.out.println(m);
	}

	private static int compute (int pt, int b, int n) {
		if (pt == n)
			return 0;
		if (visited[pt][b])
			return dp[pt][b];
		int m = 0;
		for (int x = 0; x < p.get(pt).size(); x++) {
			int[] part = p.get(pt).get(x);
			if (part[0] <= b) {
				int next = part[1] + compute(pt + 1, b - part[0], n);
				m = Math.max(next, m);
			}
		}
		visited[pt][b] = true;
		dp[pt][b] = m;
		return m;
	}

	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong () throws IOException {
		return Long.parseLong(next());
	}

	static int nextInt () throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
