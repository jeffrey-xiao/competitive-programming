package ecoo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ECOO_2013_The_Luhn_Algorithm {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int k = 0; k < 5; k++) {
			for (int i = 0; i < 5; i++) {
				String n = next();
				int total = 0;
				int currSum = 0;
				boolean mult = true;
				for (int j = n.length() - 1; j >= 0; j--) {
					int num = n.charAt(j) - '0';
					if (mult) {
						currSum = num * 2;
						while (currSum != 0) {
							total += currSum % 10;
							currSum /= 10;
						}
					} else {
						total += num;
					}
					mult = !mult;
				}
				System.out.print((10 - total % 10) % 10);
			}
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
