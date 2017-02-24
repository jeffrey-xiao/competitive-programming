/*
 * Returns an array of size two representing the two closest points.
 * 
 * Time complexity: O(N log N)
 */

package codebook.algorithms;

import java.util.*;

public class ClosestTwoPoints {

	static Point[] findClosestPair (Point[] points) {
		Point[] ret = new Point[2];
		Arrays.sort(points, cmpx);
		solve(points, 0, points.length - 1, ret, 1 << 30);
		return ret;
	}

	static int solve (Point[] points, int l, int r, Point[] ret, int minDist) {
		if (l == r)
			return 1 << 30;
		int mid = (l + r) >> 1;
		int midx = points[mid].x;
		minDist = Math.min(solve(points, l, mid, ret, minDist), minDist);
		minDist = Math.min(solve(points, mid + 1, r, ret, minDist), minDist);

		Arrays.sort(points, l, r + 1, cmpy);
		int[] t = new int[r - l + 1];
		int size = 0;
		for (int i = l; i <= r; i++)
			if (Math.abs(points[i].x - midx) < minDist)
				t[size++] = i;

		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				int dist = dist(points[t[i]], points[t[j]]);
				if (dist < minDist) {
					minDist = dist;
					ret[0] = points[t[i]];
					ret[1] = points[t[j]];
				}
			}
		}

		return minDist;
	}

	static int findClosestPairSlow (Point[] points) {
		int ret = 1 << 30;
		for (int i = 0; i < points.length; i++)
			for (int j = i + 1; j < points.length; j++)
				ret = Math.min(ret, dist(points[i], points[j]));
		return ret;
	}

	static int dist (Point a, Point b) {
		int dx = a.x - b.x;
		int dy = a.y - b.y;
		return dx * dx + dy * dy;
	}

	static class Point {
		int x, y;

		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	final static ComparatorX cmpx = new ComparatorX();
	final static ComparatorY cmpy = new ComparatorY();

	static class ComparatorX implements Comparator<Point> {
		public int compare (Point a, Point b) {
			if (a.x == b.x)
				return a.y - b.y;
			return a.x - b.x;
		}
	}

	static class ComparatorY implements Comparator<Point> {
		public int compare (Point a, Point b) {
			return a.y - b.y;
		}
	}

	public static void main (String[] args) {
		for (int iter = 0; iter < 100; iter++) {
			int N = 100;
			Point[] points = new Point[N];
			for (int i = 0; i < N; i++)
				points[i] = new Point((int)(Math.random() * 1000), (int)(Math.random() * 1000));

			Point[] res = findClosestPair(points);
			int d1 = dist(res[0], res[1]);
			int d2 = findClosestPairSlow(points);

			assert d1 == d2;
		}
	}
}
