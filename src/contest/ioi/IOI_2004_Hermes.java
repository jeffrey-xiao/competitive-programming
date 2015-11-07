package contest.ioi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IOI_2004_Hermes {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final int SHIFT = 1000;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		Point[] p = new Point[n + 1];
		p[0] = new Point(0, 0);
		for (int x = 1; x <= n; x++)
			p[x] = new Point(readInt(), readInt());
		int[][] dp1 = new int[2][2001]; // x coordinate
		int[][] dp2 = new int[2][2001]; // y coordinate
		for (int x = 0; x <= 2000; x++) {
			dp1[0][x] = Math.abs(x - 1000);
			dp2[0][x] = Math.abs(x - 1000);
		}
		for (int z = 1; z <= n; z++) {
			for (int i = 0; i <= 2000; i++) {
				dp1[z % 2][i] = Math.min(Math.abs(p[z - 1].x - p[z].x) + dp1[(z - 1) % 2][i], dp2[(z - 1) % 2][p[z].x + 1000] + Math.abs(i - 1000 - p[z - 1].y));
				dp2[z % 2][i] = Math.min(Math.abs(p[z - 1].y - p[z].y) + dp2[(z - 1) % 2][i], dp1[(z - 1) % 2][p[z].y + 1000] + Math.abs(i - 1000 - p[z - 1].x));
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= 2000; i++) {
			min = Math.min(min, Math.min(dp1[n % 2][i], dp2[n % 2][i]));
		}
		System.out.println(min);
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
