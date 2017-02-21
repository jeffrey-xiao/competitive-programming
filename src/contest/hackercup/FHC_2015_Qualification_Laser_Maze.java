package contest.hackercup;

import java.util.*;
import java.io.*;

public class FHC_2015_Qualification_Laser_Maze {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[] movex = {-1, 0, 1, 0};
	static int[] movey = {0, 1, 0, -1};

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		main : for (int t = 1; t <= n; t++) {
			int r = readInt();
			int c = readInt();
			int sx = 0, sy = 0, ex = 0, ey = 0;
			char[][] grid = new char[r][c];
			int[][] lazer = new int[r][c];
			ArrayList<Lazer> l = new ArrayList<Lazer>();
			for (int x = 0; x < r; x++) {
				char[] in = next().toCharArray();
				for (int y = 0; y < c; y++) {
					if (in[y] == 'S') {
						sx = x;
						sy = y;
						grid[x][y] = '.';
					} else if (in[y] == 'G') {
						ex = x;
						ey = y;
						grid[x][y] = '.';
					} else if (in[y] == '^') {
						l.add(new Lazer(x, y, 0));
						grid[x][y] = '#';
					} else if (in[y] == '>') {
						l.add(new Lazer(x, y, 1));
						grid[x][y] = '#';
					} else if (in[y] == 'v') {
						l.add(new Lazer(x, y, 2));
						grid[x][y] = '#';
					} else if (in[y] == '<') {
						l.add(new Lazer(x, y, 3));
						grid[x][y] = '#';
					} else {
						grid[x][y] = in[y];
					}
				}
			}
			for (int z = 0; z < l.size(); z++) {
				for (int k = 0; k < 4; k++) {
					int currX = l.get(z).p.x + movex[k];
					int currY = l.get(z).p.y + movey[k];
					while (currX >= 0 && currX < r && currY >= 0 && currY < c && grid[currX][currY] == '.') {
						lazer[currX][currY] |= 1 << ((k - l.get(z).direction + 4) % 4);
						currX += movex[k];
						currY += movey[k];
					}
				}
			}

			Queue<Point> q = new LinkedList<Point>();
			q.offer(new Point(sx, sy, 0));
			boolean[][][] v = new boolean[r][c][4];
			v[sx][sy][0] = true;
			while (!q.isEmpty()) {
				Point curr = q.poll();
				if (curr.x == ex && curr.y == ey) {
					out.printf("Case #%d: %d\n", t, curr.time);
					continue main;
				}
				for (int z = 0; z < 4; z++) {
					int nx = curr.x + movex[z];
					int ny = curr.y + movey[z];
					int time = (curr.time + 1) % 4;

					if (nx < 0 || nx >= r || ny < 0 || ny >= c || grid[nx][ny] != '.' || ((1 << time) & (lazer[nx][ny])) >= 1 || v[nx][ny][time])
						continue;
					v[nx][ny][time] = true;
					q.offer(new Point(nx, ny, curr.time + 1));
				}
			}
			out.printf("Case #%d: impossible\n", t);
		}
		out.close();
	}

	static class Point {
		int x, y, time;

		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}

		Point (int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	static class Lazer {
		Point p;
		int direction;

		Lazer (int x, int y, int direction) {
			p = new Point(x, y);
			this.direction = direction;
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
