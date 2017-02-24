package contest.ecoo;

import java.util.*;
import java.io.*;

public class ECOO_2016_R2_P1 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int TEST_CASES = 10;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));

		// br = new BufferedReader(new FileReader("in.txt"));
		// br = new BufferedReader(new FileReader("DATA11.txt"));

		for (int t = 1; t <= TEST_CASES; t++) {
			String in = readLine();
			int[] max = getLongestPalindrome(in);
			int ans = 0;
			for (int i = 0; i < max.length; i++) {
				if (Math.min(max.length - 1 - i, i) == max[i]) {
					if (i % 2 == 0)
						ans = Math.max(ans, max[i]);
					else
						ans = Math.max(ans, max[i]);
				}
			}
			out.println(in.length() - ans);
		}

		out.close();
	}

	static int[] getLongestPalindrome (String s) {
		int len = s.length() * 2 + 1;
		char[] text = new char[len];
		for (int i = 0; i < len; i++)
			text[i] = '#';
		for (int i = 1; i < len; i += 2)
			text[i] = s.charAt(i / 2);
		int[] max = new int[len];

		// center of palindrome with the greatest right boundary
		int c = 0;
		// right boundary of palindrome
		int r = 0;
		for (int i = 1; i < len; i++) {
			// the index of the mirror of i with respects to center c
			int j = (c - (i - c));

			// initializing the length of the palindrome centered at i
			max[i] = r > i ? Math.min(r - i, max[j]) : 0;
			// extending the palindrome at i 
			while (i + 1 + max[i] < len && i - 1 - max[i] >= 0 && text[i + 1 + max[i]] == text[i - 1 - max[i]])
				max[i]++;

			if (i + max[i] > r) {
				r = i + max[i];
				c = i;
			}
		}
		return max;
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
