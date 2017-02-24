package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Glenforest_Hide_n_Seek {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static char[][] g;
	static boolean[][] v;
	static int[] movex = {-1, 1, 0, 0};
	static int[] movey = {0, 0, -1, 1};
	static int[][] dist;
	static int r, c, t;
	static boolean[] vis;

	public static void main (String[] args) throws IOException {
		r = readInt();
		c = readInt();
		t = readInt();
		g = new char[r][c];
		v = new boolean[r][c];
		int[] X = new int[t + 1];
		int[] Y = new int[t + 1];
		dist = new int[t + 1][t + 1];
		int index = 1;
		for (int i = 0; i < r; i++) {
			g[i] = next().toCharArray();
			for (int j = 0; j < c; j++) {
				if (g[i][j] == 'G') {
					X[0] = i;
					Y[0] = j;
					g[i][j] = (char)0;
				} else if (g[i][j] == 'H') {
					X[index] = i;
					Y[index] = j;
					g[i][j] = (char)index;
					index++;
				}
			}
		}
		for (int i = 0; i <= t; i++) {
			Queue<Point> q = new LinkedList<Point>();
			boolean[][] v = new boolean[r][c];
			v[X[i]][Y[i]] = true;
			q.offer(new Point(X[i], Y[i], 0));
			while (!q.isEmpty()) {

				Point p = q.poll();
				if (g[p.x][p.y] >= 0 && g[p.x][p.y] <= t) {
					dist[i][g[p.x][p.y]] = p.time;
				}
				for (int j = 0; j < 4; j++) {
					int nx = p.x + movex[j];
					int ny = p.y + movey[j];
					if (nx < 0 || ny < 0 || nx >= r || ny >= c || v[nx][ny] || g[nx][ny] == 'X')
						continue;
					v[nx][ny] = true;
					q.offer(new Point(nx, ny, p.time + 1));
				}
			}
		}
		vis = new boolean[t + 1];
		vis[0] = true;
		System.out.println(compute(0, 0, t));
	}

	private static int compute (int i, int total, int left) {
		if (left == 0)
			return total;
		int min = 1 << 30;
		for (int j = 0; j <= t; j++) {
			if (!vis[j]) {
				vis[j] = true;
				min = Math.min(min, compute(j, total + dist[i][j], left - 1));
				vis[j] = false;
			}
		}
		return min;
	}

	static class Point {
		int x, y, time;

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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
