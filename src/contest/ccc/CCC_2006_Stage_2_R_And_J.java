package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2006_Stage_2_R_And_J {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		Point a = new Point(readInt(), readInt(), readInt());
		Point b = new Point(readInt(), readInt(), readInt());
		int n = readInt();
		int count = 0;
		for (int x = 0; x < n; x++)
			if (checkIntersection(a, b, new Point(readInt(), readInt(), readInt()), readInt())) 
				count++;
		
		System.out.println(count);
	}

	private static boolean checkIntersection (Point a, Point b, Point c, double r) {
		double x1 = (b.x - a.x);
		double y1 = (b.y - a.y);
		double z1 = (b.z - a.z);

		double x2 = (a.x - c.x);
		double y2 = (a.y - c.y);
		double z2 = (a.z - c.z);

		double a1 = x1 * x1 + y1 * y1 + z1 * z1;
		double b1 = 2 * (x1 * x2 + y1 * y2 + z1 * z2);
		double c1 = x2 * x2 + y2 * y2 + z2 * z2 - r * r;
		double delta = b1 * b1 - 4 * a1 * c1;

		double minX = Math.min(a.x, b.x);
		double minY = Math.min(a.y, b.y);
		double minZ = Math.min(a.z, b.z);

		double maxX = Math.max(a.x, b.x);
		double maxY = Math.max(a.y, b.y);
		double maxZ = Math.max(a.z, b.z);

		if (delta < 0)
			return false;

		double d1 = ((-b1 - Math.sqrt(delta)) / (2 * a1));
		double d2 = ((-b1 + Math.sqrt(delta)) / (2 * a1));

		x1 = a.x + d1 * (b.x - a.x);
		y1 = a.y + d1 * (b.y - a.y);
		z1 = a.z + d1 * (b.z - a.z);

		x2 = a.x + d2 * (b.x - a.x);
		y2 = a.y + d2 * (b.y - a.y);
		z2 = a.z + d2 * (b.z - a.z);
		if (minX <= x1 && x1 <= maxX && minX <= x2 && x2 <= maxX && minY <= y1 && y1 <= maxY && minY <= y2 && y2 <= maxY && minZ <= z1 && z1 <= maxZ && minZ <= y2 && z2 <= maxZ)
			return true;
		return false;
	}

	static class Point {
		double x, y, z;

		Point (double x, double y, double z) {
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
