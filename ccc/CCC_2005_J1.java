package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2005_J1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int d = readInt();
		int n = readInt();
		int w = readInt();
		int a = Math.max(0, d - 100) * 25 + 15 * n + 20 * w;
		int b = Math.max(0, d - 250) * 45 + 35 * n + 25 * w;
		System.out.printf("Plan A costs %d cents\n", a);
		System.out.printf("Plan B costs %d cents\n", b);
		if (a < b)
			System.out.println("Plan A is cheapest.");
		else if (a == b)
			System.out.println("Plan A and B are the same price.");
		else
			System.out.println("Plan B is cheapest.");
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
