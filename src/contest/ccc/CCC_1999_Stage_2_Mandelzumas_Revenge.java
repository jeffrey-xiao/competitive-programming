package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_1999_Stage_2_Mandelzumas_Revenge {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		char[][] grid = new char[n][];
		for (int x = n - 1; x >= 0; x--)
			grid[x] = next().toCharArray();
		int k = 0;
		while ((k = readInt()) != -1) {
			int b = readInt() - 1;
			int t = readInt() - 1;
			int l = readInt() - 1;
			int r = readInt() - 1;
			for (int i = t; i >= b; i--) {
				for (int j = l; j <= r; j++) {
					if (j != l)
						System.out.print(' ');
					int y = i, x = j;
					char next = '*';
					for (int z = k; z > 0; z--) {
						int ny = (int) (y / Math.pow(n, z - 1));
						int nx = (int) (x / Math.pow(n, z - 1));
						if (grid[ny][nx] == '.') {
							next = ' ';
							break;
						} else if (grid[ny][nx] == '!') {
							next = '*';
							break;
						} else if (grid[ny][nx] == '?')
							next = '*';
						y %= Math.pow(n, z - 1);
						x %= Math.pow(n, z - 1);
					}
					System.out.print(next);
				}
				System.out.println();
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