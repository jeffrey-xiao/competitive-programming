package contest.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOI_2009_Rectangle {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		Point[] p = new Point[n];
		for (int x = 0; x < n; x++)
			p[x] = new Point(readDouble(), readDouble());
		ArrayList<State> s = new ArrayList<State>();
		double[][] dist = new double[n][n];
		for (int x = 0; x < n; x++)
			for (int y = x + 1; y < n; y++) {
				dist[x][y] = dist(p[x], p[y]);
				s.add(new State(dist[x][y], x, y));
			}
		Collections.sort(s);
		double max = 0;
		for (int x = 0; x < s.size(); x++) {
			for (int y = x + 1; y < s.size() && s.get(x).dist == s.get(y).dist; y++) {
				int i1 = s.get(x).a;
				int i2 = s.get(x).b;
				int i3 = s.get(y).a;
				int i4 = s.get(y).b;
				Point m1 = mid(p[i1], p[i2]);
				Point m2 = mid(p[i3], p[i4]);
				if (m1.x == m2.x && m1.y == m2.y) {
					double area = dist[i1][i3] * dist[i1][i4];
					max = Math.max(max, area);
				}
			}
		}
		System.out.println((Math.round(max)));
	}

	static double dist (Point p1, Point p2) {
		double x = p1.x - p2.x;
		double y = p1.y - p2.y;
		return Math.sqrt(x * x + y * y);
	}

	static Point mid (Point p1, Point p2) {
		return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
	}

	static class State implements Comparable<State> {
		double dist;
		int a, b;

		State (double dist, int a, int b) {
			this.dist = dist;
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo (State s) {
			if (dist == s.dist)
				return 0;
			return dist < s.dist ? -1 : 1;
		}
	}

	static class Point {
		double x, y;

		Point (double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
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
