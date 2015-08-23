package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2005_Stage_2_Segments_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		Interval[] l = new Interval[n + 1];
		for (int x = 1; x <= n; x++) {
			int lo = readInt();
			int hi = readInt();
			l[x] = new Interval(lo, hi);
		}
		int costLL = n - l[n].lo;
		int costLR = (l[n].hi - l[n].lo) + (n - l[n].lo);
		// System.out.println(costLL + " " + costLR);
		for (int x = n - 1; x >= 1; x--) {
			int a = Math.min(Math.abs(l[x + 1].lo - l[x].hi) + costLL,
					Math.abs(l[x + 1].hi - l[x].hi) + costLR);
			int b = Math.min(Math.abs(l[x + 1].lo - l[x].lo) + costLL,
					Math.abs(l[x + 1].hi - l[x].lo) + costLR);
			int c = 1 + l[x].hi - l[x].lo;
			int costL = c + a;
			costLR = c + b;
			costLL = costL;
			// System.out.println(costLR + " " + costLL + " " + a + " " + b);
		}
		System.out
				.println(Math.min(costLL + l[1].lo - 1, costLR + l[1].hi - 1));
	}

	static class Interval {
		int lo, hi;

		Interval (int lo, int hi) {
			this.lo = lo;
			this.hi = hi;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
