package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class MockCCC_2015_S3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static Point[] p;
	static HashSet<Line> curr = new HashSet<Line>();
	static boolean[] v;
	static int n;
	static int count = 0;

	public static void main (String[] args) throws IOException {
		n = readInt();
		p = new Point[n];
		v = new boolean[n];
		for (int x = 0; x < n; x++)
			p[x] = new Point(readInt(), readInt());
		v[0] = true;
		compute(0);
		System.out.println(count / 2);
	}

	public static void compute (int i) {
		boolean done = true;
		main : for (int x = 0; x < n; x++) {
			if (!v[x]) {
				done = false;
				Line l = new Line(p[i], p[x]);
				for (Line line : curr) {
					if (l.intersect(line)) {
						continue main;
					}
				}
				v[x] = true;
				curr.add(l);
				compute(x);
				v[x] = false;
				curr.remove(l);
			}
		}
		if (done) {
			Line nl = new Line(p[i], p[0]);
			for (Line line : curr) {
				if (line.intersect(nl))
					return;
			}
			curr.add(nl);
			count++;
			curr.remove(nl);
		}
	}

	static class Point {
		double x, y;

		Point (double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Point) {
				Point p = (Point) o;
				return x == p.x && y == p.y;
			}
			return false;
		}

		@Override
		public int hashCode () {
			return new Double(x).hashCode() * 31 + new Double(y).hashCode();
		}

		@Override
		public String toString () {
			return "(" + x + "," + y + ")";
		}
	}

	static class Line {
		double A, B, C;
		Point p1, p2;

		Line (Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
			A = p2.y - p1.y;
			B = p1.x - p2.x;
			C = A * p1.x + B * p1.y;
		}

		public boolean intersect (Line l) {
			double delta = A * l.B - l.A * B;
			if (delta == 0) {
				if (p1.equals(l.p1) || p1.equals(l.p2) || p2.equals(l.p1) || p2.equals(l.p2))
					return true;
				return false;
			}
			double x = (l.B * C - B * l.C) / delta;
			double y = (A * l.C - l.A * C) / delta;
			if (p1.equals(l.p1) || p1.equals(l.p2) || p2.equals(l.p1) || p2.equals(l.p2))
				return false;
			if (Math.min(p1.x, p2.x) <= x && x <= Math.max(p1.x, p2.x) && Math.min(p1.y, p2.y) <= y && y <= Math.max(p1.y, p2.y) && Math.min(l.p1.x, l.p2.x) <= x && x <= Math.max(l.p1.x, l.p2.x) && Math.min(l.p1.y, l.p2.y) <= y && y <= Math.max(l.p1.y, l.p2.y))
				return true;
			return false;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Line) {
				Line p = (Line) o;
				return p1.equals(p.p1) && p2.equals(p.p2);
			}
			return false;
		}

		@Override
		public int hashCode () {
			return p1.hashCode() * 31 + p2.hashCode();
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
