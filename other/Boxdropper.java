package other;

import java.util.*;
import java.io.*;

public class Boxdropper {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static Box[] b = new Box[500];
	static double[][] d = new double[500][500];
	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));

		String in = null;
		int index = 0;
		while ((in = br.readLine()) != null) {
			st = new StringTokenizer(in);
			char c = readCharacter();
			if (c == 'B') {
				b[index] = new Box(readInt(), readInt(), readInt(), readInt());
				for (int i = 0; i < index; i++) {
					double dist = 1L << 60;
					for (int j = 0; j < index; j++)
						dist = Math.min(dist, d[i][j] + b[index].distTo(b[j]));
					d[i][index] = d[index][i] = dist;
				}
				for (int i = 0; i < index; i++) {
					for (int j = 0; j < index; j++) {
						d[i][j] = d[j][i] = Math.min(d[i][j], d[index][i] + d[index][j]);
					}
				}
				index++;
			} else if (c == 'G') {
				System.out.printf("%.3f\n", d[readInt()-1][readInt()-1]);
			}
		}
//		for (int i = 0; i < index; i++) {
//			for (int j = 0; j < index; j++) {
//				System.out.printf("%8.2f ",d[i][j]);
//			}
//			System.out.println();
//		}
		pr.close();
	}
	static class Box {
		double minx, maxx, miny, maxy;
		Box (int x1, int y1, int x2, int y2) {
			minx = Math.min(x1, x2);
			maxx = Math.max(x1, x2);
			miny = Math.min(y1, y2);
			maxy = Math.max(y1, y2);
		}
		public double distTo (Box b) {
			if (minx <= b.maxx && maxx >= b.minx && miny <= b.maxy && maxy >= b.miny) {
				return 0;
			} else if (b.minx >= maxx && b.miny >= maxy) {
				return dist(b.minx, b.miny, maxx, maxy);
			} else if (b.maxx <= minx && b.miny >= maxy) {
				return dist(b.maxx, b.miny, minx, maxy);
			} else if (b.maxx <= minx && b.maxy <= miny) {
				return dist(b.maxx, b.maxy, minx, miny);
			} else if (b.minx >= maxx && b.maxy <= miny) {
				return dist(b.minx, b.maxy, maxx, miny);
			} else if (b.maxx <= minx) {
				return minx - b.maxx;
			} else if (b.minx >= maxx) {
				return b.minx - maxx;
			} else if (b.maxy <= miny) {
				return miny - b.maxy;
			} else if (b.miny >= maxy) {
				return b.miny - maxy;
			}
			return Double.NaN;
		}
		public double dist (double x1, double y1, double x2, double y2) {
			return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		}
	}
	static String next () throws IOException {
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

