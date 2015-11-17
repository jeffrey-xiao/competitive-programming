package contest.dwite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class DWITE_2002_Money_Prize {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[][] grid = new int[n][n];
		int[][][] dp = new int[n][n][5];
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				grid[x][y] = readInt();
				for (int z = 0; z < 5; z++)
					dp[x][y][z] = -1;
			}
		}
		for (int x = n - 1; x >= 0; x--) {
			for (int y = 0; y < n; y++) {
				if (y == 0 && x == n - 1)
					dp[x][y][0] = grid[x][y];
				else if (y == 0 && x != n - 1)
					dp[x][y][0] = grid[x][y] + dp[x + 1][y][0];
				else if (y != 0 && x == n - 1)
					dp[x][y][0] = grid[x][y] + dp[x][y - 1][0];
				else {
					ArrayList<Integer> possible = new ArrayList<Integer>();
					for (int z = 0; z < 5; z++) {
						if (dp[x][y - 1][z] != -1)
							possible.add(grid[x][y] + dp[x][y - 1][z]);
						if (dp[x + 1][y][z] != -1)
							possible.add(grid[x][y] + dp[x + 1][y][z]);
					}
					Collections.sort(possible, new Comparator<Integer>() {

						@Override
						public int compare (Integer arg0, Integer arg1) {
							return arg1 - arg0;
						}

					});
					for (int z = 0; z < Math.min(possible.size(), 5); z++)
						dp[x][y][z] = possible.get(z);
				}
			}
		}
		for (int z = 0; z < 5; z++) {
			System.out.println(dp[0][n - 1][z]);
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
