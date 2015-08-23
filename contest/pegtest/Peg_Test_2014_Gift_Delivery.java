package contest.pegtest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Peg_Test_2014_Gift_Delivery {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static HashMap<Point, Point> wormholes = new HashMap<Point, Point>();
	static int r, c;
	static int[] movex = {0, 0, -1, 1};
	static int[] movey = {-1, 1, 0, 0};

	public static void main (String[] args) throws IOException {
		r = readInt();
		c = readInt();
		int h = readInt();
		int w = readInt();
		Point[] loc = new Point[h + 1];
		for (int x = 1; x <= h; x++) {
			loc[x] = new Point(readInt() - 1, readInt() - 1);
		}
		for (int x = 0; x < w; x++) {
			int x1 = readInt() - 1;
			int y1 = readInt() - 1;
			int x2 = readInt() - 1;
			int y2 = readInt() - 1;
			Point p1 = new Point(x1, y1);
			Point p2 = new Point(x2, y2);
			wormholes.put(p1, p2);
		}
		loc[0] = new Point(readInt() - 1, readInt() - 1);
		int total = 0;
		for (int x = 0; x <= h; x++) {
			total += bfs(loc[x], loc[(x + 1) % (h + 1)]);
		}
		System.out.println(total);
	}

	private static int bfs (Point s, Point e) {
		boolean[][] v = new boolean[r][c];
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(s.x, s.y, 0));
		v[s.x][s.y] = true;
		while (!q.isEmpty()) {
			Point curr = q.poll();
			if (curr.x == e.x && curr.y == e.y)
				return curr.moves;
			for (int z = 0; z < 4; z++) {
				int x = (movex[z] + curr.x + r) % r;
				int y = (movey[z] + curr.y + c) % c;
				if (wormholes.get(new Point(x, y)) != null) {
					Point p = wormholes.get(new Point(x, y));
					x = p.x;
					y = p.y;
				}
				if (v[x][y])
					continue;
				v[x][y] = true;
				q.offer(new Point(x, y, curr.moves + 1));
			}
		}
		return -1;
	}

	static class Point {
		int x, y, moves;

		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}

		Point (int x, int y, int moves) {
			this.x = x;
			this.y = y;
			this.moves = moves;
		}

		@Override
		public int hashCode () {
			return new Integer(x).hashCode() * 31 + new Integer(x).hashCode();
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Point) {
				Point p = (Point) o;
				return x == p.x && y == p.y;
			}
			return false;
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
