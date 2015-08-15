package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2000_J1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int d = readInt();
		System.out.println("Sun Mon Tue Wed Thr Fri Sat");
		for (int x = 0; x < n - 1; x++) {
			if (x == 0)
				System.out.print("   ");
			else
				System.out.print("    ");
		}
		for (int x = n; x < d + n; x++) {
			if ((x - 1) % 7 == 0)
				System.out.print(String.format("%3d", x - n + 1));
			else
				System.out.print(String.format("%4d", x - n + 1));
			if ((x - 1) % 7 == 6)
				System.out.println();
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
