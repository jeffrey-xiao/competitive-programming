package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2006_S1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static char[] dom = new char[] {'A', 'B', 'C', 'D', 'E'};
	static char[] rec = new char[] {'a', 'b', 'c', 'd', 'e'};

	public static void main (String[] args) throws IOException {
		String s1 = next();
		String s2 = next();

		int n = readInt();
		main : for (int x = 0; x < n; x++) {
			String baby = next();
			for (int y = 0; y < 5; y++) {
				if (baby.charAt(y) == dom[y]) {
					if (!checkDom(s1, s2, y * 2)) {
						System.out.println("Not their baby!");
						continue main;
					}
				} else {
					if (!checkRec(s1, s2, y * 2)) {
						System.out.println("Not their baby!");
						continue main;
					}
				}
			}
			System.out.println("Possible baby.");
		}
	}

	private static boolean checkDom (String s1, String s2, int x) {
		return s1.charAt(x) == dom[x / 2] || s1.charAt(x + 1) == dom[x / 2]
				|| s2.charAt(x) == dom[x / 2] || s2.charAt(x + 1) == dom[x / 2];
	}

	private static boolean checkRec (String s1, String s2, int x) {
		return (s1.charAt(x) == rec[x / 2] || s1.charAt(x + 1) == rec[x / 2])
				&& (s2.charAt(x) == rec[x / 2] || s2.charAt(x + 1) == rec[x / 2]);
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
