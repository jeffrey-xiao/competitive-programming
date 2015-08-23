package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class USACO_2014_Secret_Code {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static Map<String, Integer> codes = new HashMap<String, Integer>();

	public static void main (String[] args) throws IOException {
		String s = next();
		System.out.println(compute(s) - 1);

	}

	private static int compute (String s) {
		if (codes.containsKey(s))
			return codes.get(s);
		int ans = 1;
		int length = s.length();
		for (int x = 1; x * 2 < length; x++) {

			String s1 = s.substring(0, x);
			String s2 = s.substring(length - x, length);
			String s3 = s.substring(x, x * 2);
			String s4 = s.substring(length - x, length);
			String s5 = s.substring(length - 2 * x, length - x);
			System.out.println(s + " " + s1 + " " + s2 + " " + s3);
			if (s1.equals(s2)) {
				if (x != length - x) {
					ans += compute(s.substring(x, length));
					ans += compute(s.substring(0, length - x));
				}
			}
			if (s1.equals(s3)) {
				ans += compute(s.substring(x, length));
			}
			if (s4.equals(s5))
				ans += compute(s.substring(0, length - x));
		}
		ans %= 2014;
		System.out.println(s + " " + ans);
		codes.put(s, ans);
		return ans;
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
