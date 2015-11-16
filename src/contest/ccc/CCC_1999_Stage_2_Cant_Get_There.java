package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CCC_1999_Stage_2_Cant_Get_There {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			double c = readDouble();
			double r = readDouble();
			double y = readDouble();
			double x = readDouble();
			double ey = readDouble();
			double ex = readDouble();

			int dir = 0;
			double m = 0;
			String in = next().trim();
			if (in.equals("NE")) {
				dir = 0;
				m = 1;
			} else if (in.equals("NW")) {
				dir = 1;
				m = -1;
			} else if (in.equals("SW")) {
				dir = 2;
				m = 1;
			} else if (in.equals("SE")) {
				dir = 3;
				m = -1;
			}
			int count = 0;
			HashSet<Point> v = new HashSet<Point>();
			while (true) {
				if (v.contains(new Point(x, y, dir))) {
					System.out.println("B cannot be reached from A.");
					break;
				}
				v.add(new Point(x, y, dir));
				double prevy = y;
				double b = y - m * x;
				if (ey == m * ex + b && (((dir == 0 || dir == 1) && ey >= y) || (dir == 2 || dir == 3) && ey <= y)) {
					count += Math.abs(ey - prevy);
					System.out.printf("B can be reached from A after %d move(s).\n", count);
					break;
				}
				if (dir == 0) {
					double x1 = r;
					double y1 = x1 * m + b;
					double y2 = c;
					double x2 = (y2 - b) / m;
					if (y1 == c) {
						x = r;
						y = c;
						dir = 2;
						m = 1;
					} else if (y1 < c) {
						x = x1;
						y = y1;
						dir = 1;
						m *= -1;
					} else {
						x = x2;
						y = y2;
						dir = 3;
						m *= -1;
					}
				} else if (dir == 1) {
					double x1 = 0;
					double y1 = x1 * m + b;
					double y2 = c;
					double x2 = (y2 - b) / m;
					if (y1 == c) {
						x = 0;
						y = c;
						dir = 3;
						m = -1;
					} else if (y1 < c) {
						x = x1;
						y = y1;
						dir = 0;
						m *= -1;
					} else {
						x = x2;
						y = y2;
						dir = 2;
						m *= -1;
					}
				} else if (dir == 2) {
					double x1 = 0;
					double y1 = x1 * m + b;
					double y2 = 0;
					double x2 = (y2 - b) / m;
					if (y1 == 0) {
						x = 0;
						y = 0;
						dir = 0;
						m = 1;
					} else if (y1 > 0) {
						x = x1;
						y = y1;
						dir = 3;
						m *= -1;
					} else {
						x = x2;
						y = y2;
						dir = 1;
						m *= -1;
					}
				} else if (dir == 3) {
					double x1 = r;
					double y1 = x1 * m + b;
					double y2 = 0;
					double x2 = (y2 - b) / m;
					if (y1 == 0) {
						x = r;
						y = 0;
						dir = 1;
						m = -1;
					} else if (y1 > 0) {
						x = x1;
						y = y1;
						dir = 2;
						m *= -1;
					} else {
						x = x2;
						y = y2;
						dir = 0;
						m *= -1;
					}
				}
				count += Math.abs(prevy - y);
			}
		}
	}

	static class Point {
		short x, y, m;

		Point (double x, double y, double m) {
			this.x = (short) x;
			this.y = (short) y;
			this.m = (short) m;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Point) {
				Point p = (Point) o;
				return p.x == x && p.y == y && p.m == m;
			}
			return false;
		}

		@Override
		public int hashCode () {
			return new Short(x).hashCode() * 31 * 31 + new Short(y).hashCode() * 31 + new Short(m).hashCode();
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
