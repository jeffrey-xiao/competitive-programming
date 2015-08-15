package dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DMOPC_2013_Crossing_Field {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int[] movex = {-1, 1, 0, 0};
	static int[] movey = {0, 0, -1, 1};

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int h = readInt();
		int[][] g = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				g[i][j] = readInt();
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(0, 0));
		boolean[][] v = new boolean[n][n];
		while (!q.isEmpty()) {
			Point p = q.poll();
			if (p.x == n - 1 && p.y == n - 1) {
				System.out.println("yes");
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nx = p.x + movex[i];
				int ny = p.y + movey[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= n || v[nx][ny]
						|| Math.abs(g[nx][ny] - g[p.x][p.y]) > h)
					continue;
				v[nx][ny] = true;
				q.offer(new Point(nx, ny));
			}
		}
		System.out.println("no");
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
