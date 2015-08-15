package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2008_Stage_2_Candy_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int numOfCandies = readInt();
		ArrayList<Integer> candy = new ArrayList<Integer>();
		for (int x = 0; x < numOfCandies; x++) {
			int k = readInt();
			int c = readInt();
			for (int y = 0; y < k; y++)
				candy.add(c);
		}
		int[][] dp = new int[2][600];
		for (int x = 0; x < candy.size(); x++) {
			for (int y = 0; y < 600; y++) {
				int a = y + candy.get(x);
				int b = Math.abs(y - candy.get(x));

				if (x == 0)
					dp[x % 2][y] = Math.min(a, b);
				else {
					int prevDiffA = a >= 600 ? Integer.MAX_VALUE
							: dp[(x - 1) % 2][a];
					int prevDiffB = dp[(x - 1) % 2][b];
					dp[x % 2][y] = Math.min(prevDiffA, prevDiffB);
				}
			}
		}
		System.out.println(dp[(candy.size() - 1) % 2][0]);
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
