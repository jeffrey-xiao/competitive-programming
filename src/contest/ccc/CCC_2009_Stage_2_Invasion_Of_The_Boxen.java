package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2009_Stage_2_Invasion_Of_The_Boxen {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		Point s = new Point(0, 0);
		Point dir = new Point(readInt(), readInt());
		Box[] boxes = new Box[n];
		boolean[] visited = new boolean[n];
		for (int z = 0; z < n; z++) {
			int x = readInt();
			int y = readInt();
			int w = readInt();
			int h = readInt();
			boxes[z] = new Box(new Point(x, y), new Point(x + w, y), new Point(x + w, y + h), new Point(x, y + h));
		}
		while (true) {
			int index = -1;
			int type = -1;
			double minDist = Integer.MAX_VALUE;
			Point inter = null;
			for (int x = 0; x < n; x++) {
				if (visited[x])
					continue;
				int result = boxes[x].checkIntersection(s, new Point(s.x + dir.x, s.y + dir.y), false);
				if (result == -1)
					continue;
				if (boxes[x].minDist < minDist) {
					minDist = boxes[x].minDist;
					type = result;
					index = x;
					inter = new Point(boxes[x].inter.x, boxes[x].inter.y);
				}
			}
			if (index == -1)
				break;
			System.out.println(index + 1);
			visited[index] = true;
			s = new Point(inter.x, inter.y);
			if (type == 4)
				dir = new Point(-dir.x, -dir.y);
			else if (type == 0 || type == 2)
				dir = new Point(dir.x, -dir.y);
			else
				dir = new Point(-dir.x, dir.y);
		}
	}

	static class Point {
		double x, y;

		Point (double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Box {
		Point[] p = new Point[4];

		Box (Point p1, Point p2, Point p3, Point p4) {
			p[0] = p1;
			p[1] = p2;
			p[2] = p3;
			p[3] = p4;
		}

		Point inter;
		double minDist;

		public int checkIntersection (Point s, Point dir, boolean print) {
			Point closest = null;
			double dist = Integer.MAX_VALUE;
			int index = -1;
			for (int x = 0; x < 4; x++) {
				Point next = getInter(s, dir, p[x], p[(x + 1) % 4]);
				if (p[x].x == p[(x + 1) % 4].x) {
					double minY = Math.min(p[x].y, p[(x + 1) % 4].y);
					double maxY = Math.max(p[x].y, p[(x + 1) % 4].y);
					if (next.y > maxY || next.y < minY)
						continue;
				} else if (p[x].y == p[(x + 1) % 4].y) {
					double minX = Math.min(p[x].x, p[(x + 1) % 4].x);
					double maxX = Math.max(p[x].x, p[(x + 1) % 4].x);
					if (next.x > maxX || next.x < minX)
						continue;
				}

				if (dir.x - s.x > 0) {
					if (next.x < s.x)
						continue;
				} else if (dir.x - s.x < 0) {
					if (next.x > s.x)
						continue;
				}

				if (dir.y - s.y > 0) {
					if (next.y < s.y)
						continue;
				} else if (dir.y - s.y < 0) {
					if (next.y > s.y)
						continue;
				}

				double nextDist = getDist(s, next);
				if (nextDist < dist) {
					dist = nextDist;
					index = x;
					if ((next.x == p[x].x && next.y == p[x].y) || (next.x == p[(x + 1) % 4].x && next.y == p[(x + 1) % 4].y))
						index = 4;
					closest = new Point(next.x, next.y);
				}
			}
			if (index == -1)
				return -1;
			inter = new Point(closest.x, closest.y);
			minDist = dist;
			return index;
		}

		private double getDist (Point s, Point next) {
			double x = s.x - next.x;
			double y = s.y - next.y;
			return x * x + y * y;
		}

		private Point getInter (Point s1, Point s2, Point p1, Point p2) {
			double A1 = s2.y - s1.y;
			double B1 = s1.x - s2.x;
			double C1 = A1 * s1.x + B1 * s1.y;

			double A2 = p2.y - p1.y;
			double B2 = p1.x - p2.x;
			double C2 = A2 * p1.x + B2 * p1.y;

			double delta = A1 * B2 - A2 * B1;
			if (delta == 0)
				return new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
			double x = (B2 * C1 - B1 * C2) / delta;
			double y = (A1 * C2 - A2 * C1) / delta;

			return new Point(x, y);
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
