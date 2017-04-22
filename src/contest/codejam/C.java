package contest.codejam;

import java.util.*;
import java.io.*;

public class C {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T;
	static int N, Q;
	static Horse[] H;
	static int[][] adj;
	
	static double[][][] dp;
	static long[] suffix;
	static int[] maxDist;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("C-small-attempt2.in"));
		out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();
		
		for (int t = 1; t <= T; t++) {
			N = readInt();
			Q = readInt();
			
			H = new Horse[N];
			
			dp = new double[N][N][1001];
			suffix = new long[N];
			maxDist = new int[N];
			
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					for (int k = 0; k <= 1000; k++)
						dp[i][j][k] = -1;
			
			for (int i = 0; i < N; i++)
				H[i] = new Horse(readInt(), readInt());
			adj = new int[N][N];
			
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					adj[i][j] = readInt();
			for (int i = N - 2; i >= 0; i--)
				suffix[i] = suffix[i + 1] + adj[i][i + 1];
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (suffix[i] - suffix[j] <= H[i].maxDist) {
						maxDist[i] = j;
						break;
					}
				}
			}
			int u = readInt();
			int v = readInt();
			out.printf("Case #%d: %f\n", t, solve(0, maxDist[0], H[0].speed));
		}
		
		out.close();
	}
	
	static double solve (int city, int max, int speed) {
		if (dp[city][max][speed] != -1)
			return dp[city][max][speed];
		if (city == N - 1)
			return 0;
		double ret = 1L << 60;
		if (max > city)
			ret = Math.min(ret, 1.0 * adj[city][city + 1] / speed + solve(city + 1, max, speed));
		if (maxDist[city] > city)
			ret = Math.min(ret, 1.0 * adj[city][city + 1] / H[city].speed + solve(city + 1, maxDist[city], H[city].speed));
		return dp[city][max][speed] = ret;
	}
	
	static class Horse {
		int maxDist, speed;
		Horse (int maxDist, int speed) {
			this.maxDist = maxDist;
			this.speed = speed;
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

