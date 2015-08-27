package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class USACO_2015_Cow_Routing_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int a = readInt();
		int b = readInt();
		int n = readInt();
		int min = Integer.MAX_VALUE;
		int[] dp = new int[10001];
		for (int x = 0; x <= 10000; x++)
			dp[x] = Integer.MAX_VALUE;
		dp[a] = 0;
		int[] costs = new int[n];
		ArrayList<ArrayList<Integer>> roads = new ArrayList<ArrayList<Integer>>();
		for (int x = 0; x < n; x++) {
			costs[x] = readInt();
			int num = readInt();
			roads.add(new ArrayList<Integer>());
			for (int y = 0; y < num; y++)
				roads.get(x).add(readInt());
		}
		for (int x = 0; x < n; x++) {
			boolean isReach = false;
			for (Integer y : roads.get(x)) {
				if (y == a)
					isReach = true;
				else if (isReach)
					dp[y] = Math.min(dp[y], costs[x]);
			}
		}
		for (int x = 0; x < n; x++) {
			int localMin = 1 << 30;
			for (Integer y : roads.get(x)) {
				localMin = Math.min(localMin, dp[y]);
				if (y == b) {
					min = Math.min(costs[x] + localMin, min);
				}
			}
		}
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
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
