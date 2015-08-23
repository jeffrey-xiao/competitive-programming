package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_2005_Stage_2_Number_Matrix {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] movex = {0, 0, -1, 1};
	static int[] movey = {-1, 1, 0, 0};

	public static void main (String[] args) throws IOException {
		int c = readInt();
		int r = readInt();
		int[][] grid = new int[r][c];
		for (int x = 0; x < r; x++)
			for (int y = 0; y < c; y++)
				grid[x][y] = readInt();
		for (int i = 0; i < 10; i++) {
			for (int j = i; j < 10; j++) {
				for (int k = j; k < 10; k++) {
					Queue<Point> moves = new LinkedList<Point>();
					boolean visited[][] = new boolean[r][c];
					for (int y = 0; y < c; y++) {
						if (grid[0][y] == i || grid[0][y] == j
								|| grid[0][y] == k) {
							moves.offer(new Point(0, y));
							visited[0][y] = true;
						}
					}

					while (!moves.isEmpty()) {
						Point curr = moves.poll();
						if (curr.x == r - 1) {
							System.out.print(i + " " + j + " " + k);
							return;
						}
						for (int z = 0; z < 4; z++) {
							int nx = curr.x + movex[z];
							int ny = curr.y + movey[z];
							if (nx < 0 || ny < 0 || nx >= r || ny >= c
									|| visited[nx][ny])
								continue;
							if (grid[nx][ny] == i || grid[nx][ny] == j
									|| grid[nx][ny] == k) {
								visited[nx][ny] = true;
								moves.offer(new Point(nx, ny));
							}
						}
					}
				}
			}
		}
		System.out.println("-1 -1 -1");
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
