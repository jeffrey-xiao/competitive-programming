package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2008_GLASNICI {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static double[] m;
	static int n;
	static double k;

	public static void main (String[] args) throws IOException {
		k = readDouble();
		n = readInt();
		m = new double[n];
		for (int x = 0; x < n; x++)
			m[x] = readDouble();
		double lo = 0;
		double hi = Integer.MAX_VALUE;
		// binary search an arbitrary number of times
		for (int x = 0; x < 100; x++) {
			double mid = lo + (hi - lo) / 2;
			if (isPossible(mid))
				hi = mid;
			else
				lo = mid;
		}
		System.out.println(lo);
	}

	private static boolean isPossible (double mid) {
		double nextDist = m[0] + mid;
		for (int x = 1; x < n; x++) {
			if (m[x] > nextDist + k + mid)
				return false;
			nextDist = Math.min(nextDist + k, m[x] + mid);
		}
		return true;
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
