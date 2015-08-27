package contest.woburn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Woburn_Challenge_2002_Dark_Lord {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int a = readInt();
		int[][][] grid = new int[a + 1][a + 1][a + 1];
		int max = 0;
		int input = readInt();
		for (int x = 1; input != -1; x++) {
			for (int y = 1; y <= a; y++) {
				for (int z = 1; z <= a; z++) {

					grid[x][y][z] = input + grid[x - 1][y][z] + grid[x][y - 1][z] + grid[x][y][z - 1] - grid[x - 1][y - 1][z] - grid[x - 1][y][z - 1] - grid[x][y - 1][z - 1] + grid[x - 1][y - 1][z - 1];
					System.out.print(grid[x][y][z] + " ");
					input = readInt();
				}
				System.out.println();
			}
			System.out.println();
			if (x < a)
				continue;
			int x1 = x;
			int y1 = a;
			int z1 = a;
			int x2 = x - 3;
			int y2 = 0;
			int z2 = 0;
			max = Math.max(max, -(grid[x2][y2][z2] - grid[x1][y2][z2] - grid[x2][y1][z2] - grid[x2][y2][z1] + grid[x1][y1][z2] + grid[x2][y1][z1] + grid[x1][y2][z1] - grid[x1][y1][z1]));
		}
		System.out.println(max);
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
