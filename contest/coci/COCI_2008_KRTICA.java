package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2008_KRTICA {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static int n;
	static final int SIZE = 300001;
	static int[] dist1 = new int[SIZE], dist2 = new int[SIZE], dist3 = new int[SIZE], a = new int[SIZE], b = new int[SIZE], index1 = new int[SIZE], index2 = new int[SIZE];
	static int[] up = new int[SIZE], down = new int[SIZE], par = new int[SIZE];
	static int[] topBranch = new int[SIZE];
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));
		for (int i = 0; i < SIZE; i++) {
			adj.add(new ArrayList<Integer>());
			dist1[i] = dist2[i] = dist3[i] = -1 << 30;
		}
		n = readInt();
		for (int i = 0; i < n - 1; i++) {
			a[i] = readInt() - 1;
			b[i] = readInt() - 1;
			adj.get(a[i]).add(b[i]);
			adj.get(b[i]).add(a[i]);
		}
		dfs(0, -1);
		solve(0, -1, 0);
		int ans = 1 << 30;
		for (int i = 0; i < n; i++) {
			// System.out.println(i + " " + up[i] + " " + down[i]);
			dist2[i] = Math.max(0, dist2[i]);
			dist3[i] = Math.max(0, dist3[i]);
		}
		for (int i = 0; i < n - 1; i++) {
			int d1 = 0;
			int d2 = 0;
			if (par[b[i]] == a[i]) {
				d1 = down[b[i]];
				if (index1[a[i]] == b[i]) {
					d2 = Math.max(up[a[i]], dist2[a[i]] + dist3[a[i]]);
					d2 = Math.max(d2, topBranch[a[i]] + dist2[a[i]]);
				} else if (index2[a[i]] == b[i]) {
					d2 = Math.max(up[a[i]], dist1[a[i]] + dist3[a[i]]);
					d2 = Math.max(d2, topBranch[a[i]] + dist1[a[i]]);
				} else {
					d2 = Math.max(up[a[i]], dist1[a[i]] + dist2[a[i]]);
					d2 = Math.max(d2, topBranch[a[i]] + dist1[a[i]]);
				}
			} else {
				d1 = down[a[i]];
				if (index1[b[i]] == a[i]) {
					d2 = Math.max(up[b[i]], dist2[b[i]] + dist3[b[i]]);
					d2 = Math.max(d2, topBranch[b[i]] + dist2[b[i]]);
				} else if (index2[b[i]] == a[i]) {
					d2 = Math.max(up[b[i]], dist1[b[i]] + dist3[b[i]]);
					d2 = Math.max(d2, topBranch[b[i]] + dist1[b[i]]);
				} else {
					d2 = Math.max(up[b[i]], dist1[b[i]] + dist2[b[i]]);
					d2 = Math.max(d2, topBranch[b[i]] + dist1[b[i]]);
				}
			}
			ans = Math.min(ans, Math.max((d1 + 1) / 2 + 1 + (d2 + 1) / 2, Math.max(d1, d2)));
			// System.out.println(ans + " " + (a[i]+1) + " " + (b[i]+1) + " " +
			// up[b[i]]);
		}
		ans = Math.min(ans, dist1[0] + dist2[0]);
		System.out.println(ans);
		pr.close();
	}

	private static void solve (int i, int prev, int p) {
		topBranch[i] = p;
		if (i != 0) {
			down[i] = dist1[i] + Math.max(0, dist2[i]);

			// case 1 : does not include i, but includes parent
			if (index1[par[i]] == i) {
				up[i] = Math.max(0, dist2[par[i]]) + Math.max(0, dist3[par[i]]);
				up[i] = Math.max(up[i], topBranch[par[i]] + dist2[par[i]]);
			} else if (index2[par[i]] == i) {
				up[i] = Math.max(0, dist1[par[i]]) + Math.max(0, dist3[par[i]]);
				up[i] = Math.max(up[i], topBranch[par[i]] + dist1[par[i]]);
			} else {
				up[i] = Math.max(0, dist1[par[i]]) + Math.max(0, dist2[par[i]]);
				up[i] = Math.max(up[i], topBranch[par[i]] + dist1[par[i]]);
			}
			// case 2 : does not include par
			up[i] = Math.max(up[i], up[par[i]]);

		}

		for (int j : adj.get(i)) {
			if (j == prev)
				continue;
			if (index1[i] == j)
				solve(j, i, Math.max(p, dist2[i]) + 1);
			else
				solve(j, i, Math.max(p, dist1[i]) + 1);
		}
	}

	static void dfs (int i, int prev) {
		par[i] = prev;
		boolean hasNext = false;
		for (int j : adj.get(i)) {
			if (j == prev)
				continue;
			dfs(j, i);
			hasNext = true;
			if (dist1[j] + 1 >= dist1[i]) {
				dist3[i] = dist2[i];
				dist2[i] = dist1[i];
				dist1[i] = dist1[j] + 1;
				index2[i] = index1[i];
				index1[i] = j;
			} else if (dist1[j] + 1 >= dist2[i]) {
				dist3[i] = dist2[i];
				dist2[i] = dist1[j] + 1;
				index2[i] = j;
			} else if (dist1[j] + 1 > dist3[i]) {
				dist3[i] = dist1[j] + 1;
			}

		}
		if (!hasNext)
			dist1[i] = 0;
		// System.out.println("HERE " + (i+1) + " " + dist1[i] + " " + dist2[i]
		// + " " + dist3[i]);
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
