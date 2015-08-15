package coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class COCI_2008_RELJEF {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static ArrayList<Point> p = new ArrayList<Point>();
	static int r, c;
	static char[][] g;
	static int[] movex = {-1, 1, 0, 0};
	static int[] movey = {0, 0, -1, 1};

	public static void main (String[] args) throws IOException {
		r = readInt();
		c = readInt();
		g = new char[r][c];

		for (int i = 0; i < r; i++)
			g[i] = next().toCharArray();

		int q = readInt();
		for (int i = 0; i < q; i++) {
			int h = r - readInt();
			int ny = -1;
			if (i % 2 == 0) {
				for (int j = 0; j < c; j++) {
					if (g[h][j] == 'x') {
						ny = j;
						g[h][j] = '.';
						break;
					}
				}
			} else {
				for (int j = c - 1; j >= 0; j--) {
					if (g[h][j] == 'x') {
						ny = j;
						g[h][j] = '.';
						break;
					}
				}
			}
			for (int z = 0; z < 4; z++) {
				int newx = h + movex[z];
				int newy = ny + movey[z];
				p.clear();
				fill(newx, newy);
				adjust();
			}

		}
		for (int x = 0; x < r; x++) {
			for (int y = 0; y < c; y++)
				System.out.print(g[x][y]);
			System.out.println();
		}
		System.out.println();
	}

	private static boolean adjust () {
		if (p.size() != 0) {
			int adjust = Integer.MAX_VALUE;
			for (int j = 0; j < p.size(); j++) {
				for (int i = p.get(j).x; i < r; i++) {
					if (g[i][p.get(j).y] == 'x') {
						adjust = Math.min(adjust, i - p.get(j).x - 1);
						break;
					}
				}
				adjust = Math.min(adjust, r - p.get(j).x - 1);
			}
			if (adjust == Integer.MAX_VALUE)
				adjust = 0;
			for (int j = 0; j < p.size(); j++) {
				g[p.get(j).x + adjust][p.get(j).y] = 'x';
			}
			return adjust == 0;
		}
		return false;
	}

	private static void fill (int x, int y) {
		if (x < 0 || x >= r || y < 0 || y >= c || g[x][y] == '.')
			return;
		g[x][y] = '.';
		p.add(new Point(x, y));
		fill(x + 1, y);
		fill(x - 1, y);
		fill(x, y - 1);
		fill(x, y + 1);
	}

	static class Point {
		int x, y;

		Point (int x, int y) {
			this.x = x;
			this.y = y;
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
