package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class COCI_2006_DVAPUT {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final long MOD = 100000000007l;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		char[] str = next().toCharArray();
		int lo = 1;
		int hi = str.length;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			HashSet<Long> hs = new HashSet<Long>();
			long hash = 0;
			long pow = 1;
			for (int i = 0; i < mid; i++) {
				pow = (26 * pow) % MOD;
				hash = (26 * hash + (long) (str[i] - 'a')) % MOD;
			}
			hs.add(hash);
			boolean success = false;
			for (int i = mid; i < str.length; i++) {
				// System.out.println(26*hash + " " + pow*(long)str[i-mid] + " "
				// + (long)str[i]);
				hash = (26 * hash - (pow * (long) (str[i - mid] - 'a')) + (long) (str[i] - 'a'))
						% MOD;
				if (hs.contains(hash)) {
					success = true;
					break;
				}
				hs.add(hash);
			}
			// System.out.println(mid + " " + success);
			if (success) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		System.out.println(hi);
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
