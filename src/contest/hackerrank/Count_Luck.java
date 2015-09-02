package contest.hackerrank;

import java.util.*;
import java.io.*;

public class Count_Luck {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;
	
	static int[] movex = {0,0,-1,1};
	static int[] movey = {-1,1,0,0};
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int t = readInt();
		for (int qq = 0; qq < t; qq++) {
			int r = readInt();
			int c = readInt();
			char[][] g = new char[r][c];
			int sx = 0;
			int sy = 0;
			int ex = 0;
			int ey = 0;
			for (int i = 0; i < r; i++) {
				g[i] = next().toCharArray();
				for (int j = 0; j < c; j++) {
					if (g[i][j] == 'M') {
						sx = i;
						sy = j;
						g[i][j] = '.';
					} else if (g[i][j] == '*') {
						ex = i;
						ey = j;
						g[i][j] = '.';
					}
				}
			}
			boolean[][] turn = new boolean[r][c];
			Point[][] prev = new Point[r][c];
			prev[sx][sy] = new Point(-1, -1);
			Queue<Point> q = new ArrayDeque<Point>();
			q.offer(new Point(sx, sy));
			while (!q.isEmpty()) {
				Point curr = q.poll();
				int cnt = 0;
				for (int z = 0; z < 4; z++) {
					int nx = curr.x + movex[z];
					int ny = curr.y + movey[z];
					if (0 <= nx && nx < r && 0 <= ny && ny < c && g[nx][ny] == '.') {
						cnt++;
						if (prev[nx][ny] == null) {
							prev[nx][ny] = new Point(curr.x, curr.y);
							q.offer(new Point(nx, ny));
						}
					}
				}
				if (cnt > 2 || (cnt > 1 && curr.x == sx && curr.y == sy))
					turn[curr.x][curr.y] = true;
			}
			int k = readInt();
			boolean check = false;
			while (ex != -1 && ey != -1) {
				if (turn[ex][ey] && check)
					k--;
				check = true;
				Point next = prev[ex][ey];
				ex = next.x;
				ey = next.y;
			}
			if (k == 0)
				out.println("Impressed");
			else
				out.println("Oops!");
		}
		
		out.close();
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

