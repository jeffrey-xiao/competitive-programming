package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2007_LEKTIRA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		String s = next();
		String currLow = new String(s);
		for (int x = 1; x < s.length() - 1; x++) {
			for (int y = x + 1; y < s.length(); y++) {
				StringBuilder s1 = new StringBuilder(s.substring(0, x)).reverse();
				StringBuilder s2 = new StringBuilder(s.substring(x, y)).reverse();
				StringBuilder s3 = new StringBuilder(s.substring(y, s.length())).reverse();
				String c = s1.toString() + s2.toString() + s3.toString();
				if (compare(currLow, c) > 0)
					currLow = c;
			}
		}
		System.out.println(currLow);
	}

	private static int compare (String s1, String s2) {
		for (int x = 0; x < s1.length(); x++) {
			if (s1.charAt(x) != s2.charAt(x))
				return new Integer(s1.charAt(x)).compareTo(new Integer(s2.charAt(x)));

		}
		return 0;
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
