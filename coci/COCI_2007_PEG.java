package coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2007_PEG {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] movex = {0, 0, -1, 1};
	static int[] movey = {1, -1, 0, 0};

	public static void main (String[] args) throws IOException {
		char[][] grid = new char[7][];
		for (int x = 0; x < 7; x++)
			grid[x] = readLine().toCharArray();
		int sum = 0;
		for (int x = 0; x < 7; x++)
			for (int y = 0; y < 7; y++)
				sum += check(grid, x, y);
		System.out.println(sum);
	}

	private static int check (char[][] c, int x, int y) {
		if (c[x][y] != 'o')
			return 0;
		int sum = 0;
		for (int z = 0; z < 4; z++) {
			int nx = x + movex[z] * 2;
			int ny = y + movey[z] * 2;
			if (nx < 0 || nx >= 7 || ny < 0 || ny >= 7)
				continue;
			if (c[nx][ny] == '.' && c[nx - movex[z]][ny - movey[z]] == 'o')
				sum++;
		}
		return sum;
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
		return br.readLine();
	}
}
