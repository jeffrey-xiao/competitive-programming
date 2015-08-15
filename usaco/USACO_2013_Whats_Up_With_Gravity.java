package usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class USACO_2013_Whats_Up_With_Gravity {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] movex = {-1, 1};
	static char[][] grid;
	static int r;
	static int c;

	public static void main (String[] args) throws IOException {
		r = readInt();
		c = readInt();
		grid = new char[r][];
		int sx = 0;
		int sy = 0;
		for (int x = 0; x < r; x++) {
			String s = next();
			grid[x] = s.toCharArray();
			for (int y = 0; y < c; y++) {
				if (s.charAt(y) == 'C') {
					sx = x;
					sy = y;
				}
			}
		}
		Queue<Point> moves = new LinkedList<Point>();

		int[] initial = getCoor(sx, sy, true);

		if (initial[0] == -1) {
			System.out.println(-1);
			return;
		}

		moves.offer(new Point(initial[0], initial[1], 0, true));
		int[][] min = new int[r][c];
		for (int x = 0; x < r; x++)
			for (int y = 0; y < c; y++)
				min[x][y] = Integer.MAX_VALUE;
		min[initial[0]][initial[1]] = 0;
		int best = Integer.MAX_VALUE;
		while (!moves.isEmpty()) {
			Point p = moves.poll();
			// System.out.println(p.x + " " + p.y + " " + p.time);
			if (grid[p.x][p.y] == 'D') {
				best = Math.min(p.time, best);
				continue;
				// System.out.println("entered");
			}
			for (int x = -1; x <= 1; x++) {
				if (x == 0) {
					int[] z = getCoor(p.x, p.y, !p.regGrav);
					if (z[0] == -1 || min[z[0]][z[1]] <= p.time + 1
							|| grid[p.x][p.y + x] == '#')
						continue;
					min[z[0]][z[1]] = p.time + 1;
					// System.out.println(p.x + " " + p.y + " " + p.time);
					moves.offer(new Point(z[0], z[1], p.time + 1, !p.regGrav));
				} else {
					if (p.y + x < 0 || p.y + x >= c
							|| grid[p.x][p.y + x] == '#')
						continue;
					int[] z = getCoor(p.x, p.y + x, p.regGrav);

					if (z[0] == -1 || min[z[0]][z[1]] <= p.time)
						continue;
					min[z[0]][z[1]] = p.time;
					// System.out.println(p.x + " " + p.y + " " + p.time + " " +
					// z[0] + " " +z[1]);
					moves.offer(new Point(z[0], z[1], p.time, p.regGrav));
				}
			}
		}
		System.out.println(best == Integer.MAX_VALUE ? -1 : best);
	}

	private static int[] getCoor (int x, int y, boolean regGrav) {
		int dir = regGrav ? 1 : -1;
		if (x + dir >= 0 && x + dir < r) {
			if (grid[x][y] == 'D')
				return new int[] {x, y};
			while (grid[x + dir][y] != '#') {

				x += dir;
				if (grid[x][y] == 'D')
					return new int[] {x, y};
				if (x + dir < 0 || x + dir >= r)
					return new int[] {-1, -1};

			}
		}
		return new int[] {x, y};
	}

	static class Point {
		int x;
		int y;
		int time;
		boolean regGrav;

		Point (int x, int y, int time, boolean regGrav) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.regGrav = regGrav;
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
