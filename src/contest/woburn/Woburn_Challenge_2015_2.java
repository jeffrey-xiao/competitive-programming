package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2015_2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static TreeSet<Point> points = new TreeSet<Point>();
	
	public static void main (String[] args) throws IOException, InterruptedException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		
		N = readInt();
		
		points.add(new Point(1, 1));
		int ans = 0;
		for (int i = 0; i < N; i++) {
			Point p = new Point(readInt(), readInt());
			if (p.x % 2 == 0)
				p.y = -p.y;
			Point next = points.ceiling(p);
			if (next == null) {
				ans += dist(points.floor(p), p);
			} else {
				ans += dist(points.floor(p), p) + dist(p, points.ceiling(p)) - dist(points.floor(p), points.ceiling(p));
			}
			points.add(p);
			out.println(ans);
		}
		
		out.close();
	}
	
	static int dist (Point p1, Point p2) {
		return Math.abs(p1.x  - p2.x) + Math.abs(Math.abs(p1.y) - Math.abs(p2.y));
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

