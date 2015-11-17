package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2012_Times_17 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		StringBuilder s1 = new StringBuilder(next());
		StringBuilder s2 = new StringBuilder(s1.toString().concat("0000"));
		s1.reverse();
		s2.reverse();
		String result = "";
		int carry = 0;
		for (int x = 0; x < s2.length(); x++) {
			if (x >= s1.length()) {
				result += (s2.charAt(x) + carry) % 2;
				carry = (s2.charAt(x) + carry) / 2;
			} else {
				int sum = s1.charAt(x) - 48 + s2.charAt(x) - 48;
				result += (sum + carry) % 2;
				carry = (sum + carry) / 2;
			}
		}
		System.out.println(new StringBuilder(result).reverse());
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
