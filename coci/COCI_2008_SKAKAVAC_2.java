package coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class COCI_2008_SKAKAVAC_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int n, r, c;
	static int[] poss = {-1, 1};
	static final short INF = 1 << 14;

	public static void main (String[] args) throws IOException {
		n = readInt();
		r = readInt() - 1;
		c = readInt() - 1;
		Point[] p = new Point[n * n];
		State[][] br = new State[n][4];
		State[][] bc = new State[n][4];
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < 4; y++) {
				br[x][y] = new State();
				bc[x][y] = new State();
				br[x][y].dp = -INF;
				bc[x][y].dp = -INF;
			}
		}
		int count = 0;
		for (short x = 0; x < n; x++)
			for (short y = 0; y < n; y++) {
				int leaves = readInt();
				p[count] = new Point(x, y, leaves);
				if (x == r && y == c)
					p[count].dp = 1;
				else
					p[count].dp = -INF;
				count++;
			}
		Arrays.sort(p);
		int y = 0;
		int total = 0;
		for (int x = 0; x < n * n; x = y) {
			for (y = x; y < n * n && p[x].p == p[y].p; y++) {
				// System.out.println(p[y].x + " " + p[y].y);
				short max = p[y].dp;
				if (p[y].x > 0)
					max = (short) Math.max(max,
							1 + getMax(br, p[y].x - 1, p[y]));
				if (p[y].x + 1 < n)
					max = (short) Math.max(max,
							1 + getMax(br, p[y].x + 1, p[y]));
				if (p[y].y > 0)
					max = (short) Math.max(max,
							1 + getMax(bc, p[y].y - 1, p[y]));
				if (p[y].y + 1 < n)
					max = (short) Math.max(max,
							1 + getMax(bc, p[y].y + 1, p[y]));
				p[y].dp = max;
				total = Math.max(p[y].dp, total);
			}
			for (y = x; y < n * n && p[x].p == p[y].p; y++) {
				update(br, p[y].x, p[y]);
				update(bc, p[y].y, p[y]);
			}
			// for(int z = 0; z < n; z++){
			// System.out.print(br[z][0].dp + " ");
			// }
			// System.out.println();
			// for(int z = 0; z < n; z++){
			// System.out.print(bc[z][0].dp + " ");
			// }
			// System.out.println();
			// System.out.println();
		}
		System.out.println(total);
	}

	private static short getMax (State[][] a, int i, Point p) {
		for (int x = 0; x < 4; x++) {
			if (Math.abs(p.y - a[i][x].y) <= 1
					&& Math.abs(p.x - a[i][x].x) <= 1)
				continue;
			return a[i][x].dp;
		}
		return -INF;
	}

	private static void update (State[][] a, int i, Point p) {
		State c = new State();
		c.x = p.x;
		c.y = p.y;
		c.dp = p.dp;
		for (int x = 0; x < 4; x++) {
			if (c.dp > a[i][x].dp) {
				State temp = c;
				c = a[i][x];
				a[i][x] = temp;
			}
		}
	}

	static class State {
		short x, y;
		short dp;
	}

	static class Point implements Comparable<Point> {
		short x, y;
		int p;
		short dp;

		Point () {
		}

		Point (short x, short y, int p) {
			this.x = x;
			this.y = y;
			this.p = p;
		}

		@Override
		public int compareTo (Point o) {
			return p - o.p;
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
