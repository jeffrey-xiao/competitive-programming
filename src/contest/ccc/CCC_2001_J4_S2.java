package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2001_J4_S2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] movex = {-1, 0, 1, 0};
	static int[] movey = {0, 1, 0, -1};

	public static void main (String[] args) throws IOException {
		int[][] grid = new int[10][10];
		int start = readInt();
		int end = readInt();
		int x = 4;
		int y = 4;
		int count = start;
		int dir = 2;
		grid[x][y] = count;
		count++;
		for (int z = 1; count <= end; z++) {
			for (int t = 0; t < 2 && count <= end; t++) {
				for (int a = 1; a <= z && count <= end; a++) {
					x += movex[dir];
					y += movey[dir];
					grid[x][y] = count;
					count++;
				}
				dir = (dir + 3) % 4;
			}
		}
		for (int x1 = 0; x1 < 10; x1++) {
			for (int y1 = 0; y1 < 10; y1++) {
				if (grid[x1][y1] != 0)
					System.out.print(grid[x1][y1] + " ");
			}
			System.out.println();
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
