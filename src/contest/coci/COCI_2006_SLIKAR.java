package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class COCI_2006_SLIKAR {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] movex = {0, 0, -1, 1};
	static int[] movey = {-1, 1, 0, 0};

	public static void main (String[] args) throws IOException {
		int r = readInt();
		int c = readInt();
		char[][] grid = new char[r][];
		int[][] min = new int[r][c];
		int startx = 0;
		int starty = 0;
		int denx = 0;
		int deny = 0;
		Queue<Point> moves = new LinkedList<Point>();
		for (int x = 0; x < r; x++) {
			String next = next();
			grid[x] = next.toCharArray();
			for (int y = 0; y < c; y++) {
				if (next.charAt(y) == '*')
					moves.offer(new Point(x, y, 0));
				else if (next.charAt(y) == 'S') {
					startx = x;
					starty = y;
				} else if (next.charAt(y) == 'D') {
					denx = x;
					deny = y;
				}
				min[x][y] = Integer.MAX_VALUE;
			}

		}
		boolean[][] visited = new boolean[r][c];
		if (!moves.isEmpty())
			visited[moves.peek().x][moves.peek().y] = true;
		while (!moves.isEmpty()) {
			Point curr = moves.poll();
			// System.out.println(curr.x + " " + curr.y);

			min[curr.x][curr.y] = curr.time;
			for (int z = 0; z < 4; z++) {
				int x = curr.x + movex[z];
				int y = curr.y + movey[z];
				if (x < 0 || y < 0 || x >= r || y >= c || grid[x][y] == 'X' || grid[x][y] == 'D' || visited[x][y])
					continue;
				moves.offer(new Point(x, y, curr.time + 1));
				visited[x][y] = true;
			}
			/*
			 * for(int x = 0; x < r; x++){ for(int y = 0; y < c; y++){
			 * System.out.printf("%3d ",min[x][y] == Integer.MAX_VALUE ?
			 * 0:min[x][y]); } System.out.println(); }
			 */
		}

		visited = new boolean[r][c];
		min[denx][deny] = Integer.MAX_VALUE;
		moves.offer(new Point(startx, starty, 0));
		visited[moves.peek().x][moves.peek().y] = true;
		while (!moves.isEmpty()) {
			Point curr = moves.poll();
			// System.out.println(curr.x);
			// System.out.println(curr.x + " " + curr.y);
			if (grid[curr.x][curr.y] == 'D') {
				System.out.println(curr.time);
				return;
			}
			for (int z = 0; z < 4; z++) {
				int x = curr.x + movex[z];
				int y = curr.y + movey[z];
				if (x < 0 || y < 0 || x >= r || y >= c || visited[x][y] || grid[x][y] == 'X' || (curr.time + 1 >= min[x][y]))
					continue;
				moves.offer(new Point(x, y, curr.time + 1));
				visited[x][y] = true;
			}
		}
		System.out.println("KAKTUS");
	}

	static class Point {
		int x;
		int y;
		int time;

		Point (int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
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
