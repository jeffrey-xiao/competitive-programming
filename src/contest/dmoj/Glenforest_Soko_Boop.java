package contest.dmoj;

import java.util.*;
import java.io.*;

public class Glenforest_Soko_Boop {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[] movex = {0,0,-1,1};
	static int[] movey = {-1,1,0,0};
	static int r, c;
	static char[][] g;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		r = readInt();
		c = readInt();
		g = new char[r][c];
		int sx = 0, sy = 0, bx = 0, by = 0, ex = 0, ey = 0;
		for (int i = 0; i < r; i++) {
			g[i] = readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				if (g[i][j] == 'P') {
					sx = i;
					sy = j;
				} else if (g[i][j] == 'B') {
					bx = i;
					by = j;
				} else if (g[i][j] == 'X') {
					ex = i;
					ey = j;
				}
			}
		}
		boolean[][][][] v = new boolean[r][c][r][c];
		v[sx][sy][bx][by] = true;
		Queue<State> q = new ArrayDeque<State>();
		q.offer(new State(sx, sy, bx, by, 0));
		while (!q.isEmpty()) {
			State s = q.poll();
			if (s.bx == ex && s.by == ey) {
				out.println(s.cnt);
				out.close();
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nx = s.x + movex[i];
				int ny = s.y + movey[i];
				int nbx = s.bx;
				int nby = s.by;
				if (!valid(nx, ny))
					continue;
				if (nx == nbx && ny == nby) {
					if (!valid(nbx + movex[i], nby + movey[i]))
						continue;
					nbx += movex[i];
					nby += movey[i];
				}
				if (v[nx][ny][nbx][nby])
					continue;
				v[nx][ny][nbx][nby] = true;
				q.offer(new State(nx, ny, nbx, nby, s.cnt+1));
			}
		}
		out.println(-1);
		out.close();
	}
	static boolean valid (int x, int y) {
		return !(x < 0 || x >= r || y < 0 || y >= c || g[x][y] == '#');
	}
	static class State {
		int x, y, bx, by, cnt;
		State (int x, int y, int bx, int by, int cnt) {
			this.x = x;
			this.y = y;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
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

