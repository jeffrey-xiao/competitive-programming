package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2007_PRINOVA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] kids;
	static int a;
	static int b;
	static int max = -1;
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		kids = new int[n];
		for (int x = 0; x < n; x++)
			kids[x] = readInt();
		a = readInt();
		b = readInt();
		checkMax(a);
		checkMax(a + 1);
		checkMax(b);
		checkMax(b - 1);
		for (int x = 0; x < n; x++) {
			for (int y = x + 1; y < n; y++) {
				checkMax((kids[x] + kids[y]) / 2 - 1);
				checkMax((kids[x] + kids[y]) / 2);
				checkMax((kids[x] + kids[y]) / 2 + 1);
			}
		}
		System.out.println(max);
	}

	private static void checkMax (int x) {
		if (x < a || x > b || x % 2 == 0)
			return;
		int i = Integer.MAX_VALUE;
		int j = Integer.MAX_VALUE;
		for (int y = 0; y < n; y++) {
			i = Math.min(i, Math.abs(x - kids[y]));
			j = Math.min(j, Math.abs(max - kids[y]));
		}
		if (i > j || max == -1) {
			max = x;
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
