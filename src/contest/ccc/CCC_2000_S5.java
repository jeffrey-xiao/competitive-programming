package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2000_S5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		Point[] points = new Point[n];
		Segment[] segments = new Segment[n];
		for (int x = 0; x < n; x++) {
			points[x] = new Point(readDouble(), readDouble());
			segments[x] = new Segment(0, 1000);
		}
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (x == y)
					continue;
				double mid = getMedian(points[x], points[y]);
				if (segments[y].lo < segments[y].hi) {
					if (mid == Double.POSITIVE_INFINITY || mid == Double.NEGATIVE_INFINITY) {
						if (points[y].y < points[x].y)
							segments[x].hi = -1;
						else
							segments[y].hi = -1;
					} else {
						if (points[x].x < points[y].x)
							segments[x].hi = Math.min(segments[x].hi, mid);
						else
							segments[x].lo = Math.max(segments[x].lo, mid);
					}
				}
			}
		}
		for (int x = 0; x < n; x++) {
			if (segments[x].lo <= segments[x].hi)
				System.out.printf("The sheep at (%.2f, %.2f) might be eaten.\n", points[x].x, points[x].y);
		}
	}

	private static double getMedian (Point p1, Point p2) {
		double slope = -1.0d / ((p1.y - p2.y) / (p1.x - p2.x));
		Point middle = getMiddle(p1, p2);
		if (slope == Double.POSITIVE_INFINITY || slope == Double.NEGATIVE_INFINITY)
			return middle.x;
		double b = middle.y - slope * middle.x;
		double x = (0.0d - b) / slope;
		if (x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY)
			return x;
		return x;
	}

	private static Point getMiddle (Point p1, Point p2) {
		return new Point((p1.x + p2.x) / 2.0d, (p1.y + p2.y) / 2.0d);
	}

	static class Segment {
		double lo;
		double hi;

		Segment (double lo, double hi) {
			this.lo = lo;
			this.hi = hi;
		}
	}

	static class Point {
		double x;
		double y;

		Point (double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString () {
			return "(" + x + ", " + y + ")";
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
