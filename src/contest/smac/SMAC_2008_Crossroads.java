package contest.smac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SMAC_2008_Crossroads {

	static BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int c, r, b;
	static int total = 0;
	static int[][] grid, tl, tr, bl, br;

	public static void main (String[] args) throws IOException {
		c = readInt();
		r = readInt();
		b = readInt();
		grid = new int[r + 2][c + 2];
		tl = new int[r + 2][c + 2];
		tr = new int[r + 2][c + 2];
		bl = new int[r + 2][c + 2];
		br = new int[r + 2][c + 2];
		for (int x = 1; x <= r; x++) {
			for (int y = 1; y <= c; y++) {
				grid[x][y] = readInt();
			}
		}
		// calculating top values
		for (int x = 1; x <= r; x++) {
			// calculating left values
			for (int y = 1; y <= c; y++) {
				tl[x][y] = grid[x][y] + tl[x - 1][y] + tl[x][y - 1] - tl[x - 1][y - 1];
				total += grid[x][y];
			}
			// calculating right values
			for (int y = c; y >= 1; y--)
				tr[x][y] = grid[x][y] + tr[x - 1][y] + tr[x][y + 1] - tr[x - 1][y + 1];
		}
		// calculating bottom values
		for (int x = r; x >= 1; x--) {
			// calculating left values
			for (int y = 1; y <= c; y++)
				bl[x][y] = grid[x][y] + bl[x + 1][y] + bl[x][y - 1] - bl[x + 1][y - 1];
			// calculating right values
			for (int y = c; y >= 1; y--)
				br[x][y] = grid[x][y] + br[x + 1][y] + br[x][y + 1] - br[x + 1][y + 1];
		}
		int max = 0;
		poss(2, 1);
		for (int x = 1; x <= r; x++) {
			int y;
			for (y = c; y >= 1 && !poss(x, y); y--)
				;
			if (y == 0)
				break;
			max = Math.max(x * c + y * r - x * y, max);
		}
		System.out.println(max);
	}

	private static boolean poss (int row, int col) {
		for (int x = 1; x + row - 1 <= r; x++) {
			for (int y = 1; y + col - 1 <= c; y++) {
				int cost = total - tl[x - 1][y - 1] - tr[x - 1][y + col] - bl[x + row][y - 1] - br[x + row][y + col];
				if (cost <= b)
					return true;
			}
		}
		return false;
	}

	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(buff.readLine().trim());
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
		return buff.readLine().trim();
	}
}
