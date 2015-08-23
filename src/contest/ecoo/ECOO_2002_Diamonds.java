package contest.ecoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ECOO_2002_Diamonds {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = 0; t < 5; t++) {
			char[] n = readLine().toCharArray();
			for (int x = 0; x < n.length; x++) {
				for (int y = 0; y < n.length - 1 - x; y++)
					System.out.print(" ");
				System.out.print(n[x]);
				if (x != 0) {
					for (int y = 0; y < x * 2 - 1; y++)
						System.out.print(" ");
					System.out.print(n[x]);
				}
				System.out.println();
			}
			for (int x = n.length - 2; x >= 0; x--) {
				for (int y = 0; y < n.length - 1 - x; y++)
					System.out.print(" ");
				System.out.print(n[x]);
				if (x != 0) {
					for (int y = 0; y < x * 2 - 1; y++)
						System.out.print(" ");
					System.out.print(n[x]);
				}
				System.out.println();
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
