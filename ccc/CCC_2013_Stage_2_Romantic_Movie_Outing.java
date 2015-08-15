package ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2013_Stage_2_Romantic_Movie_Outing {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int[][] bit = new int[3100][3100];
	static boolean[][] taken = new boolean[1501][1001];

	public static void main (String[] args) throws IOException {
		int L = readInt();
		int T = readInt();
		for (int i = 0; i < T; i++) {
			char ch = readCharacter();
			int r = readInt();
			int c = readInt();
			Point p = getPoint(new Point(r, c));
			// System.out.println(p.r + " " + p.c);
			if (ch == 'E') {
				if (r <= 1500) {
					update(p.r, p.c, 1);
					taken[r][c] = true;
				}
			} else if (ch == 'L') {
				if (r <= 1500) {
					update(p.r, p.c, -1);
					taken[r][c] = false;
				}
			} else {
				Point p2 = getPoint(new Point(r, c + 1));
				if (taken[r][c] || taken[r][c + 1])
					System.out.println("No");
				else
					System.out.println(query(p.r, p.c) + query(p2.r, p2.c));
			}
		}
		int min = 1 << 30;
		int minSecond = 1 << 30;
		for (int r = L + 1; r <= L + 500; r++) {
			for (int c = 1; c <= 1000; c++) {

				Point np = getPoint(new Point(r, c));
				if (taken[r][c])
					continue;
				int res = query(np.r, np.c);
				if (res <= min) {
					minSecond = min;
					min = res;
				} else if (res < minSecond) {
					minSecond = res;
				}
			}
			if (minSecond != 1 << 30)
				break;
		}
		System.out.println(min + minSecond);
	}

	static int point (int x, int y) {
		return query(x, y) - query(x - 1, y) - query(x, y - 1)
				+ query(x - 1, y - 1);
	}

	static void update (int idxx, int idxy, int value) {
		for (int x = idxx; x <= 3002; x += (x & -x)) {
			for (int y = idxy; y <= 3002; y += (y & -y)) {
				bit[x][y] += value;
			}
		}
	}

	static int query (int idxx, int idxy) {
		int sum = 0;
		for (int x = idxx; x > 0; x -= (x & -x)) {
			for (int y = idxy; y > 0; y -= (y & -y)) {
				sum += bit[x][y];
			}
		}
		return sum;
	}

	// x is col and y is row
	static Point getPoint (Point p) {
		return new Point(p.r - p.c + 1500, p.r + p.c);
	}

	static class Point {
		int r, c;

		Point (int r, int c) {
			this.r = r;
			this.c = c;
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
