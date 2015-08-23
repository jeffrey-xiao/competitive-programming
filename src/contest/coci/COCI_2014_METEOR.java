package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class COCI_2014_METEOR {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	static int[][] p;
	static boolean[][] poss;
	static int m;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				System.out)));
		br = new BufferedReader(new FileReader("test.txt"));
		// ps = new PrintWriter("output.txt");

		int r = readInt();
		int c = readInt();
		char[][] g = new char[r][c];
		ArrayList<Point> p = new ArrayList<Point>();
		for (int i = 0; i < r; i++) {
			g[i] = next().toCharArray();

		}
		ArrayList<Point> ans = new ArrayList<Point>();
		for (int j = 0; j < c; j++) {
			boolean first = true;
			for (int i = r - 1; i >= 0; i--) {

				if (g[i][j] == 'X') {
					g[i][j] = '.';
					if (first) {
						p.add(new Point(i, j));
						first = false;
					}
					ans.add(new Point(i, j));
				}
			}
		}
		boolean change = true;
		int displace = 0;
		while (change) {
			change = true;
			for (Point pt : p) {
				if (pt.x + displace == r - 1
						|| g[pt.x + 1 + displace][pt.y] == '#') {
					change = false;
					break;
				}
			}
			if (change)
				displace++;
		}
		for (Point pt : ans)
			g[pt.x + displace][pt.y] = 'X';
		for (int i = 0; i < r; i++) {
			ps.println(new String(g[i]));
		}
		ps.close();
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