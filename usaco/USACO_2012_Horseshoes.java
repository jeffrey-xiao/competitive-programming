package usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2012_Horseshoes {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] movex = {0, 0, -1, 1};
	static int[] movey = {-1, 1, 0, 0};
	static int max = 0;
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		char[][] grid = new char[n][];
		for (int x = 0; x < n; x++)
			grid[x] = next().toCharArray();
		boolean[][] visited = new boolean[n][n];
		if (grid[0][0] == ')')
			System.out.println("0");
		else {
			compute(0, 0, visited, grid, 1, 0, true);
			System.out.println(max);
		}
	}

	private static void compute (int x, int y, boolean[][] v, char[][] g,
			int open, int close, boolean onOpen) {
		if (open == close)
			max = Math.max(open * 2, max);

		v[x][y] = true;
		for (int z = 0; z < 4; z++) {
			int nextx = movex[z] + x;
			int nexty = movey[z] + y;

			if (nextx < 0 || nexty < 0 || nextx >= n || nexty >= n
					|| v[nextx][nexty])
				continue;
			// System.out.println(x + " " + y + " " + nextx + " " + nexty);
			if (g[nextx][nexty] == '(' && !onOpen)
				continue;
			else if (g[nextx][nexty] == '(' && onOpen) {
				// System.out.println("ASDASDAS");
				compute(nextx, nexty, v, g, open + 1, close, true);
			} else if (g[nextx][nexty] == ')')
				compute(nextx, nexty, v, g, open, close + 1, false);
		}
		v[x][y] = false;
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
