package contest.ecoo;

import java.util.*;
import java.io.*;

public class ECOO_2016_R3_P2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int TEST_CASES = 10; // 10

	static int Aw, Ah, Bx, By, Sx, Sy;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt")); // DATA21.txt
		// out = new PrintWriter(new FileWriter("out.txt"));

		for (int t = 1; t <= TEST_CASES; t++) {
			Aw = readInt();
			Ah = readInt();
			Bx = readInt();
			By = readInt();
			Sx = readInt();
			Sy = readInt();

			for (int walls = 1; walls <= 5; walls++) {
				double Th = readInt();
				double Tx = readInt();
				double Ty = readInt();
				double x = Bx;
				double y = By;
				double dx = Sx;
				double dy = Sy;

				if (dx == 0) {
					if (dy == 0) {
						out.print("M");
						out.flush();
					} else if (x == Tx) {
						out.print("H");
						out.flush();
					} else {
						out.print("M");
						out.flush();
					}
				} else if (dy == 0) {
					if (Ty >= y && y >= Ty - Th) {
						if ((dx > 0 && Tx >= x) || (dx < 0 && Tx <= x)) {
							out.print("H");
							out.flush();
						} else {
							out.print("M");
							out.flush();
						}
					} else {
						out.print("M");
						out.flush();
					}
				} else {

					boolean valid = true;

					while (true) {
						if (x > Tx) {
							valid = false;
							break;
						}

						// get target bounce
						double targetY = dy / dx * (Tx - x) + y;
						if (Ty >= targetY && targetY >= Ty - Th) {
							valid = true;
							break;
						}

						// get top and bottom wall bounces
						double nx = 0, ny = 0;
						if (dy / dx > 0) {
							ny = Ah;
							nx = (ny - y) / (dy / dx) + x;
							x = nx;
							y = ny;
							dy *= -1;
						} else {
							ny = 0;
							nx = (ny - y) / (dy / dx) + x;
							x = nx;
							y = ny;
							dy *= -1;
						}
					}
					if (valid) {
						out.print("H");
						out.flush();
					} else {
						out.print("M");
						out.flush();
					}
				}
			}
			out.println();
		}

		out.close();
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
