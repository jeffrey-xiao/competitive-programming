package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class VMSS_Tree_Planting {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int[][] bit = new int[4100][4100];
	static final int MOD = 1000000007;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int ans = 0;
		for (int i = 0; i < n; i++) {
			int j = readInt();
			int r = readInt();
			int c = readInt();
			int t = readInt();
			Point np = getPoint(r, c);
			if (j == 1) {
				update(np.x, np.y, t);
			} else {
				Point p1 = getPoint(r - t - 1, c + t + 1);
				Point p2 = getPoint(r, c);
				ans = (ans + sum(p2.x, p2.y) - sum(p1.x, p1.y)) % MOD;
			}
		}
		System.out.println(ans);
	}

	private static void update (int x, int y, int val) {
		for (int indx = x; indx < 4100; indx += (indx & -indx)) {
			bit[indx][y] = (bit[indx][y] + val) % MOD;
		}
	}

	private static int sum (int x, int y) {
		int sum = 0;
		for (int indx = x; indx > 0; indx -= (indx & -indx)) {
			sum = (sum + bit[indx][y]) % MOD;
		}
		return sum;
	}

	private static Point getPoint (int x, int y) {
		return new Point(x - y + 2000, x + y);
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
