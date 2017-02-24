package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_1999_P3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] g;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			int n = readInt();
			int size = (int)Math.pow(3, n);
			g = new char[size][size];
			for (int x = 0; x < size; x++)
				for (int y = 0; y < size; y++)
					g[x][y] = '*';
			if (n != 0)
				fractal(0, size - 1, 0, size - 1, n - 1);
			int x1 = readInt() - 1;
			int x2 = readInt() - 1;
			int y1 = readInt() - 1;
			int y2 = readInt() - 1;
			for (int x = x2; x >= x1; x--) {
				for (int y = y1; y <= y2; y++)
					System.out.print(g[x][y] + " ");
				System.out.println();
			}
			System.out.println();
		}
	}

	private static void fractal (int x1, int x2, int y1, int y2, int n) {
		int gapx = (x2 - x1) / 3 + 1;
		int startx = x1 + gapx;
		int gapy = (y2 - y1) / 3 + 1;
		int starty = y1 + gapy;
		for (int x = startx; x < startx + gapx; x++)
			for (int y = starty; y < starty + gapy; y++)
				g[x][y] = ' ';
		if (n == 0)
			return;
		int d = (int)Math.pow(3, n) - 1;
		fractal(x1, x1 + d, y2 - d, y2, n - 1);
		fractal(x1 + d + 1, x1 + 2 * d + 1, y2 - d, y2, n - 1);
		fractal(x2 - d, x2, y2 - d, y2, n - 1);

		fractal(x1, x1 + d, y1 + d + 1, y1 + 2 * d + 1, n - 1);
		fractal(x2 - d, x2, y1 + d + 1, y1 + 2 * d + 1, n - 1);

		fractal(x1, x1 + d, y1, y1 + d, n - 1);
		fractal(x1 + d + 1, x1 + 2 * d + 1, y1, y1 + d, n - 1);
		fractal(x2 - d, x2, y1, y1 + d, n - 1);

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
