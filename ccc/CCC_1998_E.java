package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_1998_E {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int n;
	static int[][] dp;
	static int[] movex = {0, 0, -1, 1};
	static int[] movey = {-1, 1, 0, 0};

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			n = readInt();
			int[][] grid = new int[n][n];
			dp = new int[n][n];
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					grid[x][y] = readInt();
					dp[x][y] = 1000000;
				}
			}
			int start = grid[0][0];
			boolean[][] visited = new boolean[n][n];
			compute(start, 0, 0, grid, visited);
			/*
			 * for(int x = 0; x < n; x++){ for(int y = 0; y < n; y++){
			 * System.out.printf("%3d ", dp[x][y]>=1000000?-1:dp[x][y]); }
			 * System.out.println(); }
			 */
			/*
			 * System.out.println(); for(int x = 0; x < n; x++){ for(int y = 0;
			 * y < n; y++){ System.out.printf("%3d ",
			 * dp[x][y]>=1000000?-1:grid[x][y]); } System.out.println(); }
			 */
			System.out.println();
			System.out.println(dp[0][0] == 1000000 ? "CANNOT MAKE THE TRIP"
					: dp[0][0]);
		}
	}

	private static int compute (int s, int x, int y, int[][] g, boolean[][] v) {
		v[x][y] = true;
		// System.out.println(x + " " + y + " " + o);
		if (x == n - 1 && y == n - 1) {
			return 0;
		}
		int min = 1000000;
		for (int z = 0; z < 4; z++) {
			int newx = movex[z] + x;
			int newy = movey[z] + y;
			if (newx < 0 || newy < 0 || newx >= n || newy >= n || v[newx][newy]
					|| Math.abs(g[newx][newy] - g[x][y]) > 2)
				continue;
			int extraOxygen = g[newx][newy] > s || g[x][y] > s ? 1 : 0;
			if (dp[newx][newy] == 1000000
					|| (dp[x][y] - extraOxygen < dp[newx][newy] && dp[newx][newy] < 1000000)) {
				dp[newx][newy] = compute(s, newx, newy, g, cloneArray(v))
						+ extraOxygen;
			}
			// System.out.printf("X: %d Y: %d VALUE: %d\n",newx,newy,
			// dp[newx][newy]);
			min = Math.min(min, dp[newx][newy]);
		}
		dp[x][y] = Math.min(dp[x][y], min);
		return dp[x][y] >= 1000000 ? 1000001 : dp[x][y];
	}

	public static boolean[][] cloneArray (boolean[][] v) {
		int length = v.length;
		boolean[][] target = new boolean[length][v[0].length];
		for (int i = 0; i < length; i++) {
			System.arraycopy(v[i], 0, target[i], 0, v[i].length);
		}
		return target;
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