package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_World_Line_Convergence {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

	static int[] toIndex;
	static long[] bit;
	static int root;

	static final long MOD = 1000000007;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();

		toIndex = new int[N];
		bit = new long[N + 1];

		for (int i = 0; i < N; i++)
			adj.add(new ArrayList<Integer>());

		for (int i = 0; i < N; i++) {
			int x = readInt() - 1;
			if (x != -1) {
				adj.get(i).add(x);
				adj.get(x).add(i);
			} else {
				root = i;
			}
		}

		for (int i = 0; i < N; i++) {
			toIndex[readInt() - 1] = i + 1;
		}

		dfs(root, -1);

		for (int i = 0; i < N; i++)
			out.print(queryAt(toIndex[i]) + " ");
		out.println();
		out.close();
	}

	static void dfs (int u, int prev) {
		long subtract = query(toIndex[u]);
		for (int v : adj.get(u)) {
			if (v != prev) {
				dfs(v, u);
			}
		}
		update(toIndex[u], query(toIndex[u]) + 1 - subtract);
	}

	static long query (int x) {
		long sum = 0;
		for (int i = x; i > 0; i -= (i & -i))
			sum = (sum + bit[i]) % MOD;
		return sum;
	}

	static void update (int x, long val) {
		for (int i = x; i <= N; i += (i & -i))
			bit[i] = (bit[i] + val) % MOD;
	}

	static long queryAt (int x) {
		return (query(x) - query(x - 1) + MOD) % MOD;
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
