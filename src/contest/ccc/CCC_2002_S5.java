package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2002_S5 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		double w = readInt();
		double h = readInt();
		double sx = readInt();
		double sy = readInt();
		double slope = sy / (w - sx);
		boolean done = false;
		double x = 0;
		double y = 0;
		// project the graph to simulate bouncing
		for (int z = 1; z <= 100000 && !done; z++) {
			// get y point on width edge
			y = slope * ((z * w) - sx);
			// get x point on height edge
			x = (z * h) / slope + sx;
			// System.out.println(y + " " + x);
			// get the height and width of the blocks
			int closeY = (int) ((int) ((y - h / 2) / h + 1) * h);
			int closeX = (int) ((int) ((x - w / 2) / w + 1) * w);
			// System.out.println(closeY + " " + closeX);
			if (Math.abs(closeY - y) < 5) {
				if (closeY != y) {
					System.out.println(z - 1 + (int) (y / h));
					return;
				}
				System.out.println(z - 1 + (int) (y / h) - 1);
				return;
			} else if (Math.abs(closeX - x) < 5) {
				if (closeX != x) {
					System.out.println(z - 1 + (int) (x / w));
					return;
				}
				System.out.println(z - 1 + (int) (x / w) - 1);
				return;
			}
		}
		System.out.println(0);
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
