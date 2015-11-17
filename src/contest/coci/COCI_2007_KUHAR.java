package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2007_KUHAR {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final int SIZE = 5000000;
	static int[] needed;
	static int[] have;
	static int[] sm;
	static int[] pm;
	static int[] sv;
	static int[] pv;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		needed = new int[n];
		have = new int[n];
		sm = new int[n];
		pm = new int[n];
		sv = new int[n];
		pv = new int[n];
		for (int i = 0; i < n; i++) {
			needed[i] = readInt();
			have[i] = readInt();
			sm[i] = readInt();
			pm[i] = readInt();
			sv[i] = readInt();
			pv[i] = readInt();
		}
		int lo = 0;
		int hi = 50000;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int totalCost = 0;
			for (int i = 0; i < n; i++) {
				int amountNeeded = (mid * needed[i] - have[i]);
				totalCost += cost(amountNeeded, i);
			}
			if (totalCost <= m)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		System.out.println(hi);
	}

	private static int cost (int a, int i) {
		int res = 1 << 30;
		for (int j = 0; j * sm[i] <= a + 2 * sm[i]; j++) {
			int next = (a - j * sm[i] + sv[i] - 1) / sv[i];
			if (next < 0)
				break;
			res = Math.min(res, next * pv[i] + j * pm[i]);
		}
		return res;
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
