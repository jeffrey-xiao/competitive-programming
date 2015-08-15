package dwite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DWITE_2007_Velociraptor_Maze {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] movex = {0, 0, -1, 1};
	static int[] movey = {-1, 1, 0, 0};
	static boolean flag = true;
	static float fast;
	static int lastMove;
	static int ex;
	static int ey;
	static boolean[][] path;

	public static void main (String[] args) throws IOException {
		int r = readInt();
		int c = readInt();
		char[][] grid = new char[r][];
		int hx = 0;
		int hy = 0;
		int vx = 0;
		int vy = 0;
		path = new boolean[r][c];
		ex = 0;
		ey = 0;
		for (int x = 0; x < r; x++) {
			String s = next();
			grid[x] = s.toCharArray();
			for (int y = 0; y < c; y++) {
				if (s.charAt(y) == 'H') {
					hx = x;
					hy = y;
				} else if (s.charAt(y) == 'V') {
					vx = x;
					vy = y;
				} else if (s.charAt(y) == 'E') {
					ex = x;
					ey = y;
				}
			}
		}
		Point[][] prevh = new Point[r][c];
		Point[][] prevv = new Point[r][c];

		prevh[hx][hy] = new Point(-1, -1);
		prevv[vx][vy] = new Point(-1, -1);

		Queue<Point> moves = new LinkedList<Point>();

		moves.offer(new Point(hx, hy));
		while (!moves.isEmpty()) {
			Point curr = moves.poll();
			if (curr.x == ex && curr.y == ey)
				break;
			for (int z = 0; z < 4; z++) {
				int nx = curr.x + movex[z];
				int ny = curr.y + movey[z];

				if (nx < 0 || ny < 0 || nx >= r || ny >= c
						|| grid[nx][ny] == '#')
					continue;
				Point p = prevh[nx][ny];
				if (p != null)
					continue;
				prevh[nx][ny] = new Point(curr.x, curr.y);
				moves.offer(new Point(nx, ny));
			}
		}

		moves.offer(new Point(vx, vy));
		while (!moves.isEmpty()) {
			Point curr = moves.poll();

			if (curr.x == ex && curr.y == ey)
				break;
			for (int z = 0; z < 4; z++) {
				int nx = curr.x + movex[z];
				int ny = curr.y + movey[z];

				if (nx < 0 || ny < 0 || nx >= r || ny >= c
						|| grid[nx][ny] == '#')
					continue;
				Point p = prevv[nx][ny];
				if (p != null)
					continue;
				prevv[nx][ny] = new Point(curr.x, curr.y);
				moves.offer(new Point(nx, ny));
			}
		}
		float[][] numMoves = new float[r][c];
		dfs(numMoves, new Point(ex, ey), prevh);
		check(numMoves, new Point(ex, ey), prevv);
		/*
		 * for(int x = 0; x < r; x++){ for(int y = 0; y < c; y++){
		 * System.out.print(prevv[x][y] + " "); } System.out.println(); }
		 */
		if (flag && numMoves[ex][ey] > fast) {
			lastMove = (int) numMoves[ex][ey];
			flag = false;
			// System.out.println("ENTER");
		}
		if (flag)
			System.out.println("escape");
		else
			System.out.println(lastMove);
	}

	private static float dfs (float[][] numMoves, Point p, Point[][] prev) {
		if (p.x == -1 && p.y == -1)
			return 0;
		path[p.x][p.y] = true;
		numMoves[p.x][p.y] = dfs(numMoves, prev[p.x][p.y], prev);
		return numMoves[p.x][p.y] + 1;

	}

	private static float check (float[][] numMoves, Point p, Point[][] prev) {
		// System.out.println(p.x + " " + p.y);
		if (p.x == -1 && p.y == -1)
			return -0.5f;

		float value = check(numMoves, prev[p.x][p.y], prev) + 0.5f;

		// System.out.println(numMoves[p.x][p.y] + " " + value);
		// System.out.println(p.x + " " + p.y + " " + value + " "
		// +numMoves[p.x][p.y]);
		if (path[p.x][p.y] && flag && numMoves[p.x][p.y] == Math.ceil(value)) {
			flag = false;

			lastMove = (int) numMoves[p.x][p.y];
		}

		if (p.x == ex && p.y == ey) {
			fast = value;
		}
		return value;

	}

	static class Point {
		int x;
		int y;

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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
