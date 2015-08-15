package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Math_On_Titan {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		Line[] l = new Line[n];
		Point[] p = new Point[2 * n];
		for (int z = 0; z < n; z++) {
			int x = readInt();
			int y1 = readInt();
			int y2 = readInt();
			l[z] = new Line(x, y1, y2);
			p[z * 2] = new Point(x, y1);
			p[z * 2 + 1] = new Point(x, y2);
		}
		int max = 0;
		double A = 0;
		double B = 0;
		for (int i = 0; i < 2 * n; i++) {
			for (int j = i + 1; j < 2 * n; j++) {
				double dx = p[i].x - p[j].x;
				double dy = p[i].y - p[j].y;
				double slope = dy / dx;
				double b = p[i].y - slope * p[i].x;
				double currA = round(b);
				double currB = round(100 * slope + b);
				int count = 0;
				for (int k = 0; k < n; k++) {
					double lineY = round(slope * l[k].x + b);
					if (l[k].y1 <= lineY && lineY <= l[k].y2) {
						count++;
					}
				}

				if ((count > max || (count == max && currA < A) || (count == max
						&& currA == A && currB < B))
						&& (currA >= 0 && currB >= 0)) {
					max = count;
					A = currA;
					B = currB;
				}
			}
		}
		if (max == 0) {
			for (int i = 0; i < 2 * n; i++) {
				double currA1 = 0;
				double slope1 = (p[i].y - 0.0d) / (p[i].x - 0.0d);
				double currB1 = Math.round(100 * slope1);

				double currB2 = 100;
				double slope2 = (p[i].y - 100.0d) / (p[i].x - 100.0d);
				double b2 = 100.0d - 100.0d * slope2;
				double currA2 = Math.round(b2);
				if ((max == 0 || (currA1 < A) || (currA1 == A && currB1 < B))
						&& (currA1 >= 0 && currB1 >= 0)) {
					A = currA1;
					B = currB1;
					max = 1;
				}
				if ((max == 0 || (currA2 < A) || (currA2 == A && currB2 < B))
						&& (currA2 >= 0 && currB2 >= 0)) {
					A = currA2;
					B = currB2;
					max = 1;
				}
			}
		}
		System.out.println(max + "\n" + A + "\n" + B);
	}

	private static double round (double value) {
		long factor = 10000000000l;
		value = value * factor;
		long tmp = (long) (value + 0.5);
		return (double) tmp / factor;
	}

	static class Line {
		int x, y1, y2;

		Line (int x, int y1, int y2) {
			this.x = x;
			this.y1 = y1;
			this.y2 = y2;
		}
	}

	static class Point {
		int x, y;

		Point (int x, int y) {
			this.x = x;
			this.y = y;
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
