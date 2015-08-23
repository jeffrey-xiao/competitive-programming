package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class CCC_2006_S4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static PrintStream ps = new PrintStream(System.out);

	public static void main (String[] args) throws IOException {
		int n = -1;
		while ((n = readInt()) != 0) {
			int[][] g = new int[n + 1][n + 1];
			for (int x = 1; x <= n; x++)
				for (int y = 1; y <= n; y++)
					g[x][y] = readInt();
			boolean valid = true;
			for (int x = 1; x <= n; x++) {
				for (int y = 1; y <= n; y++) {
					for (int z = 1; z <= n; z++) {
						if (g[g[x][y]][z] != g[x][g[y][z]])
							valid = false;
					}
				}
			}
			if (!valid) {
				System.out.println("no");
				continue;
			}
			int i = 0;
			valid = false;
			for (int x = 1; x <= n; x++) {
				valid = true;
				for (int y = 1; y <= n && valid; y++) {
					if (g[x][y] != y)
						valid = false;
				}
				if (valid) {
					i = x;
					break;
				}
			}
			if (!valid) {
				System.out.println("no");
				continue;
			}

			for (int x = 1; x <= n; x++) {
				valid = false;
				for (int y = 1; y <= n; y++) {
					if (g[x][y] == i)
						valid = true;
				}
				if (!valid) {
					break;
				}
			}
			if (!valid) {
				System.out.println("no");
				continue;
			}
			System.out.println("yes");
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
