package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2000_J2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt(), m = readInt();
		int count = 0;
		for (int x = n; x <= m; x++)
			if (isValid(Integer.toString(x)))
				count++;
		System.out.println(count);
	}

	private static boolean isValid (String x) {
		int l = x.length() - 1;
		for (int y = 0; y < (x.length() + 1) / 2; y++) {
			char c1 = x.charAt(y);
			char c2 = x.charAt(l - y);
			if (!((c1 == '8' || c1 == '1' || c1 == '0') && c1 == c2) && !((c1 == '6' && c2 == '9') || (c1 == '9' && c2 == '6')))
				return false;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
