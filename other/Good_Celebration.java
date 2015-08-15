package other;

import java.util.*;
import java.io.*;

public class Good_Celebration {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static long[] b;
	static long[] m;
	static long[][] dp;
	static int N, M;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));
		
		N = readInt();
		M = readInt();
		b = new long[N];
		m = new long[N];
		for (int i = 0; i < N; i++)
			adj.add(new ArrayList<Integer>());
		dp = new long[N][M+1];
		for (int i = 0; i < N; i++) {
			int prev = readInt()-1;
			b[i] = readInt();
			m[i] = readInt();
			if (prev != -1)
				adj.get(prev).add(i);
		}
		solve(0);
		long max = 0;
		for (int i = 0; i <= M; i++) {
			max = Math.max(max, dp[0][i]);
		}
		System.out.println(max);
		pr.close();
	}

	private static void solve (int u) {
		if (adj.get(u).size() == 0) {
			for (int i = 0; i <= M; i++) {
				dp[u][i] = b[u]+m[u]*i;
			}
		} else {
			long[] maxTasty = new long[M+1];
			for (int v = 0; v < adj.get(u).size(); v++) {
				solve(adj.get(u).get(v));
				if (v == 0) {
					for (int j = 0; j <= M; j++) {
						maxTasty[j] = dp[adj.get(u).get(v)][j];
					}
				} else {
					for (int i = M; i >= 0; i--) {
						long max = 0;
						for (int j = 0; j <= M; j++) {
							if (i - j >= 0) {
								max = Math.max(max, Math.min(maxTasty[i-j], dp[adj.get(u).get(v)][j]));
							}
						}
						maxTasty[i] = max;
					}
				}
			}
			for (int i = 0; i <= M; i++) {
//				System.out.println("MAXTASTY " + maxTasty[i]);
				for (int j = 0; j <= i; j++) {
					dp[u][i] = Math.max(dp[u][i], b[u] + m[u]*(j + maxTasty[i-j]));
				}
			}
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

