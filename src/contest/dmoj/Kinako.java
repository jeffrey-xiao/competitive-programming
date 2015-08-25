package contest.dmoj;

import java.util.*;
import java.io.*;

public class Kinako {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static char[] in;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static int[][] pa = new int[1001][11];
	static int[] d;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));
		int n = readInt();
		int lk = readInt();
		int rk = readInt();
		int lc = readInt();
		int rc = readInt();
		in = next().toCharArray();
		d = new int[n];
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
			for (int j = 0; j < 11; j++) {
				pa[i][j] = -1;
			}
		}
		for (int i = 0; i < n - 1; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		dfs(0, -1, 0);
		for (int j = 1; j < 11; j++)
			for (int i = 0; i < n; i++)
				if (pa[i][j - 1] != -1)
					pa[i][j] = pa[pa[i][j - 1]][j - 1];
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int lca = LCA(i, j);
				int ci = i;
				int cj = j;
				int sumK = 0;
				int sumC = 0;
				while (ci != lca) {
					if (in[ci] == 'K')
						sumK++;
					else
						sumC++;
					ci = pa[ci][0];
				}
				while (cj != lca) {
					if (in[cj] == 'K')
						sumK++;
					else
						sumC++;
					cj = pa[cj][0];
				}
				if (in[lca] == 'K')
					sumK++;
				else
					sumC++;
				// System.out.println(i+1 + " "+(j+1) + " " + sumK + " " +
				// sumC);
				if (lk <= sumK && sumK <= rk && lc <= sumC && sumC <= rc) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		pr.close();
	}

	static int LCA (int x, int y) {
		if (d[x] < d[y]) {
			int temp = x;
			x = y;
			y = temp;
		}
		for (int i = 10; i >= 0; i--)
			if (pa[x][i] != -1 && d[pa[x][i]] >= d[y])
				x = pa[x][i];

		if (x == y)
			return x;

		for (int i = 10; i >= 0; i--)
			if (pa[x][i] != -1 && pa[x][i] != pa[y][i]) {
				x = pa[x][i];
				y = pa[y][i];
			}

		return pa[x][0];
	}

	private static void dfs (int i, int j, int depth) {
		pa[i][0] = j;
		d[i] = depth;
		for (int k : adj.get(i)) {
			if (k == j)
				continue;
			dfs(k, i, depth + 1);
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
