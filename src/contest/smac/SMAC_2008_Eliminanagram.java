package contest.smac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SMAC_2008_Eliminanagram {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int[] alpha = new int[26];
		char[] s1 = next().toCharArray();
		char[] s2 = next().toCharArray();
		for (int x = 0; x < s1.length; x++)
			alpha[s1[x] - 'a']++;
		for (int x = 0; x < s2.length; x++)
			alpha[s2[x] - 'a']++;
		for (int x = 0; x < 26; x++)
			if (alpha[x] % 2 != 0) {
				System.out.println("No");
				return;
			}
		System.out.println("Yes");
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
