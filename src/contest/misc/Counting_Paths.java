package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Counting_Paths {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int k = readInt();
		int[][] dp = new int[n][k + 1];
		for (int x = 0; x < n; x++)
			adj.add(new ArrayList<Integer>());

		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (readInt() == 1) {
					adj.get(x).add(y);
				}
			}
		}
		for (int c = 0; c < k; c++) {
			for (int x = 0; x < n; x++) {
				for (Integer i : adj.get(x)) {
					if (c == 0)
						dp[i][c + 1]++;
					else if (dp[x][c] != 0)
						dp[i][c + 1] += dp[x][c];
				}
			}
		}
		int total = 0;
		for (int x = 0; x < n; x++)
			total += dp[x][k];
		System.out.println(total);
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
