package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2006_MAGIJA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int r = readInt();
		int c = readInt();
		char[][] grid = new char[r * 2][c * 2];
		for (int x = 0; x < r; x++) {
			String n = readLine();
			for (int y = 0; y < c; y++) {
				grid[x][y] = n.charAt(y);
				grid[2 * r - 1 - x][y] = n.charAt(y);
				grid[x][2 * c - 1 - y] = n.charAt(y);
				grid[2 * r - 1 - x][2 * c - 1 - y] = n.charAt(y);
			}
		}
		int a = readInt() - 1;
		int b = readInt() - 1;
		grid[a][b] = grid[a][b] == '#' ? '.' : '#';
		for (int x = 0; x < 2 * r; x++) {
			for (int y = 0; y < 2 * c; y++)
				System.out.print(grid[x][y]);
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
