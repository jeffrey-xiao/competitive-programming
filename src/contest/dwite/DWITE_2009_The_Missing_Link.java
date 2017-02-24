package contest.dwite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DWITE_2009_The_Missing_Link {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = 0; t < 5; t++) {
			StringBuilder s = new StringBuilder(next());
			int i = s.indexOf("_");
			boolean flag = false;
			for (int x = 0; x < 10; x++) {
				if (i + x == 0)
					continue;
				s.setCharAt(i, (char)(x + 48));
				if (isPrime(Integer.parseInt(s.toString()))) {
					flag = true;
					System.out.print(x + " ");
				}
			}
			if (!flag)
				System.out.print("Not possible");
			System.out.println();
		}
	}

	private static boolean isPrime (int n) {
		if (n <= 1)
			return false;
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;
		if (n < 9)
			return true;
		if (n % 3 == 0)
			return false;

		long counter = 5;
		while ((counter * counter) <= n) {
			if (n % counter == 0)
				return false;
			if (n % (counter + 2) == 0)
				return false;
			counter += 6;
		}

		return true;
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
