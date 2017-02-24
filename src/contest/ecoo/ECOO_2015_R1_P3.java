package contest.ecoo;

import java.util.*;
import java.io.*;

public class ECOO_2015_R1_P3 {

	static BufferedReader br;
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static double x, y, s, n;
	static double[] dx = {0, 0, -Math.cos(60 / 180.0 * Math.PI) / 3.0, Math.cos(60 / 180.0 * Math.PI) / 3.0, -Math.cos(60 / 180.0 * Math.PI) * 2.0 / 3.0, 0, Math.cos(60 / 180.0 * Math.PI) * 2.0 / 3.0, -Math.cos(60 / 180.0 * Math.PI), -Math.cos(60 / 180.0 * Math.PI) / 3.0, Math.cos(60 / 180.0 * Math.PI) / 3.0, Math.cos(60 / 180.0 * Math.PI)};
	static double[] dy = {0, 0, Math.cos(30 / 180.0 * Math.PI) / 3.0, Math.cos(30 / 180.0 * Math.PI) / 3.0, Math.cos(30 / 180.0 * Math.PI) * 2.0 / 3.0, Math.cos(30 / 180.0 * Math.PI) * 2.0 / 3.0, Math.cos(30 / 180.0 * Math.PI) * 2.0 / 3.0, Math.cos(30 / 180.0 * Math.PI), Math.cos(30 / 180.0 * Math.PI), Math.cos(30 / 180.0 * Math.PI), Math.cos(30 / 180.0 * Math.PI)};

	static final int TEST_CASES = 10;
	static final int PIN_POSITIONS = 5;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new FileReader("DATA31.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		for (int qq = 0; qq < TEST_CASES; qq++) {
			x = readDouble() * Math.pow(10, readInt());
			y = readDouble() * Math.pow(10, readInt());
			s = readDouble() * Math.pow(10, readInt());
			n = Math.pow(10, readInt());
			for (int q = 0; q < PIN_POSITIONS; q++) {
				double px = readDouble() * Math.pow(10, readInt());
				double py = readDouble() * Math.pow(10, readInt());
				double minDist = 1 << 30;
				int minIndex = -1;
				for (int i = 1; i <= 10; i++) {
					double calcX = dx[i] * s + x;
					double calcY = dy[i] * s + y;

					double d = dist(calcX, calcY, px, py);
					if (d < minDist) {
						minDist = d;
						minIndex = i;
					}
				}
				System.out.print(minIndex + " ");
			}
			System.out.println();
		}
	}

	static double dist (double x1, double y1, double x2, double y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return dx * dx + dy * dy;
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