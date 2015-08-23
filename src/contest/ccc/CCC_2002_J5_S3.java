package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2002_J5_S3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int r;
	static int c;
	static char[] com;
	static char[][] grid;
	static int[] movex = {-1, 0, 1, 0};
	static int[] movey = {0, 1, 0, -1};

	public static void main (String[] args) throws IOException {
		r = readInt();
		c = readInt();
		grid = new char[r][];
		for (int x = 0; x < r; x++)
			grid[x] = next().toCharArray();

		int commands = readInt();
		com = new char[commands];
		for (int x = 0; x < commands; x++)
			com[x] = next().charAt(0);
		for (int x = 0; x < r; x++) {
			for (int y = 0; y < c; y++) {
				for (int z = 0; z < 4; z++) {
					if (grid[x][y] != 'X' && check(x, y, z)) {
						grid[x][y] = '*';
						break;
					}
				}
				System.out.print(grid[x][y]);
			}
			System.out.println();
		}
	}

	private static boolean check (int x, int y, int z) {
		for (int m = com.length - 1; m >= 0; m--) {
			char curr = com[m];
			if (curr == 'R')
				z = (z + 3) % 4;
			else if (curr == 'L')
				z = (z + 1) % 4;
			else {
				x -= movex[z];
				y -= movey[z];
				if (x < 0 || y < 0 || x >= r || y >= c || grid[x][y] == 'X')
					return false;
			}
		}
		return true;
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
