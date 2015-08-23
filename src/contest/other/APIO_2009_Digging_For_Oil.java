package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class APIO_2009_Digging_For_Oil {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[][][][][] dp;
	static int[][] g;
	static int r, c, k;

	public static void main (String[] args) throws IOException {
		r = readInt();
		c = readInt();
		k = readInt();
		dp = new int[r + 1][c + 1][r + 1][c + 1][4];
		g = new int[r + 1][c + 1];
		for (int x = 1; x <= r; x++)
			for (int y = 1; y <= c; y++) {
				g[x][y] = readInt() + g[x - 1][y] + g[x][y - 1]
						- g[x - 1][y - 1];
				for (int z = 1; z <= 3; z++)
					for (int x1 = 0; x1 <= r; x1++)
						for (int y1 = 0; y1 <= c; y1++)
							dp[x][y][x1][y1][z] = -1;
			}
		System.out.println(compute(r, c, 0, 0, 3));
		// for(int x = 1; x <= r; x++)
		// for(int y = 1; y <= c; y++)
		// for(int x1 = 0; x1 <= r; x1++)
		// for(int y1 = 0; y1 <= c; y1++)
		// for(int z = 1; z <= 3; z++)
		// System.out.printf("%d %d %d %d %d %d\n",x,y,x1,y1,z,dp[x][y][x1][y1][z]);
	}

	private static int compute (int x2, int y2, int x, int y, int d) {
		int x1 = x2 - k;
		int y1 = y2 - k;
		if (x1 < x || y1 < y)
			return 0;
		if (d == 0)
			return 0;
		if (dp[x2][y2][x][y][d] != -1)
			return dp[x2][y2][x][y][d];
		int sum = g[x2][y2] - g[x1][y2] - g[x2][y1] + g[x1][y1];
		int a = 0;
		int b = 0;
		for (int z = 0; z <= d - 1; z++) {
			a = Math.max(
					compute(x1, y2, x, y, d - 1 - z)
							+ compute(x2, y1, x1, y, z), a);
			b = Math.max(
					compute(x2, y1, x, y, d - 1 - z)
							+ compute(x1, y2, x, y1, z), b);
		}
		// if(x2 == r && y2 == c && x == 0 && y == 0){
		// System.out.println(a + " " + b + " " + sum);
		// }
		int take = sum + Math.max(a, b);
		a = 0;
		b = 0;
		x1 = x2 - 1;
		y1 = y2 - 1;
		for (int z = 0; z <= d; z++) {
			a = Math.max(
					compute(x1, y2, x, y, d - z) + compute(x2, y1, x1, y, z), a);
			b = Math.max(
					compute(x2, y1, x, y, d - z) + compute(x1, y2, x, y1, z), b);
		}
		int noTake = Math.max(a, b);
		dp[x2][y2][x][y][d] = Math.max(take, noTake);
		// if(x2 == r && y2 == c && x == 0 && y == 0){
		// System.out.println(a + " " + b);
		// }
		return dp[x2][y2][x][y][d];
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
