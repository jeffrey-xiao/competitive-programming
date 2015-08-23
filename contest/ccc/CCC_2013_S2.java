package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2013_S2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int c = readInt();
		int total = 0;
		int x = 0;
		int[] carts = new int[c];
		for (; x < c; x++) {
			carts[x] = readInt();
			if (x >= 4) {
				total = (total + carts[x] - carts[x - 4]);
			} else
				total += carts[x];
			if (total > n) {
				x--;
				break;
			}
		}
		if (x == c)
			x--;
		System.out.println(x + 1);
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
