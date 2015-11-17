package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_1996_Stage_2_Hoppers {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean[][][][] visited;
	static int[] movex = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
	static int[] movey = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
	static boolean[][] grid;
	static int c;
	static int r;

	public static void main (String[] args) throws IOException {
		main : for (int t = readInt(); t > 0; t--) {
			c = readInt();
			r = readInt();
			grid = new boolean[r][c];
			visited = new boolean[r][c][7][7];
			int starty = readInt();
			int startx = readInt();
			int endy = readInt();
			int endx = readInt();
			for (int m = readInt(); m > 0; m--) {
				int y1 = readInt();
				int y2 = readInt();
				int x1 = readInt();
				int x2 = readInt();
				for (int z1 = y1; z1 <= y2; z1++) {
					for (int z2 = x1; z2 <= x2; z2++) {
						grid[z2][z1] = true;
					}
				}
			}

			Queue<int[]> q = new LinkedList<int[]>();
			q.add(new int[] {startx, starty, 0, 0, 0});
			while (!q.isEmpty()) {
				int[] next = q.poll();
				int x = next[0];
				int y = next[1];
				int sx = next[2];
				int sy = next[3];
				int moves = next[4];

				if (x < 0 || y < 0 || x >= r || y >= c || sx > 3 || sx < -3 || sy > 3 || sy < -3 || grid[x][y])
					continue;

				if (visited[x][y][sx + 3][sy + 3])
					continue;

				if (x == endx && endy == y) {
					System.out.printf("Optimal solution takes %d hop(s).\n", moves);
					continue main;
				}

				visited[x][y][sx + 3][sy + 3] = true;
				for (int z = 0; z < 9; z++) {
					int nextsx = sx + movex[z];
					int nextsy = sy + movey[z];
					q.add(new int[] {x + nextsx, y + nextsy, nextsx, nextsy, moves + 1});
				}
			}
			System.out.println("No solution.");
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
