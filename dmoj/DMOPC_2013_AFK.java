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

public class DMOPC_2013_AFK {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static char[][] g;
	static int[] movex = {0, 0, -1, 1};
	static int[] movey = {-1, 1, 0, 0};

	public static void main (String[] args) throws IOException {
		for (int qq = readInt(); qq > 0; qq--) {
			int c = readInt();
			int r = readInt();
			g = new char[r][c];
			int sx = 0, sy = 0, ex = 0, ey = 0;
			for (int i = 0; i < r; i++) {
				char[] in = readLine().toCharArray();
				for (int j = 0; j < c; j++) {
					g[i][j] = in[j];
					if (in[j] == 'C') {
						sx = i;
						sy = j;
					}
					if (in[j] == 'W') {
						ex = i;
						ey = j;
					}
				}
			}
			boolean[][] v = new boolean[r][c];
			Queue<Point> q = new LinkedList<Point>();
			q.offer(new Point(sx, sy, 0));
			v[sx][sy] = true;
			boolean good = false;
			while (!q.isEmpty()) {
				Point curr = q.poll();
				if (curr.x == ex && curr.y == ey) {
					good = true;
					System.out.println(curr.time >= 60 ? "#notworth"
							: curr.time);
					break;
				}

				for (int i = 0; i < 4; i++) {
					int nx = curr.x + movex[i];
					int ny = curr.y + movey[i];
					if (nx < 0 || ny < 0 || nx >= r || ny >= c || v[nx][ny]
							|| g[nx][ny] == 'X')
						continue;
					q.offer(new Point(nx, ny, curr.time + 1));
					v[nx][ny] = true;
				}
			}
			if (!good) {
				System.out.println("#notworth");
			}
		}
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
