package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class USACO_2015_Cow_Hopscotch_Gold {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final int MOD = 1000000007;
	static int r, c, k;
	static int[][] g;

	@SuppressWarnings ("unchecked")
	public static void main (String[] args) throws IOException {
		r = readInt();
		c = readInt();
		k = readInt();
		int[][] g = new int[r + 1][c + 1];
		HashMap<Integer, Short>[] toCx = new HashMap[k];
		HashMap<Integer, Short>[] toCy = new HashMap[k];
		int[][] prefix = new int[r + 1][c + 1];
		prefix[1][1] = 1;
		for (int i = 0; i < k; i++) {
			toCx[i] = new HashMap<Integer, Short>();
			toCy[i] = new HashMap<Integer, Short>();
		}
		for (int i = 1; i <= r; i++)
			for (int j = 1; j <= c; j++)
				g[i][j] = readInt() - 1;

		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				int a = g[i][j];
				if (!toCx[a].containsKey(i)) {
					toCx[a].put(i, (short) (toCx[a].size() + 1));
				}
			}
		}
		for (int j = 1; j <= c; j++) {
			for (int i = 1; i <= r; i++) {
				int a = g[i][j];
				if (!toCy[a].containsKey(j)) {
					toCy[a].put(j, (short) (toCy[a].size() + 1));
				}
			}
		}

		int[][][] bit = new int[k][][];
		for (int x = 0; x < k; x++)
			bit[x] = new int[toCx[x].size() + 1][toCy[x].size() + 1];
		int sum = 0;
		update(1, 1, bit[g[1][1]], 1);
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if (i == 1 && j == 1)
					continue;
				sum = 0;
				int id = g[i][j];
				int x = toCx[id].get(i);
				int y = toCy[id].get(j);
				sum = (prefix[i - 1][j - 1] - query(x - 1, y - 1, bit[id])) % MOD;
				update(x, y, bit[id], sum);
				prefix[i][j] = (((sum + prefix[i - 1][j]) % MOD + prefix[i][j - 1]) % MOD - prefix[i - 1][j - 1]) % MOD;
			}
		}
		if (sum < 0)
			System.out.println(sum + MOD);
		else
			System.out.println(sum % MOD);
	}

	private static void update (int x, int y, int[][] tree, int v) {
		for (int idx = x; idx < tree.length; idx += (idx & -idx)) {
			for (int idy = y; idy < tree[x].length; idy += (idy & -idy)) {
				tree[idx][idy] = (tree[idx][idy] + v) % MOD;
			}
		}
	}

	private static int query (int x, int y, int[][] tree) {
		int sum = 0;
		if (x == 0 || y == 0)
			return 0;
		for (int idx = x; idx > 0; idx -= (idx & -idx)) {
			for (int idy = y; idy > 0; idy -= (idy & -idy)) {
				sum = (sum + tree[idx][idy]) % MOD;
			}
		}
		return sum;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}