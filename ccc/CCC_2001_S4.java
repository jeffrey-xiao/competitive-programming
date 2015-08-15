package ccc;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2001_S4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int numOfPoints = readInt();
		Point[] points = new Point[numOfPoints];
		double min = Integer.MIN_VALUE;
		for (int x = 0; x < numOfPoints; x++)
			points[x] = new Point(readInt(), readInt());
		for (int x = 0; x < numOfPoints; x++) {
			for (int y = x + 1; y < numOfPoints; y++) {
				for (int z = y + 1; z < numOfPoints; z++) {
					double a = getDist(points[x], points[y]);
					double b = getDist(points[y], points[z]);
					double c = getDist(points[x], points[z]);
					double s = (a + b + c) / 2.0;
					double dist = 0;
					if ((a * a + b * b - c * c < 0)
							|| (b * b + c * c - a * a < 0)
							|| (c * c + a * a - b * b < 0))
						dist = Math.max(Math.max(a, b), c);
					else
						dist = (a * b * c)
								/ (2 * Math.sqrt(s * (s - a) * (s - b)
										* (s - c)));
					min = Math.max(dist, min);
				}
			}
		}
		System.out.printf("%.2f", min);
	}

	private static double getDist (Point point, Point point2) {
		return Math.sqrt(sq(point.getX() - point2.getX())
				+ sq(point.getY() - point2.getY()));
	}

	private static double sq (double f) {
		return f * f;
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
