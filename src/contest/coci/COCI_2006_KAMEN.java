package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2006_KAMEN {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static char[][] g;
	static int r, c;
	static Point[] curr;
	static Point[][] prev;

	public static void main (String[] args) throws IOException {
		r = readInt();
		c = readInt();
		g = new char[r][];
		prev = new Point[r][c];
		curr = new Point[c];
		for (int i = 0; i < r; i++)
			g[i] = next().toCharArray();

		int n = readInt();
		for (int i = 0; i < n; i++) {
			Point p = new Point(0, readInt() - 1);
			p.fall();
			while (p.adjust())
				p.fall();
			g[p.x][p.y] = 'O';
		}
		for (int a = 0; a < r; a++) {
			for (int b = 0; b < c; b++) {
				System.out.print(g[a][b] + "");
			}
			System.out.println();
		}
	}

	static boolean isValid (int x, int y) {
		if (0 <= x && x < r && 0 <= y && y < c && g[x][y] == '.')
			return true;
		return false;
	}

	static class Point {
		int x, y;

		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}

		boolean adjust () {
			if (x + 1 < r && g[x + 1][y] == 'O') {
				if (isValid(x, y - 1) && isValid(x + 1, y - 1)) {
					y--;
					x++;
					return true;
				} else if (isValid(x, y + 1) && isValid(x + 1, y + 1)) {
					y++;
					x++;
					return true;
				}
			}
			return false;
		}

		void fall () {
			while (x + 1 < r && g[x + 1][y] == '.') {
				x++;
			}
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
