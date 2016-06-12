package contest.hackerrank;

import java.util.*;
import java.io.*;

public class Blackrock_D {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static ArrayList<ArrayList<Integer>> adj;
	static long[] val;
	static long[][] dp;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();

		val = new long[1000000];
		dp = new long[1000000][2];
		adj = new ArrayList<ArrayList<Integer>>(1000000);

		Queue<Integer> q = new ArrayDeque<Integer>();
		int curr = -1;
		int cnt = 0;

		String input = br.readLine().trim();
		
		st = new StringTokenizer(input);
		
		N = 0;
		
		for (int i = 0; st.hasMoreTokens(); ) {
			String c = st.nextToken();
			if (!c.equals("#")) {
				adj.add(new ArrayList<Integer>());
				q.offer(i);

				if (curr != -1) {
					adj.get(curr).add(i);
				} else {
					curr = q.poll();
					cnt = -1;
				}
				cnt++;
				val[i] = Long.parseLong(c);
				i++;
			} else {
				cnt++;
			}

			if (cnt == 2) {
				curr = q.poll();
				cnt = 0;
			}
			N = i;
		}
		
//		for (int i = 0; i < N; i++) {
//			out.printf("ON %d %d\n", i, val[i]);
//			for (int j : adj.get(i)) {
//				out.printf("connected to (%d, %d)\n", j, val[j]);
//			}
//		}

		solve(0);
		out.println(Math.max(dp[0][0], dp[0][1]));
		out.close();
	}

	static void solve (int u) {
		for (int v : adj.get(u))
			solve(v);

		long take = val[u];
		long noTake = 0;

		for (int v : adj.get(u)) {
			take = take + dp[v][0];
			noTake = noTake + Math.max(dp[v][0], dp[v][1]);
		}

		dp[u][0] = noTake;
		dp[u][1] = take;
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

