package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class USACO_2013_Perimeter_Bronze {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean[][] grid = new boolean[102][102];
	static boolean[][] visited = new boolean[102][102];
	static int count;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		for (int z = 0; z < n; z++) {
			int x = readInt();
			int y = readInt();
			grid[x][y] = true;
		}
		Stack<Point> moves = new Stack<Point>();
		moves.push(new Point(0, 0, -1, -1));
		while (!moves.isEmpty()) {
			Point curr = moves.pop();
			int x = curr.x;
			int y = curr.y;
			if (x < 0 || y < 0 || x >= 102 || y >= 102)
				continue;
			if (grid[x][y]) {
				// if(curr.px != 0 && curr.px != 101 && curr.py != 0 && curr.py
				// != 101)
				count++;
				continue;
			}
			if (visited[x][y])
				continue;
			visited[x][y] = true;
			moves.push(new Point(x + 1, y, x, y));
			moves.push(new Point(x - 1, y, x, y));
			moves.push(new Point(x, y + 1, x, y));
			moves.push(new Point(x, y - 1, x, y));
		}
		System.out.println(count);
	}

	static class Point {
		int x;
		int y;
		int px;
		int py;

		Point (int x, int y, int px, int py) {
			this.x = x;
			this.y = y;
			this.px = px;
			this.py = py;

		}
	}

	static void fill (int x, int y) {
		if (x < 0 || y < 0 || x >= 101 || y >= 101)
			return;
		if (grid[x][y]) {
			count++;
			return;
		}
		if (visited[x][y])
			return;
		visited[x][y] = true;
		fill(x + 1, y);
		fill(x - 1, y);
		fill(x, y + 1);
		fill(x, y - 1);
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