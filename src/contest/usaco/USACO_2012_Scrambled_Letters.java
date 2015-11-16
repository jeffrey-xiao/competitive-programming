package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class USACO_2012_Scrambled_Letters {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		String[] alpha = new String[n];
		String[] rev = new String[n];
		String[] words = new String[n];
		for (int x = 0; x < n; x++) {
			String curr = next();
			words[x] = curr;
			char[] ordered = curr.toCharArray();
			Arrays.sort(ordered);
			String s1 = new String(ordered);
			alpha[x] = s1;
			String s2 = new StringBuilder(new String(ordered)).reverse().toString();
			rev[x] = s2;
		}
		Arrays.sort(alpha);
		Arrays.sort(rev);
		for (int x = 0; x < n; x++) {
			char[] curr = words[x].toCharArray();
			Arrays.sort(curr);
			String s1 = new String(curr);
			String s2 = new StringBuilder(s1).reverse().toString();
			int bound1 = Arrays.binarySearch(rev, s1);
			if (bound1 < 0)
				bound1 = -bound1 - 1;
			bound1++;
			int bound2 = Arrays.binarySearch(alpha, s2);
			if (bound2 < 0)
				bound2 = -bound2 - 1;
			else
				bound2++;
			System.out.println(Math.min(bound2, bound1) + " " + Math.max(bound2, bound1));
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
