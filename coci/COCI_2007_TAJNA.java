package coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2007_TAJNA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		String s = next();
		int l = s.length();
		int max = 0;
		for (int x = 1; x * x <= l; x++) {
			if (l % x == 0)
				max = x;
		}
		char[][] c = new char[max][l / max];
		int count = 0;
		for (int y = 0; y < l / max; y++) {
			for (int x = 0; x < max; x++) {
				c[x][y] = s.charAt(count);
				count++;
			}
		}
		for (int x = 0; x < max; x++) {
			for (int y = 0; y < l / max; y++) {
				System.out.print(c[x][y]);
			}
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
