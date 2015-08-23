package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class COCI_2008_SKAKAVAC {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int n, r, c;
	static int[] poss = {-1, 1};

	public static void main (String[] args) throws IOException {
		n = readInt();
		r = readInt() - 1;
		c = readInt() - 1;
		Point[] p = new Point[n * n];
		int[][] best = new int[n][n];
		int[][] val = new int[n][n];
		int count = 0;
		for (short x = 0; x < n; x++)
			for (short y = 0; y < n; y++) {
				int leaves = readInt();
				p[count++] = new Point(x, y, leaves);
				best[x][y] = -1;
				val[x][y] = leaves;
			}
		best[r][c] = 1;
		Arrays.sort(p);
		int max = 0;
		for (int z = 0; z < n * n; z++) {
			int x = p[z].x;
			int y = p[z].y;
			if (best[x][y] == -1)
				continue;
			for (int i = 0; i < 2; i++) {
				for (int k = 0; k < n; k++) {
					if (x + poss[i] >= 0 && x + poss[i] < n
							&& Math.abs(k - y) > 1
							&& val[x + poss[i]][k] > val[x][y]) {
						best[x + poss[i]][k] = Math.max(best[x + poss[i]][k],
								best[x][y] + 1);
					}
					if (y + poss[i] >= 0 && y + poss[i] < n
							&& Math.abs(k - x) > 1
							&& val[k][y + poss[i]] > val[x][y]) {
						best[k][y + poss[i]] = Math.max(best[k][y + poss[i]],
								best[x][y] + 1);
					}
				}
			}
			max = Math.max(max, best[x][y]);
		}
		System.out.println(max);
	}

	static class Point implements Comparable<Point> {
		short x, y;
		int p;

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
