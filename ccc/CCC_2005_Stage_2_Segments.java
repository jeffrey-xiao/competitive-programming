package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2005_Stage_2_Segments {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static Interval[] l;
	static int dp[][];
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		l = new Interval[n];
		// dp = new int[n+1][n+1];
		for (int x = 0; x < n; x++) {
			int lo = readInt();
			int hi = readInt();
			l[x] = new Interval(lo, hi);
		}
		System.out.println(compute(1, 0) + n - 1);
	}

	private static int compute (int i, int r) {

		// if(dp[i][r] > 0)
		// return dp[i][r];
		int min = Integer.MAX_VALUE;
		if (i <= l[r].lo) {
			if (r == n - 1)
				min = Math.min(min, l[r].hi - i + (n - l[r].hi));
			else
				min = Math.min(min, l[r].hi - i + compute(l[r].hi, r + 1));
		} else if (i >= l[r].hi) {
			if (r == n - 1)
				min = Math.min(min, i - l[r].lo + (n - l[r].lo));
			else
				min = Math.min(min, i - l[r].lo + compute(l[r].lo, r + 1));
		} else {
			if (r == n - 1) {
				min = Math.min(min, l[r].hi - l[r].lo + i - l[r].lo + n
						- l[r].lo);
				min = Math.min(min, l[r].hi - l[r].lo + l[r].hi - i + n
						- l[r].hi);
			} else {
				int a = l[r].hi - l[r].lo + i - l[r].lo;
				int b = l[r].hi - l[r].lo + l[r].hi - i;
				min = Math.min(min, a + compute(l[r].lo, r + 1));
				min = Math.min(min, b + compute(l[r].hi, r + 1));
			}
		}
		// dp[i][r] = min;
		// System.out.println(i + " " + r + " " + min);
		return min;
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
