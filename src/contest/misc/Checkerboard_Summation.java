package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Checkerboard_Summation {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int r = readInt();
		int c = readInt();
		int[][] grid = new int[r + 1][c + 1];
		long[][] sum = new long[r + 1][c + 1];
		int a = readInt();
		int b = readInt();
		int v = readInt() * ((a + b) % 2 == 0 ? 1 : -1);
		while (a != 0) {
			grid[a][b] = v;
			a = readInt();
			b = readInt();
			v = readInt() * ((a + b) % 2 == 0 ? 1 : -1);
		}
		for (int x = 1; x <= r; x++) {
			for (int y = 1; y <= c; y++) {
				sum[x][y] = grid[x][y] - sum[x - 1][y - 1] + sum[x - 1][y] + sum[x][y - 1];
			}
		}

		int x1 = readInt();
		int y1 = readInt();
		int x2 = readInt();
		int y2 = readInt();
		while (x1 != 0) {
			int k = (x1 + y1) % 2 == 0 ? 1 : -1;
			long ans = sum[x1 - 1][y1 - 1] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x2][y2];
			System.out.println(ans * k);
			x1 = readInt();
			y1 = readInt();
			x2 = readInt();
			y2 = readInt();
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
