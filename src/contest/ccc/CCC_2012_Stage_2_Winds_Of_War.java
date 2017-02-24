package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2012_Stage_2_Winds_Of_War {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int P, T;

	static Point[] p;
	static Point o = new Point(0, 0, 0);

	static int[][] dp, points;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		P = readInt();
		T = readInt();

		p = new Point[P + T];
		dp = new int[P + T][P + T];
		points = new int[P + T][P + T];

		for (int i = 0; i < P + T; i++)
			p[i] = new Point(readInt(), readInt(), i < P ? 1 : -1);

		Arrays.sort(p);

		for (int i = 0; i < P + T; i++) {
			for (int j = i + 1; j < P + T; j++) {
				dp[i][j] = -1 << 30;
			}
		}

		for (int j = 0; j < P + T; j++) {
			for (int k = j + 1; k < P + T; k++) {
				int score = 0;

				for (int i = j + 1; i < k; i++)
					if (ccw(p[i], p[j], p[k]) > 0)
						score += p[i].type;

				dp[j][k] = Math.max(dp[j][k], p[j].type + p[k].type + score);
				for (int i = 0; i < j; i++) {
					if (ccw(p[i], p[j], p[k]) > 0) {
						dp[j][k] = Math.max(dp[j][k], dp[i][j] + p[k].type + score);
					}
				}
			}
		}

		int ans = 0;
		for (int i = 0; i < P + T; i++)
			for (int j = i + 1; j < P + T; j++)
				ans = Math.max(ans, dp[i][j]);

		out.println(ans);
		out.close();
	}

	static long ccw (Point p1, Point p2, Point p3) {
		return 1L * (p1.x - p3.x) * (p2.y - p3.y) - 1L * (p2.x - p3.x) * (p1.y - p3.y);
	}

	static class Point implements Comparable<Point> {
		int x, y, type;

		Point (int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}

		@Override
		public int compareTo (Point o) {
			Double a1 = Math.atan2(y, x);
			Double a2 = Math.atan2(o.y, o.x);
			return a1.compareTo(a2);
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
