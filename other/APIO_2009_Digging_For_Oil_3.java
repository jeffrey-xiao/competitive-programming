package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class APIO_2009_Digging_For_Oil_3 {

	static BufferedReader b = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int[][] tl;
	static int[][] tr;
	static int[][] bl;
	static int[][] br;
	static int k;
	static int[][] g;
	static int count;

	public static void main (String[] args) throws IOException {
		int r = readInt();
		int c = readInt();
		k = readInt();
		g = new int[r + 1][c + 1];
		tl = new int[r + 1][c + 1];
		tr = new int[r + 1][c + 1];
		bl = new int[r + 1][c + 1];
		br = new int[r + 1][c + 1];
		for (int x = 1; x <= r; x++)
			for (int y = 1; y <= c; y++)
				g[x][y] = readInt() + g[x - 1][y] + g[x][y - 1]
						- g[x - 1][y - 1];
		int max = 0;
		for (int x = k; x <= r - k; x++) {
			for (int y = k; y <= c - k; y++) {
				int TL = getTL(0, 0, x, y);
				int BL = getBL(x, 0, r, y);
				int TR = getTR(0, y, x, c);
				int BR = getBR(x, y, r, c);

				int top = getTL(0, 0, x, c);
				int left = getTL(0, 0, r, y);
				int right = getBR(0, y, r, c);
				int bottom = getBR(x, 0, r, c);
				// System.out.println(x + " " + y + " " + top + " " + left + " "
				// + right + " " + bottom);
				max = Math.max(max, top + BL + BR);
				max = Math.max(max, left + TR + BR);
				max = Math.max(max, bottom + TL + TR);
				max = Math.max(max, right + TL + BL);
			}
		}
		for (int x = k; x <= r; x++) {
			for (int y = x + k; y <= r; y++) {
				int top = getTL(0, 0, x, c);
				int bottom = getBR(y, 0, r, c);
				for (int z = k; z <= c; z++) {
					int middle = g[y][z] - g[y - k][z] - g[y][z - k]
							+ g[y - k][z - k];
					max = Math.max(max, top + middle + bottom);
				}
			}
		}
		for (int x = k; x <= c; x++) {
			for (int y = x + k; y <= c; y++) {
				int left = getTL(0, 0, r, x);
				int right = getBR(0, y, r, c);
				for (int z = k; z <= r; z++) {
					int middle = g[z][y] - g[z - k][y] - g[z][y - k]
							+ g[z - k][y - k];
					max = Math.max(max, left + middle + right);
				}
			}
		}
		System.out.println(max);
	}

	static int getTL (int x1, int y1, int x2, int y2) {
		// x2, y2
		if (x2 - x1 < k || y2 - y1 < k)
			return 0;
		if (tl[x2][y2] > 0)
			return tl[x2][y2];
		count++;
		int max = g[x2][y2] - g[x2 - k][y2] - g[x2][y2 - k] + g[x2 - k][y2 - k];
		max = Math.max(max, getTL(x1, y1, x2 - 1, y2));
		max = Math.max(max, getTL(x1, y1, x2, y2 - 1));
		tl[x2][y2] = max;
		return max;
	}

	static int getTR (int x1, int y1, int x2, int y2) {
		// x2, y1
		if (x2 - x1 < k || y2 - y1 < k)
			return 0;
		if (tr[x2][y1] > 0)
			return tr[x2][y1];
		count++;
		int max = g[x2][y1 + k] - g[x2 - k][y1 + k] - g[x2][y1] + g[x2 - k][y1];
		max = Math.max(max, getTR(x1, y1, x2 - 1, y2));
		max = Math.max(max, getTR(x1, y1 + 1, x2, y2));
		tr[x2][y1] = max;
		return max;
	}

	static int getBL (int x1, int y1, int x2, int y2) {
		// x1, y2
		if (x2 - x1 < k || y2 - y1 < k)
			return 0;
		if (bl[x1][y2] > 0)
			return bl[x1][y2];
		count++;
		int max = g[x1 + k][y2] - g[x1][y2] - g[x1 + k][y2 - k] + g[x1][y2 - k];
		max = Math.max(max, getBL(x1 + 1, y1, x2, y2));
		max = Math.max(max, getBL(x1, y1, x2, y2 - 1));
		bl[x1][y2] = max;
		return max;
	}

	static int getBR (int x1, int y1, int x2, int y2) {
		// x1,y1
		if (x2 - x1 < k || y2 - y1 < k)
			return 0;
		if (br[x1][y1] > 0)
			return br[x1][y1];
		count++;
		int max = g[x1 + k][y1 + k] - g[x1][y1 + k] - g[x1 + k][y1] + g[x1][y1];
		max = Math.max(max, getBR(x1 + 1, y1, x2, y2));
		max = Math.max(max, getBR(x1, y1 + 1, x2, y2));
		br[x1][y1] = max;
		return max;
	}

	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(b.readLine().trim());
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
		return b.readLine().trim();
	}
}
