package coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2007_JABUKE {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		Point a = new Point(readDouble(), readDouble());
		Point b = new Point(readDouble(), readDouble());
		Point c = new Point(readDouble(), readDouble());
		int count = 0;
		int n = readInt();
		for (int x = 0; x < n; x++) {
			Point p = new Point(readDouble(), readDouble());
			if (p.inside(a, b, c))
				count++;
		}
		System.out.println(Math.abs(a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x
				* (a.y - b.y)) / 2);
		System.out.println(count);
	}

	static class Point {
		double x;
		double y;

		Point (double x, double y) {
			this.x = x;
			this.y = y;
		}

		boolean inside (Point p1, Point p2, Point p3) {
			return area(p1, p2, p3) - area(new Point(x, y), p1, p2)
					- area(new Point(x, y), p2, p3)
					- area(new Point(x, y), p3, p1) == 0;
		}

		double area (Point a, Point b, Point c) {
			return Math.abs(a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x
					* (a.y - b.y)) / 2;
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
