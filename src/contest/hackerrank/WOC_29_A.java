package contest.hackerrank;

import java.util.*;
import java.io.*;

public class WOC_29_A {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int year = readInt();

		if (year != 1918) {
			if ((year > 1918 && isLeapYearGregorian(year)) || (year < 1918 && isLeapYearJulian(year)))
				out.printf("12.09.%04d\n", year);
			else
				out.printf("13.09.%04d\n", year);
		} else {
			out.printf("26.09.%04\n", year);
		}

		out.close();
	}

	static boolean isLeapYearGregorian (int year) {
		return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
	}

	static boolean isLeapYearJulian (int year) {
		return year % 4 == 0;
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
