package coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2006_MARATON {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] movex = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] movey = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int n;
	static char[][] g;

	public static void main (String[] args) throws IOException {
		n = readInt();
		g = new char[n][n];
		for (int x = 0; x < n; x++)
			g[x] = next().toCharArray();
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (g[x][y] != '.') {
					for (int z = 0; z < 4; z++) {
						if (check(x + movex[z], y + movey[z], movex[z],
								movey[z], 1, g[x][y])) {
							System.out.println(g[x][y]);
							return;
						}
					}
				}
			}
		}
		System.out.println("ongoing");
	}

	private static boolean check (int x, int y, int dx, int dy, int i, char c) {
		if (x < 0 || x >= n || y < 0 || y >= n || g[x][y] != c)
			return false;
		if (i == 0)
			return true;
		return check(x + dx, y + dy, dx, dy, i - 1, c);
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
