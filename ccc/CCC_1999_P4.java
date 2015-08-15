package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_1999_P4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] movex = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] movey = {-1, 1, -2, 2, -2, 2, -1, 1};

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			int r = readInt();
			int c = readInt();
			int pr = r - readInt();
			int pc = readInt() - 1;
			int kr = r - readInt();
			int kc = readInt() - 1;
			int[][] grid = new int[r][c];
			Queue<Point> moves = new LinkedList<Point>();
			moves.add(new Point(kr, kc, 0));
			grid[kr][kc] = -1;
			while (!moves.isEmpty()) {
				Point curr = moves.poll();
				for (int z = 0; z < 8; z++) {
					int nx = curr.x + movex[z];
					int ny = curr.y + movey[z];
					if (nx < 0 || ny < 0 || nx >= r || ny >= c)
						continue;
					if (grid[nx][ny] != 0)
						continue;
					moves.offer(new Point(nx, ny, curr.moves + 1));
					grid[nx][ny] = curr.moves + 1;
				}
			}
			boolean win = false;
			boolean stale = false;
			int staleMoves = 0;
			int winMoves = 0;
			/*
			 * for(int x = 0; x < r; x++){ for(int y = 0; y < c; y++)
			 * System.out.print(grid[x][y] + " "); System.out.println();
			 * 
			 * }
			 */
			for (int x = pr - 1; x >= 0; x--) {
				int time = pr - x;
				if (grid[x][pc] == 0)
					continue;
				if (x != 0
						&& grid[x][pc] <= time
						&& ((grid[x][pc] == -1 ? 0 : grid[x][pc]) - time) % 2 == 0) {
					win = true;
					winMoves = time;
					break;
				}
				if (grid[x][pc] <= time
						&& ((grid[x][pc] == -1 ? 0 : grid[x][pc]) - time + 1) % 2 == 0
						&& !stale) {
					stale = true;
					staleMoves = time - 1;
				}
			}
			if (win)
				System.out.printf("Win in %d knight move(s).\n", winMoves);
			else if (stale)
				System.out.printf("Stalemate in %d knight move(s).\n",
						staleMoves);
			else
				System.out.printf("Loss in %d knight move(s).\n", pr - 1);
		}
	}

	static class Point {
		int x;
		int y;
		int moves;

		Point (int x, int y, int moves) {
			this.x = x;
			this.y = y;
			this.moves = moves;
		}
	}

	/*
	 * 99 2 1 1 3 1
	 */
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
