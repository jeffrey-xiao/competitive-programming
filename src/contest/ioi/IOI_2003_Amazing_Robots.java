package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class IOI_2003_Amazing_Robots {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] movex = {-1, 0, 1, 0};
	static int[] movey = {0, 1, 0, -1};
	static int sx1, sy1, sx2, sy2;

	/*
	 * 5 4 #### #X.# #..# ...# ##.# 1 4 3 2 W 4 4 #### #... #X.# #### 0
	 */
	public static void main (String[] args) throws IOException {

		Robot[] robots1 = null;
		Robot[] robots2 = null;
		int r1 = readInt();
		int c1 = readInt();
		char[][] grid1 = new char[r1][c1];
		readInput(grid1, robots1, r1, c1, true);

		int r2 = readInt();
		int c2 = readInt();
		char[][] grid2 = new char[r1][c1];
		readInput(grid2, robots2, r2, c2, false);
		boolean[][][][][] states = new boolean[r1][c1][r2][c2][12];
		states[sx1][sy1][sx2][sy2][0] = true;
		Queue<int[]> moves = new LinkedList<int[]>();
		moves.offer(new int[] {sx1, sy1, sx2, sy2, 0});
		while (!moves.isEmpty()) {
			int[] curr = moves.poll();
			int x1 = curr[0];
			int y1 = curr[1];
			int x2 = curr[2];
			int y2 = curr[3];
			int t = curr[4];
			for (int z = 0; z < 4; z++) {

			}
		}
	}

	public static void readInput (char[][] grid, Robot[] robots, int r, int c, boolean first) throws IOException {
		for (int x = 0; x < r; x++) {
			String s = next();
			grid[x] = s.toCharArray();
			for (int y = 0; y < c; y++) {
				if (grid[x][y] == 'X') {
					if (first) {
						sx1 = x;
						sy1 = y;
					} else {
						sx2 = x;
						sy2 = y;
					}
				}
			}
		}

		int n = readInt();
		robots = new Robot[n];
		for (int x = 0; x < n; x++)
			robots[x] = new Robot(readInt(), readInt(), readInt(), next().charAt(0));

	}

	static class Point {
		int x;
		int y;

		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Robot {
		int x;
		int y;
		short dir;
		int len;

		Robot (int x, int y, int len, char dir) {
			this.x = x;
			this.y = y;
			if (dir == 'N')
				dir = 0;
			else if (dir == 'E')
				dir = 1;
			else if (dir == 'S')
				dir = 2;
			else
				dir = 3;
			this.len = len;
		}

		Point getPoint (int time) {
			time %= 12;
			time %= len;
			int nx = x + movex[dir] * time;
			int ny = y + movey[dir] * time;
			return new Point(nx, ny);
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
