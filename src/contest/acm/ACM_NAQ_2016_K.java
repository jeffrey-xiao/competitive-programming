package contest.acm;

import java.util.*;
import java.io.*;

public class ACM_NAQ_2016_K {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int t = readInt();

		for (int i = 0; i < t; i++) {
			int a1 = readInt();
			int a2 = readInt();
			int b1 = readInt();
			int b2 = readInt();
			int c1 = readInt();
			int c2 = readInt();

			x = 0;
			y = 0;
			linearDiophantine(a1, b1, c1);
			int x1 = x;
			int y1 = y;

			x = 0;
			y = 0;
			linearDiophantine(a2, b2, c2);
			int x2 = x;
			int y2 = y;

			int lcm1 = lcm(a1, b1);
			int lcm2 = lcm(a2, b2);

			if (x1 == -1 << 30 && y1 == -1 << 30 || x2 == -1 << 30 && y2 == -1 << 30) {
				out.println("?");
				continue;
			}

			while (x1 - lcm1 / a1 > 0) {
				x1 -= lcm1 / a1;
				y1 += lcm1 / b1;
			}

			while (x2 - lcm2 / a2 > 0) {
				x2 -= lcm2 / a2;
				y2 += lcm2 / b2;
			}

			int solutions = 0;
			int ansx = 0, ansy = 0;

			while (y2 > 0 && y1 > 0) {
				if (x1 == x2) {
					if (y1 == y2) {
						if (x1 > 0 && x2 > 0 && y1 > 0 && y2 > 0) {
							solutions++;
							ansx = x1;
							ansy = y1;
						}
					}
					x1 += lcm1 / a1;
					y1 -= lcm1 / b1;

					x2 += lcm2 / a2;
					y2 -= lcm2 / b2;
				} else if (x1 < x2) {
					x1 += lcm1 / a1;
					y1 -= lcm1 / b1;
				} else if (x2 < x1) {
					x2 += lcm2 / a2;
					y2 -= lcm2 / b2;
				}
			}

			if (solutions == 1) {
				out.printf("%d %d\n", ansx, ansy);
			} else {
				out.println("?");
			}
		}

		out.close();
	}

	// computes x and y such that ax + by = c; on failure, x = y = -1
	static void linearDiophantine (int a, int b, int c) {
		int d = gcd(a, b);
		if (c % d != 0) {
			x = y = -1 << 30;
		} else {
			a /= d;
			b /= d;
			c /= d;
			int[] ret = euclid(a, b);
			x = ret[1] * c;
			y = ret[2] * c;
		}
	}

	static int x, y;

	// returns d = gcd(a, b); finds x, y such that d = ax * by
	public static int[] euclid (int a, int b) {
		int x = 1, y = 0, x1 = 0, y1 = 1, t;
		while (b != 0) {
			int q = a / b;
			t = x;
			x = x1;
			x1 = t - q * x1;
			t = y;
			y = y1;
			y1 = t - q * y1;
			t = b;
			b = a - q * b;
			a = t;
		}
		return a > 0 ? new int[] {a, x, y} : new int[] {-a, -x, -y};
	}

	static int gcd (int a, int b) {
		return b == 0 ? a : (gcd(b, a % b));
	}

	static int lcm (int a, int b) {
		return a / gcd(a, b) * b;
	}

	static int mod (int a, int b) {
		return ((a % b) + b) % b;
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
