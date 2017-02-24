package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2008_J5_S5 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] moves = new int[][] {{2, 1, 0, 2}, {1, 1, 1, 1}, {0, 0, 2, 1}, {0, 3, 0, 0}, {1, 0, 0, 1}};

	public static void main (String[] args) throws IOException {
		for (int testCases = readInt(); testCases > 0; testCases--) {
			int a1 = readInt();
			int b1 = readInt();
			int c1 = readInt();
			int d1 = readInt();
			boolean[][][][] dp = new boolean[a1 + 1][b1 + 1][c1 + 1][d1 + 1];
			for (int a = 0; a <= a1; a++)
				for (int b = 0; b <= b1; b++)
					for (int c = 0; c <= c1; c++)
						for (int d = 0; d <= d1; d++)
							for (int[] x : moves) {
								if (a >= x[0] && b >= x[1] && c >= x[2] && d >= x[3] && !dp[a - x[0]][b - x[1]][c - x[2]][d - x[3]])
									dp[a][b][c][d] = true;
								if (dp[a1][b1][c1][d1])
									break;
							}
			System.out.println(dp[a1][b1][c1][d1] ? "Patrick" : "Roland");
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
