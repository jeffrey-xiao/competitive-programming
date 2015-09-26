package contest.hackerrank;

import java.util.*;
import java.io.*;

public class Garden_Planning {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m;
	static int[][] g;
	static final long MOD = 1000000007;
	static ArrayList<HashMap<State, Long>> dp;

	public static void main (String[] args) throws IOException {
		//		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		g = new int[n][m];
		ArrayList<ArrayList<Integer>> indexes = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			String in = next();
			indexes.add(new ArrayList<Integer>());
			for (int j = 0; j < m; j++) {
				if (in.charAt(j) == '.')
					g[i][j] = -1;
				else if (in.charAt(j) == '#')
					g[i][j] = 0;
				else {
					g[i][j] = -2;
					indexes.get(i).add(j);
				}
			}
		}
		dp = new ArrayList<HashMap<State, Long>>();
		dp.add(new HashMap<State, Long>());
		dp.add(new HashMap<State, Long>());

		for (int i = 0; i < n; i++) {
			int sz = indexes.get(i).size();
			dp.get(i % 2).clear();

			out.println(i);
			for (int j = 0; j < (1 << sz); j++) {
				int[] newRow = new int[m];
				for (int k = 0; k < m; k++)
					newRow[k] = g[i][k];
				for (int k = 0; k < sz; k++)
					newRow[indexes.get(i).get(k)] = (j & 1 << k) > 0 ? -1 : 0;
				int id = 1;
				for (int k = 0; k < m; k++) {
					if (newRow[k] == -1)
						newRow[k] = id++;
					if (k + 1 < m && newRow[k] != 0 && newRow[k + 1] == -1)
						newRow[k + 1] = newRow[k];
				}
				if (i == 0) {
					out.println("NEW ROW");
					for (int k = 0; k < m; k++)
						out.print(newRow[k] + " ");
					out.println();
					dp.get(i).put(new State(newRow, true), 1l);
				} else {
					main : for (Map.Entry<State, Long> e : dp.get((i - 1) % 2).entrySet()) {
						State s = e.getKey();
						HashSet<Integer> hs = new HashSet<Integer>();
						for (int k = 0; k < m; k++)
							hs.add(s.row[k]);
						for (int k = 0; k < m; k++) {
							for (int l = k + 1; l < m; l++) {
								if (s.row[k] != 0 && s.row[l] != 0 && newRow[k] != 0 && newRow[l] != 0 && s.row[k] == s.row[l] && newRow[k] == newRow[l])
									continue main;
								if (s.row[k] != 0 && s.row[l] != 0 && s.row[k] != s.row[l] && (newRow[k] == 0 || newRow[l] == 0))
									continue main;

							}
						}

						id = 1;
						for (int k = 0; k < m; k++)
							if (newRow[k] != 0)
								newRow[k] = -1;
						boolean hasCurr = false;
						boolean hasPrev = false;
						for (int k = 0; k < m; k++) {
							if (newRow[k] == -1)
								newRow[k] = id++;
							for (int l = k + 1; l < m; l++) {
								if (newRow[k] != 0 && newRow[l] == -1 && (l == k + 1 || (s.row[k] != 0 && s.row[l] != 0 && s.row[k] == s.row[l])))
									newRow[l] = newRow[k];
							}
							if (!s.canEmpty && newRow[k] != 0)
								continue main;
							if (newRow[k] != 0)
								hasCurr = true;
							if (s.row[k] != 0)
								hasPrev = true;
						}
						if (hs.size() == 2) {
							boolean valid = false;
							for (int k = 0; k < m; k++)
								if (hs.size() == 2 && s.row[k] != 0 && newRow[k] != 0)
									valid = true;
							if (!valid && hasCurr)
								continue main;
						}
						out.println("OLD ROW");
						for (int k = 0; k < m; k++)
							out.print(s.row[k] + " ");
						out.println();
						out.println("NEW ROW");
						for (int k = 0; k < m; k++)
							out.print(newRow[k] + " ");
						out.println();
						boolean canEmpty = false;
						if (hasCurr)
							canEmpty = true;
						else if (!hasCurr && hasPrev)
							canEmpty = false;
						else if (!hasCurr && !hasPrev)
							canEmpty = s.canEmpty;
						out.println(canEmpty);
						add(i % 2, new State(newRow, canEmpty), e.getValue());
					}
				}
			}
		}
		long ans = 0;
		for (Map.Entry<State, Long> e : dp.get((n - 1) % 2).entrySet()) {
			HashSet<Integer> hs = new HashSet<Integer>();
			State s = e.getKey();
			for (int i = 0; i < m; i++)
				hs.add(s.row[i]);
			if (hs.size() <= 2)
				ans = (ans + e.getValue()) % MOD;
		}
		out.println(ans);
		out.close();
	}

	static void add (int i, State s, long cnt) {
		if (!dp.get(i).containsKey(s))
			dp.get(i).put(s, 0l);
		dp.get(i).put(s, (dp.get(i).get(s) + cnt) % MOD);
	}

	static class State {
		int[] row;
		boolean canEmpty;

		State (int[] row, boolean canEmpty) {
			this.row = Arrays.copyOf(row, row.length);
			this.canEmpty = canEmpty;
		}

		public int hashCode () {
			int res = 0;
			for (int i = 0; i < row.length; i++)
				res = (res * 31 + row[i]);
			return res * 31 + (canEmpty ? 1 : 0);
		}

		public boolean equals (Object o) {
			if (o instanceof State) {
				State s = (State) o;
				for (int i = 0; i < row.length; i++)
					if (s.row[i] != row[i])
						return false;
				return canEmpty == s.canEmpty;
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
