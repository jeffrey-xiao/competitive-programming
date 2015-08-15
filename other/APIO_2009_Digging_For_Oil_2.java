package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class APIO_2009_Digging_For_Oil_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int[][][][] dp;
	static int k;
	static int[][] g;

	public static void main (String[] args) throws IOException {
		int r = readInt();
		int c = readInt();
		k = readInt();
		dp = new int[r + 1][c + 1][r + 1][c + 1];
		g = new int[r + 1][c + 1];
		for (int x = 1; x <= r; x++)
			for (int y = 1; y <= c; y++)
				g[x][y] = readInt() + g[x - 1][y] + g[x][y - 1]
						- g[x - 1][y - 1];
		int max = 0;
		for (int x = k; x <= r - k + 1; x++) {
			for (int y = k; y <= c - k + 1; y++) {
				int TL = compute(0, 0, x, y);
				int BL = compute(x, 0, r, y);
				int TR = compute(0, y, x, c);
				int BR = compute(x, y, r, c);

				int top = compute(0, 0, x, c);
				int left = compute(0, 0, r, y);
				int right = compute(0, y, r, c);
				int bottom = compute(x, 0, r, c);
				max = Math.max(max, top + BL + BR);
				max = Math.max(max, left + TR + BR);
				max = Math.max(max, bottom + TL + TR);
				max = Math.max(max, right + TL + BL);
			}
		}
		for (int x = k; x <= r - k + 1; x++) {
			for (int y = x + k; y <= r - k + 1; y++) {
				int left = compute(0, 0, x, c);
				int middle = compute(x, 0, y, c);
				int right = compute(y, 0, r, c);
				max = Math.max(max, left + middle + right);
			}
		}
		for (int x = k; x <= c - k + 1; x++) {
			for (int y = x + k; y <= c - k + 1; y++) {
				int left = compute(0, 0, r, x);
				int middle = compute(0, x, r, y);
				int right = compute(0, y, r, c);
				max = Math.max(max, left + middle + right);
			}
		}
		System.out.println(max);
	}

	static int compute (int x1, int y1, int x2, int y2) {
		if (dp[x1][y1][x2][y2] != 0)
			return dp[x1][y1][x2][y2];
		if (x2 - x1 < k || y2 - y1 < k)
			return 0;
		int max = g[x2][y2] - g[x2 - k][y2] - g[x2][y2 - k] + g[x2 - k][y2 - k];
		// System.out.printf("%d %d %d %d VALUE: %d and %d\n",x1,y2,x2,y2,max,g[x2][y2]);
		max = Math.max(max, compute(x1, y1, x2 - 1, y2));
		max = Math.max(max, compute(x1, y1, x2, y2 - 1));
		dp[x1][y1][x2][y2] = max;
		return max;
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
