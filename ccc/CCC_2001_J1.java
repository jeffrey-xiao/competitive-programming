package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2001_J1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		for (int x = 1; x <= n; x += 2) {
			String s1 = fill(x, '*');
			String s2 = fill(n - x, ' ');
			System.out.println(s1 + s2 + s2 + s1);
		}
		for (int x = n - 2; x >= 1; x -= 2) {
			String s1 = fill(x, '*');
			String s2 = fill(n - x, ' ');
			System.out.println(s1 + s2 + s2 + s1);
		}
	}

	public static String fill (int n, char c) {
		String s = "";
		for (int x = 0; x < n; x++)
			s += c;
		return s;
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
