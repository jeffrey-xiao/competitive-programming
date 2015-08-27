package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class COCI_2007_PRAVOKUTNI {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int N;

	public static void main (String[] args) throws IOException {
		N = readInt();
		Point[] p = new Point[N];
		Point[] sort = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = readInt(), y = readInt();
			p[i] = new Point(x, y);
			sort[i] = new Point(x, y);
		}
		int res = 0;
		for (int i = 0; i < N; i++) {
			curr = p[i];
			Arrays.sort(sort);
			System.out.println();
			System.out.printf("Current x: %d; y: %d\n", p[i].x, p[i].y);
			// for (int j = 0; j < N; j++) {
			// System.out.printf("X: %d; Y: %d\n", sort[j].x, sort[j].y);
			// System.out.println(angle(curr, sort[j]));
			// }
			int lo = 1;
			int hi = 2;
			Point c;
			while (hi <= N) {
				if (lo == hi) {
					hi++;
					continue;
				}
				int i1 = lo, i2 = hi == N ? 1 : hi;
				c = sort[lo];
				double angle = angle(sort[i1], curr) - angle(sort[i1], sort[i2]);
				// System.out.println(angle(c, curr) + " " + angle(c,
				// sort[i2]));
				System.out.println(curr + " " + sort[i1] + " " + sort[i2]);
				boolean right = true;
				if (angle < 0) {
					angle += 360;
					right = false;
				}
				if (angle > 90) {
					if (right)
						hi++;
					else
						hi++;
				} else if (angle < 90) {
					if (right)
						lo++;
					else
						lo++;
				} else {
					hi++;
					res++;
				}
				System.out.println(angle);

			}
		}
		System.out.println(res);
	}

	private static Point curr;

	private static Double angle (Point c, Point p) {
		double angle = Math.toDegrees(Math.atan2(p.x - c.x, p.y - c.y));
		if (angle < 0)
			angle += 360;
		return angle;
	}

	static class Point implements Comparable<Point> {
		int x, y;

		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo (Point o) {
			return angle(curr, new Point(x, y)).compareTo(angle(curr, o));
		}

		public String toString () {
			return "(" + x + "," + y + ")";
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
