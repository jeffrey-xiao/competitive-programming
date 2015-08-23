package contest.dwite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DWITE_2005_Minesweeper {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] movex = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int[] movey = {-1, -1, -1, 0, 0, 1, 1, 1};
	static char[][] grid = new char[16][];
	static int[][] grid1 = new int[16][30];

	public static void main (String[] args) throws IOException {
		for (int x = 0; x < 16; x++)
			grid[x] = next().toCharArray();
		for (int z = 0; z < 5; z++) {
			int x = readInt() - 1;
			int y = readInt() - 1;
			if (grid[x][y] == 'X')
				System.out.println("MINE - YOU LOSE");
			else {
				int mines = checkSquares(x, y);
				if (mines > 0) {
					System.out.printf("NO MINE - %d SURROUNDING IT\n", mines);
				} else {
					boolean[][] visited = new boolean[16][30];
					int squares = floodFill(x, y, visited);
					System.out.printf("NO MINE - %d SQUARES REVEALED\n",
							squares);
					/*
					 * for(int x1 = 0; x1 < 16; x1++){ for(int y1 = 0;y1< 30;
					 * y1++){ System.out.print(grid1[x1][y1]); }
					 * System.out.println(); }
					 */
				}
			}
		}
	}

	private static int floodFill (int x, int y, boolean[][] v) {
		int count = 1;
		v[x][y] = true;
		grid1[x][y] = 1;
		if (grid[x][y] == 'X')
			return count;

		for (int z = 0; z < 8; z++) {
			int newx = x + movex[z];
			int newy = y + movey[z];
			if (newx < 0 || newy < 0 || newx >= 16 || newy >= 30
					|| v[newx][newy])
				continue;
			if (checkSquares(x, y) == 0)
				count += floodFill(newx, newy, Arrays.copyOf(v, v.length));
		}
		return count;
	}

	private static int checkSquares (int x, int y) {
		int count = 0;
		for (int z = 0; z < 8; z++) {
			int newx = x + movex[z];
			int newy = y + movey[z];
			if (newx < 0 || newy < 0 || newx >= 16 || newy >= 30)
				continue;
			if (grid[newx][newy] == 'X')
				count++;
		}
		return count;
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
