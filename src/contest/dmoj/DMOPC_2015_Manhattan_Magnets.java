package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Manhattan_Magnets {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int[] movex = {-1, 1, 0, 0};
	static final int[] movey = {0, 0, -1, 1};
	static final int SIZE = 4000;
	static final int RSIZE = SIZE * 2 - 1;
	static int[][] rg = new int[RSIZE + 2][RSIZE + 2];

	static int m, n;
	static Point[] magnets;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		m = readInt();
		magnets = new Point[m];
		for (int i = 0; i < m; i++)
			magnets[i] = new Point(readInt(), readInt());
		n = readInt();
		for (int i = 0; i < n; i++) {
			Point p = new Point(readInt(), readInt());
			int d = 1 << 30;
			for (int j = 0; j < m; j++)
				d = Math.min(d, Math.abs(p.x - magnets[j].x) + Math.abs(p.y - magnets[j].y));
			d--;
			p = getRotated(p);
			if (d == -1)
				continue;
			p.x -= d;
			p.y -= d;
			int x1 = Math.max(1, p.x + 1), y1 = Math.max(1, p.y + 1), x2 = Math.min(RSIZE + 1, p.x + 2 * d + 2), y2 = Math.min(RSIZE + 1, p.y + 2 * d + 2);
			rg[x1][y1]++;
			rg[x2][y1]--;
			rg[x1][y2]--;
			rg[x2][y2]++;
		}
		for (int i = 1; i <= RSIZE; i++)
			for (int j = 1; j <= RSIZE; j++)
				rg[i][j] += rg[i - 1][j] + rg[i][j - 1] - rg[i - 1][j - 1];
		int ans = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				Point curr = new Point(i, j);
				curr = getRotated(curr);
				ans = Math.max(ans, rg[curr.x + 1][curr.y + 1]);
			}
		}

		out.println(ans);
		out.close();
	}

	static Point getRotated (Point p) {
		return new Point(p.x + p.y, p.y - p.x + SIZE - 1);
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
