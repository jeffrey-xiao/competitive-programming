package contest.acm;

import java.util.*;
import java.io.*;

public class ACM_Office_Mates {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T, N, M;
	static ArrayList<ArrayList<Integer>> adj;
	static int[][] dp;
	static boolean[] vis;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();
		
		for (int t = 0; t < T; t++) {
			N = readInt();
			M = readInt();
			
			vis = new boolean[N];
			dp = new int[N][2];
			adj = new ArrayList<ArrayList<Integer>>();
			
			for (int i = 0; i < N; i++)
				adj.add(new ArrayList<Integer>());
				
			for (int i = 0; i < M; i++) {
				int u = readInt() - 1;
				int v = readInt() - 1;
				adj.get(u).add(v);
				adj.get(v).add(u);
			}
			
			int ans = 0;
			boolean first = true;
			for (int i = 0; i < N; i++) {
				if (!vis[i]) {
					solve(i, -1);
					ans += Math.min(dp[i][0], dp[i][1]);
					if (!first)
						ans++;
					first = false;
				}
			}
			out.println(ans + N);
		}
		
		out.close();
	}
	
	static void solve (int u, int p) {
		vis[u] = true;
		
		for (int v : adj.get(u))
			if (v != p)
				solve(v, u);
		
		int ans1 = 0; // one connection
		int ans2 = 0; // two connections
		PriorityQueue<Integer> choices = new PriorityQueue<Integer>();
		for (int v : adj.get(u)) {
			if (v != p) {
				ans1 += dp[v][1] + 1;
				ans2 += dp[v][1] + 1;
				choices.offer(dp[v][0] - dp[v][1]);
			}
		}
		
		if (!choices.isEmpty()) {
			ans1 += choices.peek() - 1;
			ans2 += choices.peek() - 1;
			choices.poll();
		}

		if (!choices.isEmpty()) {
			ans2 += choices.peek() - 1;
			choices.poll();
		}
	
		dp[u][0] = ans1;
		dp[u][1] = ans2;
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

