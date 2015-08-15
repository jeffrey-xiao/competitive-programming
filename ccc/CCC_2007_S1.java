package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2007_S1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			int year = readInt();
			int month = readInt();
			int day = readInt();
			// System.out.println(2007-year + " "+(2-month) + " " + (27-day));
			if (2007 - year > 18) {
				System.out.println("Yes");
			} else if (2007 - year == 18 && 2 - month > 0) {
				System.out.println("Yes");
			} else if (2007 - year == 18 && 2 - month == 0 && 27 - day >= 0) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
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
