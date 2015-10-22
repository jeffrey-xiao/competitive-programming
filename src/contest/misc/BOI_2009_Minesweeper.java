package contest.misc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOI_2009_Minesweeper {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int r = readInt();
		Point[] p = new Point[n];
		for (int x = 0; x < n; x++)
			p[x] = new Point(readInt(), readInt());
		Arrays.sort(p);
		ArrayList<Point> u = new ArrayList<Point>();
		ArrayList<Point> l = new ArrayList<Point>();
		for (int x = 0; x < n; x++) {
			int size = l.size();
			while (size >= 2 && (ccw(l.get(size - 2), l.get(size - 1), p[x]) <= 0)) {
				l.remove(size - 1);
				size = l.size();
			}
			l.add(p[x]);
		}
		for (int x = n - 1; x >= 0; x--) {
			int size = u.size();
			while (size >= 2 && (ccw(u.get(size - 2), u.get(size - 1), p[x]) <= 0)) {
				u.remove(size - 1);
				size = u.size();
			}
			u.add(p[x]);
		}
		u.remove(u.size() - 1);
		l.remove(l.size() - 1);
		u.addAll(l);
		double dist = 0;
		for (int x = 0; x < u.size(); x++)
			dist += dist(u.get(x), u.get((x + 1) % u.size()));
		System.out.println(dist + 2 * r * Math.PI);
	}

	private static double dist (Point p1, Point p2) {
		double x = p1.x - p2.x;
		double y = p1.y - p2.y;
		return Math.sqrt(x * x + y * y);
	}

	static int ccw (Point p1, Point p2, Point p3) {
		return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
	}

	static class Point implements Comparable<Point> {
		int x, y;

		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo (Point o) {
			if (x == o.x)
				return y - o.y;
			return x - o.x;
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
