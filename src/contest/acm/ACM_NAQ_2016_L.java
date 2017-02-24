package contest.acm;

import java.util.*;
import java.io.*;

public class ACM_NAQ_2016_L {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T;
	static Point[] p;
	static double prob;
	static int[] ans;
	static final double EPS = 1e-10;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();

		for (int i = 0; i < T; i++) {
			p = new Point[7];
			ans = null;
			for (int j = 0; j < 7; j++)
				p[j] = new Point(j + 1, readDouble(), readDouble());
			prob = readDouble();

			permute(0);
			for (int j = 0; j < 7; j++)
				out.print(ans[j] + " ");
			out.println();
		}

		out.close();
	}

	static void permute (int i) {
		if (i == 7) {
			boolean valid = true;

			for (int j = 0; j < 7; j++) {
				for (int k = j + 1; k < 7; k++) {
					Point a = p[(j - 1 + 7) % 7];
					Point b = p[j];
					Point c = p[k];
					Point d = p[k - 1];

					Point x = intersect(a, b, c, d);

					if (x != null && !x.equals(a) && !x.equals(b) && !x.equals(c) && !x.equals(d)) {
						if (within(a, b, x) && within(c, d, x)) {
							valid = false;
						}
					}
				}
			}
			if (valid) {
				double one = area() / 4.0;

				if (Math.abs(one * one * one - prob) < 0.00001) {
					int[] curr = new int[7];
					boolean smaller = true;
					for (int j = 0; j < 7; j++) {
						curr[j] = p[j].index;
						if (ans != null && ans[j] < p[j].index)
							smaller = false;
					}

					if (ans == null || smaller) {
						ans = curr;
					}
				}
			}
			return;
		}
		for (int j = i; j < 7; j++) {
			swap(i, j);
			permute(i + 1);
			swap(i, j);
		}
	}

	static boolean within (Point a, Point b, Point x) {
		return Math.min(a.x, b.x) <= x.x && x.x <= Math.max(a.x, b.x) && Math.min(a.y, b.y) <= x.y && x.y <= Math.max(a.y, b.y);
	}

	static double area () {
		double ret = 0;
		for (int i = 0; i < 7; i++)
			ret += p[i].x * p[(i + 1) % 7].y - p[(i + 1) % 7].x * p[i].y;
		return Math.abs(ret / 2);
	}

	static Point intersect (Point a, Point b, Point c, Point d) {
		double A1 = a.y - b.y;
		double B1 = b.x - a.x;
		double C1 = -A1 * a.x - B1 * a.y;

		double A2 = c.y - d.y;
		double B2 = d.x - c.x;
		double C2 = -A2 * c.x - B2 * c.y;

		double det = A1 * B2 - A2 * B1;
		if (Math.abs(det) < EPS)
			return null;
		else
			return new Point(-1, (B1 * C2 - B2 * C1) / det, (A2 * C1 - A1 * C2) / det);
	}

	static void swap (int i, int j) {
		Point temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}

	static class Point {
		int index;
		double x, y;

		Point (int index, double x, double y) {
			this.index = index;
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Point) {
				Point p = (Point)o;
				return Math.abs(p.x - x) < EPS && Math.abs(p.y - y) < EPS;
			}
			return false;
		}

		@Override
		public String toString () {
			return String.format("(%f, %f)", x, y);
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
