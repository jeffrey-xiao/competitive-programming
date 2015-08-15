package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2006_J1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int[] b = {461, 431, 420, 0};
		int[] s = {100, 57, 70, 0};
		int[] dr = {130, 160, 118, 0};
		int[] ds = {167, 266, 75, 0};
		System.out.printf("Your total Calorie count is %d.", b[readInt() - 1]
				+ s[readInt() - 1] + dr[readInt() - 1] + ds[readInt() - 1]);
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
