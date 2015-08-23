package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2010_Stage_2_Space_Miner {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		ArrayList<int[]> points = new ArrayList<int[]>();
		for (int x1 = 0; x1 < n; x1++) {
			int x = readInt();
			int y = readInt();
			int z = readInt();
			int r = readInt();
			int d = readInt();
			points.add(new int[] {x, y, z, r, d});
		}
		int m = readInt();
		ArrayList<int[]> waypoints = new ArrayList<int[]>();
		for (int x1 = 0; x1 < m; x1++) {
			int x = readInt();
			int y = readInt();
			int z = readInt();
			waypoints.add(new int[] {x, y, z});
		}
		int d = readInt();
		int total = 0;
		for (int i = 0; i < waypoints.size() - 1; i++) {
			int x1 = waypoints.get(i)[0];
			int y1 = waypoints.get(i)[1];
			int z1 = waypoints.get(i)[2];

			int x2 = waypoints.get(i + 1)[0];
			int y2 = waypoints.get(i + 1)[1];
			int z2 = waypoints.get(i + 1)[2];
			for (int j = points.size() - 1; j >= 0; j--) {
				int x3 = points.get(j)[0];
				int y3 = points.get(j)[1];
				int z3 = points.get(j)[2];
				/*
				 * double d1 = getDistance(x1,y1,z1,x3,y3,z3); double d2 =
				 * getDistance(x2,y2,z2,x1,y1,z1); double dot =
				 * getDot(x1-x3,y1-y3,z1-z3,x2-x1,y2-y1,z2-z1); double distance
				 * = (d1*d1*d2*d2-dot*dot)/d2/d2;
				 */

				// dist from 2 to 3
				double linex = x2 - x3;
				double liney = y2 - y3;
				double linez = z2 - z3;
				double d1 = getDistance(x2, y2, z2, x3, y3, z3);

				// direction from 2 to 1
				double newx = x2 - x1;
				double newy = y2 - y1;
				double newz = z2 - z1;
				double scale = Math.sqrt(newx * newx + newy * newy + newz
						* newz);
				double unitx = newx / scale;
				double unity = newy / scale;
				double unitz = newz / scale;

				double distance = getDot(linex, liney, linez, unitx, unity,
						unitz);
				// System.out.println(distance);
				distance = Math.sqrt(d1 * d1 - distance * distance);
				distance = Math.min(distance,
						getDistance(x1, y1, z1, x3, y3, z3));
				distance = Math.min(distance,
						getDistance(x2, y2, z2, x3, y3, z3));
				// System.out.println(getDistance(x1,y1,z1,x3,y3,z3) + " " +
				// getDistance(x2,y2,z2,x3,y3,z3));
				// System.out.println(distance + " " + j + " " + d1);
				if (distance <= d + points.get(j)[4]) {
					total += points.get(j)[3];
					// System.out.println(points.get(j)[3]);
					points.remove(j);
				}
			}

		}
		System.out.println(total);
	}

	static double getDot (double x1, double y1, double z1, double x2, double y2,
			double z2) {
		return x1 * x2 + y1 * y2 + z1 * z2;
	}

	static double getDistance (int x1, int y1, int z1, int x2, int y2, int z2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)
				+ (z1 - z2) * (z1 - z2));
	}

	static class Point {
		int x;
		int y;
		int z;

		Point (int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
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
