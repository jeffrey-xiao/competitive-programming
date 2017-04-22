package contest.codejam;

import java.util.*;
import java.io.*;

public class GCJ_2016_Round_2_D {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T, N;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] vis;
	static int ans, edges, min;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();

		for (int t = 1; t <= T; t++) {
			int N = readInt();

			ans = 0;
			edges = 0;

			adj = new ArrayList<ArrayList<Integer>>(2 * N);
			vis = new boolean[2 * N];

			for (int i = 0; i < 2 * N; i++)
				adj.add(new ArrayList<Integer>());

			for (int i = 0; i < N; i++) {
				String in = readLine();
				for (int j = 0; j < N; j++) {
					if (in.charAt(j) == '1') {
						adj.get(i * 2).add(j * 2 + 1);
						adj.get(j * 2 + 1).add(i * 2);
						edges++;
					}
				}
			}

			ArrayList<State> s = new ArrayList<State>();

			for (int i = 0; i < 2 * N; i++)
				if (!vis[i]) {
					State next = dfs(i);
					ans += next.a * next.b;
					if (next.a != next.b) {
						s.add(next);
					}
				}

			if (s.isEmpty()) {
				out.printf("Case #%d: %d\n", t, ans - edges);

			} else {
				Collections.sort(s);

				ArrayList<Integer> occ = new ArrayList<Integer>();
				ArrayList<State> states = new ArrayList<State>();

				occ.add(1);
				states.add(s.get(0));

				for (int i = 1; i < s.size(); i++) {
					if (s.get(i).equals(s.get(i - 1)))
						occ.set(occ.size() - 1, occ.get(occ.size() - 1) + 1);
					else {
						occ.add(1);
						states.add(s.get(i));
					}
				}

				int maxStates = 1;

				for (int sz : occ)
					maxStates *= (sz + 1);

				int[][] dp = new int[N + 1][maxStates];
				int[][] szA = new int[N + 1][maxStates];
				int[][] szB = new int[N + 1][maxStates];

				for (int i = 0; i <= N; i++)
					for (int j = 0; j < maxStates; j++)
						dp[i][j] = 1 << 30;

				dp[0][0] = 0;

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < maxStates; j++) {
						if (dp[i][j] == 1 << 30)
							continue;
						int[] sizes = new int[occ.size()];
						int curr = j;
						int pow = 1;
						for (int k = occ.size() - 1; k >= 0; k--) {
							sizes[k] = curr % (occ.get(k) + 1);
							curr /= (occ.get(k) + 1);
							if (sizes[k] < occ.get(k)) {
								int newState = j + pow;
								int newSzA = szA[i][j] + states.get(k).a;
								int newSzB = szB[i][j] + states.get(k).b;
								int newCost = dp[i][j] + szA[i][j] * states.get(k).b + szB[i][j] * states.get(k).a;

								if (newSzA == newSzB) {
									if (newCost < dp[i + 1][newState]) {
										dp[i + 1][newState] = newCost;
										szA[i + 1][newState] = 0;
										szB[i + 1][newState] = 0;
									}
								} else {
									if (newCost < dp[i][newState]) {
										dp[i][newState] = newCost;
										szA[i][newState] = newSzA;
										szB[i][newState] = newSzB;
									}
								}
							}
							pow *= (occ.get(k) + 1);
						}
					}
				}

				min = 1 << 30;

				for (int i = 0; i <= N; i++)
					min = Math.min(min, dp[i][maxStates - 1]);

				out.printf("Case #%d: %d\n", t, ans + min - edges);
			}
		}

		out.close();
	}

	static State dfs (int u) {
		vis[u] = true;

		State ret = new State(0, 0);

		if (u % 2 == 0)
			ret.a++;
		else
			ret.b++;

		for (int v : adj.get(u)) {
			if (!vis[v]) {
				State res = dfs(v);
				ret.a += res.a;
				ret.b += res.b;
			}
		}
		return ret;
	}

	static class State implements Comparable<State> {
		int a, b;

		State (int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo (State s) {
			if (a != s.a)
				return a - s.a;
			return b - s.a;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof State) {
				State s = (State)o;
				return a == s.a && b == s.b;
			}
			return false;
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
