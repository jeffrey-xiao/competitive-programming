package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2008_BAZEN {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		double sx = readDouble(), sy = readDouble();
		double fx = 0.0d, fy = 0.0d;
		if (sx == 0) {
			if (sy < 125) {
				fx = 31250 / (250 - sy);
				fy = 250 - fx;
			} else {
				fx = 31250 / sy;
				fy = 0;
			}
		} else if (sy == 0) {
			if (sx < 125) {
				fy = 31250 / (250 - sx);
				fx = 250 - fy;
			} else {
				fy = 31250 / sx;
				fx = 0;
			}
		} else {
			if (sy < 125) {
				fy = 250 - (31250 / sx);
				fx = 0;
			} else {
				fy = 0;
				fx = 250 - (31250 / sy);
			}
		}
		System.out.printf("%.2f %.2f", fx, fy);
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
