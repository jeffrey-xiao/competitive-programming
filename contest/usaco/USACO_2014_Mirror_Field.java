package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2014_Mirror_Field {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] dir1 = {1, 0, 3, 2};
	static int[] dir2 = {3, 2, 1, 0};
	static int r;
	static int c;
	static char[][] grid;

	public static void main (String[] args) throws IOException {
		r = readInt();
		c = readInt();
		grid = new char[r][];
		for (int x = 0; x < r; x++)
			grid[x] = next().toCharArray();
		int max = 1;
		for (int x = 0; x < r; x++) {
			max = Math.max(max, getPath(1, x, 0));
			max = Math.max(max, getPath(3, x, c - 1));
		}
		for (int x = 0; x < c; x++) {
			max = Math.max(max, getPath(2, 0, x));
			max = Math.max(max, getPath(0, r - 1, x));
		}
		System.out.println(max);
	}

	private static int getPath (int dir, int x, int y) {
		int num = 0;
		// System.out.println();
		while ((x >= 0 && x < r) && (y >= 0 && y < c)) {
			// System.out.println(dir + " " + x + " " + y);
			if (grid[x][y] == '\\')
				dir = dir2[dir];
			else
				dir = dir1[dir];
			switch (dir) {
				case 0:
					x--;
					break;
				case 1:
					y++;
					break;
				case 2:
					x++;
					break;
				case 3:
					y--;
					break;
			}
			num++;

		}
		return num;

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
